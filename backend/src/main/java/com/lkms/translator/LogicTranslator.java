package com.lkms.translator;

import com.lkms.mapper.LogicMapper;
import com.lkms.po.LogicPo;
import lombok.NoArgsConstructor;
import org.springframework.stereotype.Component;

import java.util.UUID;

@NoArgsConstructor
@Component
public class LogicTranslator {

    public LogicMapper logicDAO;

    public LogicTranslator(LogicMapper logicDAO){
        this.logicDAO = logicDAO;
    }

    String[] BriefSituation = {"对积极参加的","对其他参加的","情节严重的","情节较轻的","情节特别严重的","造成严重后果的","造成特别严重后果的",
            "过失犯前款罪的","情节特别恶劣的","后果特别严重的","数额巨大的","数额特别巨大的","造成重大损失的","造成特别重大损失的","数额巨大或者有其他严重情节的",
            "数额特别巨大或者有其他特别严重情节的",};
    //推荐实现一个Service根据ArticleId查找Subparagraph
    String ABD = "有下列";
    //推荐实现一个Service根据ArticleId查找Article
    String proceedingSituation = "前款";
    String proceedingText = "";

    String negative = "不得";
    public void getLogic(String text,String[] logicFKIdList,String fullText){
        //拆解“有下列”
        if (fullText.contains("（")){
            fullText = "（" + fullText.split("（",2)[1].replaceAll("@Subp@","");
        }
        if (fullText.contains("@Para@")){
            fullText = fullText.split("@Para")[0];
        }
        //第一种情况：xxx，处xxx
        if (text.contains("，处")){
            //xxx，处xxx；xxx，处xxx
            //情节较轻、较重等找前文对照
            if (text.contains("；")){
                String splitStr = "，处";
                handleSemi(text, logicFKIdList,splitStr,fullText);
            }
            else{
                insertAntAndConsByPunish(text.split("，处"),logicFKIdList,fullText);
            }
        }
        //第二种情况：xxx的，xxx
        else if (text.contains("的，")){
            if (text.contains("；")){
                String splitStr = "的，";
                handleSemi(text, logicFKIdList,splitStr,fullText);
            }
            else{
                insertAntAndConsByPunish(text.split("的，"),logicFKIdList,fullText);
            }
        }
        //第三种情况：否定
        else if (text.contains(negative)){
            String[] neg = text.split(negative);

            LogicPo logicPo1 = new LogicPo();
            logicPo1.setBody(checkAll(neg[0],ABD));
            logicPo1.setConsequence(checkAll(negative+neg[1],ABD));
            logicPo1.setId(UUID.randomUUID().toString());
            setForeignKey(logicPo1,logicFKIdList);
            logicDAO.insert(logicPo1);

//            LogicPo logicPo2 = new LogicPo();
//            logicPo2.setBody(checkAll(negative+neg[1],ABD));
//            logicPo2.setAntecedent(checkAll(neg[0],ABD));
//            logicPo2.setId(UUID.randomUUID().toString());
//            setForeignKey(logicPo2,logicFKIdList);
//            logicDAO.insert(logicPo2);
        }
        proceedingText = fullText;
    }

    public void handleSemi(String text, String[] logicFKIdList, String splitStr,String ABD) {
        String context = null;
        String[] sentences = text.split("；");
        for (int i=0; i<sentences.length; i++){
            if (sentences.length!=2){
                continue;
            }
            if (i==0){
                context = sentences[i].split(splitStr)[0];
                insertAntAndConsByPunish(sentences[i].split(splitStr),logicFKIdList,ABD);
            }
            if (i==1){
                insertAntAndConsByPunishWithExtraAnt(sentences[i].split(splitStr),context,logicFKIdList,ABD);
            }
        }
    }

    public void insertAntAndConsByPunish(String[] sentence, String[] logicFKList,String ABD){
        if (sentence.length!=2){
            return;
        }
        //已知本体设置后件
        LogicPo logicPo1 = new LogicPo();
        logicPo1.setBody(checkAll(sentence[0],ABD));
        logicPo1.setConsequence(checkAll(sentence[1],ABD));
        logicPo1.setId(UUID.randomUUID().toString());
        setForeignKey(logicPo1,logicFKList);
        logicDAO.insert(logicPo1);
        //已知本体设置前件
//        LogicPo logicPo2 = new LogicPo();
//        logicPo2.setBody(checkAll(sentence[1],ABD));
//        logicPo2.setAntecedent(checkAll(sentence[0],ABD));
//        logicPo2.setId(UUID.randomUUID().toString());
//        setForeignKey(logicPo2,logicFKList);
//        logicDAO.insert(logicPo2);
    }

    public void insertAntAndConsByPunishWithExtraAnt(String[] sentence, String context, String[] logicFKList,String ABD){
        if (sentence.length!=2){
            return;
        }
        //已知本体设置后件
        LogicPo logicPo1 = new LogicPo();
        logicPo1.setBody(checkAll(context,ABD)+"，"+checkAll(sentence[0],ABD));
        logicPo1.setAntecedent(checkAll(context,ABD));
        logicPo1.setConsequence(checkAll(sentence[1],ABD));
        logicPo1.setId(UUID.randomUUID().toString());
        setForeignKey(logicPo1,logicFKList);
        logicDAO.insert(logicPo1);
        //已知本体设置前件
//        LogicPo logicPo2 = new LogicPo();
//        logicPo2.setBody(checkAll(sentence[1],ABD));
//        logicPo2.setAntecedent(checkAll(context+sentence[0],ABD));
//        logicPo2.setId(UUID.randomUUID().toString());
//        setForeignKey(logicPo2,logicFKList);
//        logicDAO.insert(logicPo2);
    }

    private String checkABD(String text, String ABD){
        if (text.contains(this.ABD)){
            return  text+":"+ABD;
        }
        return text;
    }

    private String checkProceedParagraph(String text){
        if (text.contains(proceedingSituation)){
            return text + "（前款为：" + proceedingText + "）";
        }
        return text;
    }

    private String checkAll(String text,String ABD){
        text = checkABD(text,ABD);
        text = checkProceedParagraph(text);
        return text;
    }

    public void setForeignKey(LogicPo logicPo,String[] logicFKList){
        if (logicFKList[0] != null){
            logicPo.setDocId(logicFKList[0]);
        }else{
            logicPo.setDocId(null);
        }
        if (logicFKList[1] != null){
            logicPo.setPartId(logicFKList[1]);
        }else{
            logicPo.setPartId(null);
        }
        if (logicFKList[2] != null){
            logicPo.setChapterId(logicFKList[2]);
        }
        else{
            logicPo.setChapterId(null);
        }
        if (logicFKList[3] != null){
            logicPo.setSectionId(logicFKList[3]);
        }
        else{
            logicPo.setSectionId(null);
        }
        if (logicFKList[4] != null){
            logicPo.setArticleId(logicFKList[4]);
        }
        else {{
            logicPo.setArticleId(null);
        }}
    }
}
