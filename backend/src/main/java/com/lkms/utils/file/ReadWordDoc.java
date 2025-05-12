package com.lkms.utils.file;

import lombok.NoArgsConstructor;
import org.apache.poi.hwpf.extractor.WordExtractor;
import org.apache.poi.ooxml.POIXMLDocument;
import org.apache.poi.ooxml.POIXMLProperties;
import org.apache.poi.ooxml.extractor.POIXMLTextExtractor;
import org.apache.poi.openxml4j.exceptions.OpenXML4JException;
import org.apache.poi.openxml4j.opc.OPCPackage;
import org.apache.poi.xwpf.extractor.XWPFWordExtractor;
import org.apache.poi.xwpf.usermodel.XWPFDocument;
import org.apache.xmlbeans.XmlException;

import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.Date;

@NoArgsConstructor
public class ReadWordDoc {

    public static void main(String[] args) throws Exception {
        String wordPath = "C:\\Users\\LENOVO\\Desktop\\国家法律法规数据库\\地方性法规\\七台河市人民代表大会及其常务委员会立法条例.doc";
        ReadWordDoc readWordDoc = new ReadWordDoc();
        String output = readWordDoc.readTextFromDoc(wordPath);
        String[] tmp = output.split("\\R");
    }

    /**
     * 通过XWPFWordExtractor访问XWPFDocument的内容
     * @throws Exception
     */
    public void readByExtractor(String path) throws Exception {
        InputStream is = Files.newInputStream(Paths.get(path));
        XWPFDocument doc = new XWPFDocument(is);
        XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
        String text = extractor.getText();
        System.out.println(text);
        /**
         * 打印文档信息
         */
        POIXMLProperties.CoreProperties coreProps = extractor.getCoreProperties();
        this.printCoreProperties(coreProps);
        this.close(is);
    }

    public String readTextFromDocx(String path) throws Exception{
        try{
            InputStream is = Files.newInputStream(Paths.get(path));
            XWPFDocument doc = new XWPFDocument(is);
            XWPFWordExtractor extractor = new XWPFWordExtractor(doc);
            String text = extractor.getText();
            /**
             * 打印文档信息
             */
            POIXMLProperties.CoreProperties coreProps = extractor.getCoreProperties();
            this.printCoreProperties(coreProps);
            this.close(is);
            return text;
        }catch (Exception e){
            System.out.println("文件出错："+path);
            throw(e);
        }
    }


    public String readTextFromDoc(String path) throws OpenXML4JException, XmlException, IOException {
         String buffer = "";
         try {
                 if (path.endsWith(".doc")) {
                         InputStream is = new FileInputStream(new File(path));
                         WordExtractor ex = new WordExtractor(is);
                         buffer = ex.getText();
                         ex.close();
                     } else if (path.endsWith("docx")) {
                         OPCPackage opcPackage = POIXMLDocument.openPackage(path);
                         POIXMLTextExtractor extractor = new XWPFWordExtractor(opcPackage);
                         buffer = extractor.getText();
                         extractor.close();
                     } else {
                         System.out.println("此文件不是word文件！");
                     }

             } catch (Exception | NoClassDefFoundError e) {
                 e.printStackTrace();
                 throw(e);
             }

        return buffer;
     }

    /**
     * 输出CoreProperties信息
     * @param coreProps
     */
    private void printCoreProperties(POIXMLProperties.CoreProperties coreProps) {
        String category = coreProps.getCategory(); //分类
        String creator = coreProps.getCreator(); //创建者,Microsoft Office User
        Date createdDate = coreProps.getCreated(); //创建时间
        String title = coreProps.getTitle(); //标题
        String description = coreProps.getDescription(); //描述，默认为null
    }

    /**
     * 关闭输入流
     * @param is
     */
    private void close(InputStream is) {
        if (is != null) {
            try {
                is.close();
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
    }

}