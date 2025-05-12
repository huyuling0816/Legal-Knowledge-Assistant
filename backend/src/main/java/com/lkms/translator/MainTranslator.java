package com.lkms.translator;

import com.lkms.mapper.*;
import com.lkms.po.lawPo.*;
import com.lkms.utils.file.*;

import java.io.File;
import java.util.*;
public class MainTranslator {
    public LawDocMapper lawDocDAO;

    public LawPartMapper lawPartDAO;

    public LawChapterMapper lawChapDAO;

    public LawSectionMapper lawSectDAO;

    public LawArticleMapper lawArticleDAO;

    public LawParagraphMapper lawParaDAO;

    public LawSubparagraphMapper lawSubParaDAO;

    public LawItemMapper lawItemDAO;

    public  LawAppendixMapper appendixDAO;

    public LogicMapper logicDAO;

    public LogicTranslator logicTranslator;

    public MainTranslator(LawDocMapper lawDocDAO, LawPartMapper lawPartDAO, LawChapterMapper lawChapDAO, LawSectionMapper lawSectDAO, LawArticleMapper lawArticleDAO, LawParagraphMapper lawParaDAO,
                         LawSubparagraphMapper lawSubParaDAO, LawItemMapper lawItemDAO, LawAppendixMapper appendixDAO,LogicMapper logicDAO) {

        this.lawDocDAO=lawDocDAO;
        this.lawPartDAO=lawPartDAO;
        this.lawChapDAO=lawChapDAO;
        this.lawSectDAO=lawSectDAO;
        this.lawArticleDAO=lawArticleDAO;
        this.lawParaDAO=lawParaDAO;
        this.lawSubParaDAO=lawSubParaDAO;
        this.lawItemDAO=lawItemDAO;
        this.appendixDAO=appendixDAO;
        this.logicDAO = logicDAO;
        this.logicTranslator = new LogicTranslator(logicDAO);
    }

    public MainTranslator() {

    }

    public MainTranslator(LawDocMapper lawDocDAO, LawPartMapper lawPartDAO, LawChapterMapper lawChapterDAO, LawSectionMapper lawSectionDAO, LawArticleMapper lawArticleDAO, LawParagraphMapper lawParagraphDAO, LawSubparagraphMapper lawSubparagraphDAO, LawItemMapper lawItemDAO, LawAppendixMapper lawAppendixDAO) {
        this.lawDocDAO=lawDocDAO;
        this.lawPartDAO=lawPartDAO;
        this.lawChapDAO=lawChapterDAO;
        this.lawSectDAO=lawSectionDAO;
        this.lawArticleDAO=lawArticleDAO;
        this.lawParaDAO=lawParagraphDAO;
        this.lawSubParaDAO=lawSubparagraphDAO;
        this.lawItemDAO=lawItemDAO;
        this.appendixDAO=lawAppendixDAO;
    }

    public void translatorFolder(String folderPath) throws Exception {
        File folder = new File(folderPath);
        File[] files = folder.listFiles();

        if (files != null) {
            for (File file : files) {
                if (file.isDirectory()) {
                    translatorFolder(String.valueOf(file)); // 递归处理子文件夹
                } else {
                    translatorFile(String.valueOf(file));
                }
            }
        }
    }

    public void translatorFile(String filePath) throws Exception {
        StringToDate stringToDate = new StringToDate();
        ReadWordDoc readWordDoc = new ReadWordDoc();
        CheckNextLine checkNextLine = new CheckNextLine();
        //读取文本
        String lawText = "";
        String[] lawTextInLine;
        if (filePath.endsWith(".docx")){
            try {
                lawText = readWordDoc.readTextFromDocx(filePath).replaceAll("\u200B", "\n");
            } catch (Exception e){
                System.out.println(e);
                return;
            }
            lawTextInLine = lawText.split("\\R");
        }
        else{
            try {
                lawText = readWordDoc.readTextFromDoc(filePath).replaceAll("\t", " ").replaceAll("\u200B", "\n");
            } catch (Exception | NoClassDefFoundError e){
                System.out.println(e);
                return;
            }
            lawTextInLine = lawText.split("\\R");
        }
        //标记目前解析层级
        String state = "doc";
        //记录每一部分的内容
        List<String> docList = new ArrayList<>(); //全文
        List<String> partList = new ArrayList<>(); //编
        List<String> chapterList = new ArrayList<>(); //章
        List<String> sectionList = new ArrayList<>(); //节
        List<String> articleList = new ArrayList<>(); //条
        List<String> paragraphList = new ArrayList<>(); //款:“款”的表现形式为条中的自然段。每个自然段为一款。款前均无数字。有数字排列的不称为款。
        List<String> subparagraphList = new ArrayList<>(); //项:“项”前冠以数字以对列举的内容进行排列，各项前都冠以(一)、(二)、(三)、（四）等数字，而且这些数字只能以中文数字加括号的形式出现。
        List<String> itemList = new ArrayList<>(); //目:“目”的前面则冠以阿拉伯数字，并在阿拉伯数字后加点
        List<String> appendixList = new ArrayList<>(); //附

        //检查该行是否为空或仅有空格
        CheckLineEmpty checkLineEmpty = new CheckLineEmpty();

        String docId = UUID.randomUUID().toString();
        String partId = UUID.randomUUID().toString();
        String chapterId = UUID.randomUUID().toString();
        String sectionId = UUID.randomUUID().toString();
        String articleId = UUID.randomUUID().toString();
        String paragraphId = UUID.randomUUID().toString();
        String subparagraphId = UUID.randomUUID().toString();
        String itemId = UUID.randomUUID().toString();
        String appendixId =UUID.randomUUID().toString();

        int countOfParagraph = 0;
        //记录遍历的深度
        int[] deepPath = new int[8];
        //去除开头的空行
        int line = 0;
        //新的换行符添加在这里
        while (lawTextInLine[line].isEmpty() || lawTextInLine[line].equals("\n") || lawTextInLine[line].equals("\u200B")){
            line++;
        }
        //对文本中每一行进行解析
        for (; line<lawTextInLine.length; line++){
            String thisLine = lawTextInLine[line].replaceAll("\\p{Z}"," ").trim();

            if(state.equals("doc")){
                deepPath[0] = 1;
                for (int i=1;i<deepPath.length;i++){
                    deepPath[i] = 0;
                }
                //对多行标题的处理
//                line++;
//                while (!lawTextInLine[line].isEmpty()){
//                    thisLine += lawTextInLine[line];
//                    line++;
//                }

                for(line = line+1; line<lawTextInLine.length; line++){
                    if (lawTextInLine[line].startsWith("（") || lawTextInLine[line].startsWith("(")){
                        break;
                    }
                    else if (!lawTextInLine[line].isEmpty()){
                        thisLine += lawTextInLine[line];
                        line++;
                    }
                    else{
                        continue;
                    }
                }

                //对章头的处理
//                for (;line<lawTextInLine.length;line++){
//                    if (!lawTextInLine[line].isEmpty()){
//                        break;
//                    }
//                }
                String chapterHeader = lawTextInLine[line];
                line++;
                while (lawTextInLine[line].isEmpty()){
                    line++;
                }
                String passCatalog = lawTextInLine[line].replaceAll("\\p{Z}","");
                if (passCatalog.equals("目录")){
                    for (;line<lawTextInLine.length;line++){
                        if (lawTextInLine[line].isEmpty()){
                            break;
                        }
                    }
                }
                line--;
                LawDocPo lawDocPo = new LawDocPo();

//                String[] titleArray = filePath.split("\\\\");
//                String title = titleArray[titleArray.length-1].split("\\.")[0];


                //windows
//                String[] titleArray = path.split("\\\\");
//                String title = titleArray[titleArray.length-1].split("\\.")[0];

                //mac
                String[] titleArray=filePath.split("/");
                String title=titleArray[titleArray.length-1].split("\\.")[0];
                System.out.println(title);
                lawDocPo.setTitle(title);
                lawDocPo.setFullContent(lawText);
                //匹配通过日期publish
                docId = lawDocDAO.selectByName(title);
                lawDocPo.setId(docId);
                lawDocDAO.updateByDocName(chapterHeader,lawText,title);
                docList.add(thisLine);
            }
            else if(state.equals("part")){
                deepPath[1] = 1;
                for (int i=2;i<deepPath.length;i++){
                    deepPath[i] = 0;
                }
                LawPartPo lawPartPo = new LawPartPo();
                lawPartPo.setDocId(docId);
                partId = UUID.randomUUID().toString();
                lawPartPo.setId(partId);
                String[] thislineSeq = thisLine.split("\\p{Z}",2);
                lawPartPo.setPartSeq(thislineSeq[0]);
                lawPartPo.setPartName(thislineSeq[1].replaceAll(" ",""));
                partList.add(thisLine);
                lawPartDAO.insert(lawPartPo);
            }
            else if(checkLineEmpty.checkLineEmptyOrSpace(thisLine) && state.equals("chapter")){
                deepPath[2] = 1;
                for (int i=3;i<deepPath.length;i++){
                    deepPath[i] = 0;
                }
                LawChapterPo lawChapterPo = new LawChapterPo();
                lawChapterPo.setDocId(docId);
                if (deepPath[1] == 1){
                    lawChapterPo.setPartId(partId);
                }else{
                    lawChapterPo.setPartId(null);
                }
                chapterId = UUID.randomUUID().toString();
                lawChapterPo.setId(chapterId);
                String[] thislineSeq = thisLine.split("\\p{Z}",2);
                lawChapterPo.setChapterSeq(thislineSeq[0]);
                lawChapterPo.setChapterName(thislineSeq[1].replaceAll(" ",""));
                chapterList.add(thisLine);
                lawChapDAO.insert(lawChapterPo);
            }
            else if(checkLineEmpty.checkLineEmptyOrSpace(thisLine) && state.equals("section")){
                deepPath[3] = 1;
                for (int i=4;i<deepPath.length;i++){
                    deepPath[i] = 0;
                }
                LawSectionPo lawSectionPo = new LawSectionPo();
                lawSectionPo.setDocId(docId);
                if (deepPath[1] == 1){
                    lawSectionPo.setPartId(partId);
                }else{
                    lawSectionPo.setPartId(null);
                }
                if (deepPath[2] == 1){
                    lawSectionPo.setChapterId(chapterId);
                }else{
                    lawSectionPo.setChapterId(null);
                }
                sectionId = UUID.randomUUID().toString();
                lawSectionPo.setId(sectionId);
                String[] thislineSeq = thisLine.split("\\p{Z}",2);
                lawSectionPo.setSectionSeq(thislineSeq[0]);
                lawSectionPo.setSectionName(thislineSeq[1].replaceAll(" ",""));
                sectionList.add(thisLine);
                lawSectDAO.insert(lawSectionPo);
            }
            else if(checkLineEmpty.checkLineEmptyOrSpace(thisLine) && state.equals("article")){
                deepPath[4] = 1;
                //for logic
                String[] logicFKidList = new String[5];
                logicFKidList[0] = docId;
                for (int i=5;i<deepPath.length;i++){
                    deepPath[i] = 0;
                }
                LawArticlePo lawArticlePo = new LawArticlePo();
                lawArticlePo.setDocId(docId);
                LawParagraphPo lawParagraphPo = new LawParagraphPo();
                lawParagraphPo.setDocId(docId);
                if (deepPath[1] == 1){
                    logicFKidList[1] = partId;
                    lawArticlePo.setPartId(partId);
                    lawParagraphPo.setPartId(partId);
                }else{
                    logicFKidList[1] = null;
                    lawArticlePo.setPartId(null);
                    lawParagraphPo.setPartId(null);
                }
                if (deepPath[2] == 1){
                    logicFKidList[2] = chapterId;
                    lawArticlePo.setChapterId(chapterId);
                    lawParagraphPo.setChapterId(chapterId);
                }else{
                    logicFKidList[2] = null;
                    lawArticlePo.setChapterId(null);
                    lawParagraphPo.setChapterId(null);
                }
                if (deepPath[3] == 1){
                    logicFKidList[3] = sectionId;
                    lawArticlePo.setSectionId(sectionId);
                    lawParagraphPo.setSectionId(sectionId);
                }else{
                    logicFKidList[3] = null;
                    lawArticlePo.setSectionId(null);
                    lawParagraphPo.setSectionId(null);
                }
                articleId = UUID.randomUUID().toString();
                lawArticlePo.setId(articleId);
                logicFKidList[4] = articleId;
                String[] thislineSeq = thisLine.split("\\p{Z}",2);
                lawArticlePo.setArticleSeq(thislineSeq[0]);
                //change
                StringBuilder text = new StringBuilder(thislineSeq[1].replaceAll(" ", ""));
                for(int i=line+1; i<lawTextInLine.length; i++){
                    String articleLineText = lawTextInLine[i];
                    if (articleLineText.isEmpty() || articleLineText.equals("\n")){
                        continue;
                    }
                    String articleEnd = checkNextLine.checkNextLineState(articleLineText,state);
                    if (articleEnd.equals("paragraph")){
                        text.append("@Para@").append(articleLineText.replaceAll("\\p{Z}"," ").trim());
                    }
                    else if(articleEnd.equals("subparagraph")){
                        text.append("@Subp@").append(articleLineText.replaceAll("\\p{Z}"," ").trim());
                    }
                    else if (articleEnd.equals("item")){
                        text.append("@Item@").append(articleLineText.replaceAll("\\p{Z}"," ").trim());
                    }
                    else break;
                }
                lawArticlePo.setArticleName(text.toString());
                articleList.add(thisLine);
                lawArticleDAO.insert(lawArticlePo);
                logicTranslator.getLogic(thislineSeq[1].replaceAll(" ", ""),logicFKidList,String.valueOf(text));
                //XX条 YYYY
                lawParagraphPo.setArticleId(articleId);
                paragraphId = UUID.randomUUID().toString();
                lawParagraphPo.setId(paragraphId);
                lawParagraphPo.setParagraphSeq("第一款");
                deepPath[5] = 1;
                StringBuilder paragraphName = new StringBuilder(thislineSeq[1]);
                for (int i=line+1; i<lawTextInLine.length; i++){
                    String nextLine = lawTextInLine[i].replaceAll("\\p{Z}","").trim();
                    if (nextLine.startsWith("（")){
                        state = "paragraph";
                        paragraphName.append(nextLine);
                    }
                    else {
                        break;
                    }
                }
                lawParagraphPo.setParagraphName(paragraphName.toString());
                lawParaDAO.insert(lawParagraphPo);
                countOfParagraph = 1;
            }
            else if(checkLineEmpty.checkLineEmptyOrSpace(thisLine) && state.equals("paragraph")){
                deepPath[5] = 1;
                String[] logicFKidList = new String[5];
                logicFKidList[0] = docId;
                for (int i=6;i<deepPath.length;i++){
                    deepPath[i] = 0;
                }
                LawParagraphPo lawParagraphPo = new LawParagraphPo();
                lawParagraphPo.setDocId(docId);
                if (deepPath[1] == 1){
                    logicFKidList[1] = partId;
                    lawParagraphPo.setPartId(partId);
                }else{
                    logicFKidList[1] = null;
                    lawParagraphPo.setPartId(null);
                }
                if (deepPath[2] == 1){
                    logicFKidList[2] = chapterId;
                    lawParagraphPo.setChapterId(chapterId);
                }else{
                    logicFKidList[2] = null;
                    lawParagraphPo.setChapterId(null);
                }
                if (deepPath[3] == 1){
                    logicFKidList[3] = sectionId;
                    lawParagraphPo.setSectionId(sectionId);
                }else{
                    logicFKidList[3] = null;
                    lawParagraphPo.setSectionId(null);
                }
                if (deepPath[4] == 1){
                    logicFKidList[4] = articleId;
                    lawParagraphPo.setArticleId(articleId);
                }else{
                    logicFKidList[4] = null;
                    lawParagraphPo.setArticleId(null);
                }
                paragraphId = UUID.randomUUID().toString();
                lawParagraphPo.setId(paragraphId);
                lawParagraphPo.setParagraphSeq("第"+ (UpperNumber.convertToChinese(++countOfParagraph)) +"款");
                lawParagraphPo.setParagraphName(thisLine.trim());
                paragraphList.add(thisLine);
                logicTranslator.getLogic(thisLine.trim(),logicFKidList,thisLine.trim());
                lawParaDAO.insert(lawParagraphPo);
            }
            else if(checkLineEmpty.checkLineEmptyOrSpace(thisLine) && state.equals("subparagraph")){
                deepPath[6] = 1;
                for (int i=7;i<deepPath.length;i++){
                    deepPath[i] = 0;
                }
                LawSubparagraphPo lawSubparagraphPo = new LawSubparagraphPo();
                //可能插入
                LawParagraphPo lawParagraphPo = new LawParagraphPo();
                lawSubparagraphPo.setDocId(docId);
                //可能插入
                lawParagraphPo.setDocId(docId);
                if (deepPath[1] == 1){
                    lawSubparagraphPo.setPartId(partId);
                    lawParagraphPo.setPartId(partId);
                }else{
                    lawSubparagraphPo.setPartId(null);
                    lawParagraphPo.setPartId(null);
                }
                if (deepPath[2] == 1){
                    lawSubparagraphPo.setChapterId(chapterId);
                    lawParagraphPo.setChapterId(chapterId);
                }else{
                    lawSubparagraphPo.setChapterId(null);
                    lawParagraphPo.setChapterId(null);
                }
                if (deepPath[3] == 1){
                    lawSubparagraphPo.setSectionId(sectionId);
                    lawParagraphPo.setSectionId(sectionId);
                }else{
                    lawSubparagraphPo.setSectionId(null);
                    lawParagraphPo.setSectionId(null);
                }
                if (deepPath[4] == 1){
                    lawSubparagraphPo.setArticleId(articleId);
                    lawParagraphPo.setArticleId(articleId);
                }else{
                    lawSubparagraphPo.setArticleId(null);
                    lawParagraphPo.setArticleId(null);
                }
                if (deepPath[5] == 1){
                    lawSubparagraphPo.setParagraphId(paragraphId);
                }else if (thisLine.replaceAll("\\p{Z}","").charAt(1) == '一'){
                    StringBuilder paragraphText = new StringBuilder("@Subp@"+thisLine);
                    for (int i=line+1;i<lawTextInLine.length;i++){
                        if (lawTextInLine[i].replaceAll("\\p{Z}","").startsWith("（")){
                            paragraphText.append("@Subp@").append(lawTextInLine[i]);
                        }
                        else{
                            break;
                        }
                    }
                    lawParagraphPo.setParagraphName(paragraphText.toString());
                    lawParagraphPo.setParagraphSeq("第一款");
                    deepPath[5] = 1;
                    paragraphId = UUID.randomUUID().toString();
                    lawParagraphPo.setId(paragraphId);
                    lawParaDAO.insert(lawParagraphPo);
                    lawSubparagraphPo.setParagraphId(paragraphId);
                }
                subparagraphId = UUID.randomUUID().toString();
                lawSubparagraphPo.setId(subparagraphId);
                lawSubparagraphPo.setSubparagraphSeq("第"+thisLine.substring(thisLine.indexOf("（")+1,thisLine.indexOf("）"))+"项");
                lawSubparagraphPo.setSubparagraphName(thisLine.trim());
                subparagraphList.add(thisLine);
                lawSubParaDAO.insert(lawSubparagraphPo);
            }
            else if(checkLineEmpty.checkLineEmptyOrSpace(thisLine) && state.equals("item")){
                deepPath[7] = 1;
                LawItemPo lawItemPo = new LawItemPo();
                lawItemPo.setDocId(docId);
                //可能插入
                LawSubparagraphPo lawSubparagraphPo = new LawSubparagraphPo();
                lawSubparagraphPo.setDocId(docId);
                if (deepPath[1] == 1){
                    lawItemPo.setPartId(partId);
                    lawSubparagraphPo.setPartId(partId);
                }else{
                    lawItemPo.setPartId(null);
                    lawSubparagraphPo.setPartId(null);
                }
                if (deepPath[2] == 1){
                    lawItemPo.setChapterId(chapterId);
                    lawSubparagraphPo.setChapterId(chapterId);
                }else{
                    lawItemPo.setChapterId(null);
                    lawSubparagraphPo.setChapterId(null);
                }
                if (deepPath[3] == 1){
                    lawItemPo.setSectionId(sectionId);
                    lawSubparagraphPo.setSectionId(sectionId);
                }else{
                    lawItemPo.setSectionId(null);
                    lawSubparagraphPo.setSectionId(null);
                }
                if (deepPath[4] == 1){
                    lawItemPo.setArticleId(articleId);
                    lawSubparagraphPo.setArticleId(articleId);
                }else{
                    lawItemPo.setArticleId(null);
                    lawSubparagraphPo.setArticleId(null);
                }
                if (deepPath[5] == 1){
                    lawItemPo.setParagraphId(paragraphId);
                    lawSubparagraphPo.setParagraphId(paragraphId);
                }else{
                    lawItemPo.setParagraphId(null);
                    lawSubparagraphPo.setParagraphId(null);
                }
                if (deepPath[6] == 1){
                    lawItemPo.setSubparagraphId(subparagraphId);
                }
                else if (thisLine.replaceAll("\\p{Z}","").charAt(0) == '1'){
                    StringBuilder subparagraphText = new StringBuilder("@Item@"+thisLine);
                    for (int i=line+1;i<lawTextInLine.length;i++){
                        if (lawTextInLine[i].replaceAll("\\p{Z}","").startsWith("(")){
                            subparagraphText.append("@Item@").append(lawTextInLine[i]);
                        }
                        else{
                            break;
                        }
                    }
                    lawSubparagraphPo.setSubparagraphName(subparagraphText.toString());
                    lawSubparagraphPo.setSubparagraphSeq("第一项");
                    deepPath[6] = 1;
                    subparagraphId = UUID.randomUUID().toString();
                    lawSubparagraphPo.setId(subparagraphId);
                    lawSubParaDAO.insert(lawSubparagraphPo);
                    lawItemPo.setSubparagraphId(subparagraphId);
                }
                itemId = UUID.randomUUID().toString();
                lawItemPo.setId(itemId);
                lawItemPo.setItemSeq("第"+UpperNumber.convertToChinese(Integer.parseInt(String.valueOf(thisLine.charAt(1))))+"目");
                lawItemPo.setItemName(thisLine.trim());
                itemList.add(thisLine);
                lawItemDAO.insert(lawItemPo);
            }
            else if (checkLineEmpty.checkLineEmptyOrSpace(thisLine) && state.equals("appendix")){
                LawAppendixPo lawAppendixPo = new LawAppendixPo();
                lawAppendixPo.setDocId(docId);
                String appendix = thisLine;
                for (int i=line+1;i<lawTextInLine.length;i++){
                    line++;
                    appendix += lawTextInLine[i];
                }
                appendixId = UUID.randomUUID().toString();
                lawAppendixPo.setId(appendixId);
                lawAppendixPo.setAppendixName(appendix);
                lawAppendixPo.setAppendixSeq("附则");
                appendixDAO.insert(lawAppendixPo);
            }
            //update state
            if (line<lawTextInLine.length-1){
                String newState = checkNextLine.checkNextLineState(lawTextInLine[line+1],state);
                if (!newState.equals("unchanged")){
                    state = newState;
                }
            }
        }

        System.out.println("Add A New Law Success");
    }
}
