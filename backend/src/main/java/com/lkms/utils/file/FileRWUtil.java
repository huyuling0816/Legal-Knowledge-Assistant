package com.lkms.utils.file;

import com.lkms.utils.object.StringUtils;
import org.apache.poi.hwpf.HWPFDocument;
import org.apache.poi.hwpf.usermodel.Paragraph;
import org.apache.poi.xwpf.usermodel.*;
import org.springframework.web.multipart.MultipartFile;

import java.io.*;
import java.net.HttpURLConnection;
import java.net.MalformedURLException;
import java.net.URL;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

public class FileRWUtil {
    private static String lineSeparator = System.lineSeparator();

    public static String readURLFile(String url) {
        String fileType = url.substring(url.lastIndexOf(".") + 1);
        String content = null;
        if (fileType.endsWith("txt")) {
            content = readTxt(returnBitMap(url));
        } else if (fileType.endsWith("doc")) {
            content = readDoc(returnBitMap(url));
        } else if (fileType.endsWith("docx")) {
            content = readDocx(returnBitMap(url));
        }
        return content;
    }

    public static String readMultipartFile(MultipartFile file) {
        String fileName = file.getOriginalFilename();
        String content;
        if (fileName.endsWith(".txt")) {
            try {
                content = readTxt(file.getInputStream());
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else {
            try {
                content = readWord(file);
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return content;
    }

    public static String readPathFile(String filePath) {
        String content = null;
        Path path = Paths.get(filePath);
        if (filePath.endsWith(".txt")) {
            try {
                content = readTxt(Files.newInputStream(path));
            } catch (IOException e) {
                throw new RuntimeException(e);
            }
        } else if (filePath.endsWith(".doc")) {
            try {
                content = readDoc(Files.newInputStream(path));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        } else if (filePath.endsWith(".docx")) {
            try {
                content = readDocx(Files.newInputStream(path));
            } catch (Exception e) {
                throw new RuntimeException(e);
            }
        }
        return content;
    }

    public static String readTxt(InputStream is) {
        StringBuilder buffer = new StringBuilder();
        try {
            // 两个输入流，两个reader
            InputStreamReader inCurrReader = new InputStreamReader(is);
            BufferedReader currReader = new BufferedReader(inCurrReader);
            while (currReader.ready()) {
                // 获取当前行
                String currline = currReader.readLine().trim();
                if (!StringUtils.isEmpty(currline)) {
                    buffer.append(currline).append(lineSeparator);
                }
            }
        } catch (Exception e) {
            System.out.println(e.getMessage());
        }
        return buffer.toString();
    }

    public static String readTxt(String path) {
        try {
            return readTxt(new FileInputStream(new File(path)));
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }
    }

    public static String readWord(String filePath) throws Exception {
        if (filePath.endsWith(".doc")) {
            InputStream is = Files.newInputStream(new File(filePath).toPath());
            return readDoc(is);
        } else if (filePath.endsWith(".docx")) {
            InputStream is = Files.newInputStream(new File(filePath).toPath());
            return readDocx(is);
        }
        return null;
    }

    public static String readWord(MultipartFile file) throws Exception {
        String fileName = file.getOriginalFilename();
        assert fileName != null;
        if (fileName.endsWith(".doc")) {
            InputStream is = file.getInputStream();
            return readDoc(is);
        } else if (fileName.endsWith(".docx")) {
            InputStream is = file.getInputStream();
            return readDocx(is);
        }
        return null;
    }

    public static String saveToTxt(String folderPath, String title, String content) {
        String path = folderPath + title + ".txt";
        try {
            File file = new File(path);
            // if file doesnt exists, then create it
            if (!file.exists()) {
                file.createNewFile();
            } else {
                return path;
            }
            FileWriter fw = new FileWriter(file.getAbsoluteFile());
            BufferedWriter bw = new BufferedWriter(fw);
            bw.write(content);
            bw.close();
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static String saveToWord(String folderPath, String title, String content) {
        String path = folderPath + title + ".docx";
        XWPFDocument document = new XWPFDocument();
        String[] paragraphs = content.split("\n");
        XWPFParagraph paragraph;
        XWPFRun run;
        for (String para : paragraphs) {
            paragraph = document.createParagraph();
            run = paragraph.createRun();
            run.setText(para);
        }
        try {
            File file = new File(path);
            if (!file.exists()) {
                file.createNewFile();
                OutputStream outputStream = Files.newOutputStream(Paths.get(path));
                document.write(outputStream);
                outputStream.close();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
        return path;
    }

    public static boolean deleteFile(File file) {
        file.delete();
        return true;
    }

    public static boolean deleteFile(String filePath) {
        boolean flag = false;
        File file = new File(filePath);
        // 判断目录或文件是否存在
        if (!file.exists()) {  // 不存在返回 false
            return flag;
        } else {
            // 判断是否为文件
            if (file.isFile()) {  // 为文件时调用删除文件方法
                return deleteFile(file);
            } else {  // 为目录时调用删除目录方法
                return false;
            }
        }
    }

    private static InputStream returnBitMap(String path) {
        URL url = null;
        InputStream is = null;
        try {
            url = new URL(path);
        } catch (MalformedURLException e) {
            e.printStackTrace();
        }
        try {
            assert url != null;
            HttpURLConnection conn = (HttpURLConnection) url.openConnection();//利用HttpURLConnection对象,我们可以从网络中获取网页数据.
            conn.setDoInput(true);
            conn.connect();
            is = conn.getInputStream();    //得到网络返回的输入流

        } catch (IOException e) {
            e.printStackTrace();
        }
        return is;
    }

    private static String readDoc(InputStream is) {
        HWPFDocument hwpfDocument = null;
        try {
            hwpfDocument = new HWPFDocument(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        String content = hwpfDocument.getDocumentText();
        try {
            hwpfDocument.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        return content;
    }

    public static String readDocx(InputStream is) {
        XWPFDocument xwpfDocument = null;
        try {
            xwpfDocument = new XWPFDocument(is);
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        Iterator<IBodyElement> bodyElementsIterator = xwpfDocument.getBodyElementsIterator();
        List<Object> datas = new ArrayList<>();
        while (bodyElementsIterator.hasNext()) {
            IBodyElement bodyElement = bodyElementsIterator.next();
            String content = handlerByBodyType(bodyElement, bodyElement.getPartType());
            datas.add(content);
        }
        try {
            xwpfDocument.close();
            is.close();
        } catch (IOException e) {
            throw new RuntimeException(e);
        }
        StringBuilder res = new StringBuilder();
        for (Object data : datas) {
            res.append((String) data).append(lineSeparator);
        }
        return res.toString();
    }

    private static String handlerByIBodyElement(IBodyElement bodyElement) {
        String content = null;
        //用于处理XWPFParagraph
        if (bodyElement instanceof XWPFParagraph) {
            content = handlerXWPFParagraphType(bodyElement);
        }
        return content;
    }

    //用于处理当前的XWPFParagraph类型的数据
    private static String handlerXWPFParagraphType(IBodyElement bodyElement) {
        XWPFParagraph xwpfParagraph = (XWPFParagraph) bodyElement;
        BodyElementType elementType = xwpfParagraph.getElementType();
        return getStringByBodyElementType(xwpfParagraph, elementType);
    }

    //通过当前的类型和元素进行相对应的处理
    private static String getStringByBodyElementType(XWPFParagraph xwpfParagraph, BodyElementType bodyElementType) {
        String content = "";
        switch (bodyElementType) {
            case CONTENTCONTROL:
                //如果使用的是文本控件
                break;
            case PARAGRAPH:
                //如果是段落的处理结果
                content = xwpfParagraph.getParagraphText();
                break;
            case TABLE:
                //如果当前的的元素部分为表格
                break;

            default:
                break;
        }
        return content;
    }

    //通过身体类型来处理
    private static String handlerByBodyType(IBodyElement bodyElement, BodyType partType) {
        String content = null;
        switch (partType) {
            case CONTENTCONTROL:
                break;
            case DOCUMENT:
                content = handlerByIBodyElement(bodyElement);
                break;
            case HEADER:

                break;
            case FOOTER:

                break;
            case FOOTNOTE:

                break;
            case TABLECELL:

                break;
            default:
                throw new IllegalArgumentException("there is no this document type !please check this type!");
        }
        return content;
    }

    public static void main(String[] args) {
        saveToWord("src/main/resources/temp/", "test", "\n" +
                "安徽省公路安全保护条例\n" +
                "\n" +
                "（2018年7月27日安徽省第十三届人民代表大会\n" +
                "常务委员会第四次会议通过）\n" +
                "\n" +
                "目    录\n" +
                "\n" +
                "第一章  总    则\n" +
                "第二章  公路线路保护\n" +
                "第三章  公路养护\n" +
                "第四章  公路通行\n" +
                "第五章　监督管理\n" +
                "第六章  法律责任\n" +
                "第七章  附　　则\n" +
                "\n" +
                "第一章  总    则\n" +
                "\n" +
                "第一条　为了加强公路安全保护，保障公路完好、安全和畅通，根据《中华人民共和国公路法》、国务院《公路安全保护条例》和其他有关法律、行政法规，结合本省实际，制定本条例。\n" +
                "第二条  本条例适用于本省行政区域内的公路安全保护活动。\n" +
                "本条例所称公路，按其在公路路网中的地位分为国道、省道、县道、乡道和村道，按技术等级分为高速公路、一级公路、二级公路、三级公路和四级公路。公路包括公路桥梁、公路隧道和公路渡口。\n" +
                "村道的安全保护活动，适用《安徽省农村公路条例》和本条例的有关专门规定。\n" +
                "第三条  县级以上人民政府应当加强对公路安全保护工作的领导，将公路安全保护纳入国民经济和社会发展规划，将政府及其有关部门从事公路管理、养护所需经费，以及行使公路行政管理职能所需经费，纳入本级人民政府财政预算。专用公路的公路安全保护经费按照国家有关规定执行。\n" +
                "第四条  县级以上人民政府交通运输主管部门按照职责负责本行政区域内公路安全保护工作，其所属的交通运输综合执法机构具体负责公路行政执法工作，公路公益服务机构具体负责除经营性收费公路以外的公路养护和通行保障等工作。\n" +
                "县级以上人民政府公安部门按照职责负责本行政区域内公路交通安全管理以及与公路安全保护相关的治安管理工作；其他有关部门按照职责分工，做好公路安全保护的相关工作。\n" +
                "第五条  乡镇人民政府、街道办事处根据法律法规规定和县级人民政府确定的职责，做好乡道、村道的安全保护工作。\n" +
                "第六条  各级人民政府应当组织开展公路安全保护的宣传教育工作，加大公益宣传力度，发挥行业协会、村（居）民委员会、社区、学校等宣传作用，提高公民的守法意识、安全意识和公德意识。\n" +
                "对公路安全保护工作做出突出贡献的单位和个人，县级以上人民政府按照规定给予表彰奖励。\n" +
                "\n" +
                "第二章   公路线路保护\n" +
                "\n" +
                "第七条  县级以上人民政府应当根据公路规划的规定，对公路、公路用地、公路建筑控制区依法进行规划控制。\n" +
                "第八条  公路初步设计批准部门应当将公路初步设计文件等资料，及时抄送公路沿线县级以上人民政府及其相关管理部门。\n" +
                "第九条  新建、改建、扩建公路确需穿越集镇、村庄的，应当同步建设排水设施，防止环境污染和水土流失。\n" +
                "第十条  县级以上人民政府应当确定公路两侧边沟（截水沟、坡脚护坡道）外缘起不少于一米的公路用地；公路两侧无边沟的，应当确定自路缘石或者坡脚线外缘起不少于一米的公路用地。\n" +
                "第十一条  县级以上人民政府应当根据保障公路运行安全、节约用地和保护环境的原则以及公路发展的需要，组织交通运输、国土资源等主管部门依法划定公路建筑控制区的范围。\n" +
                "新建、改建公路的建筑控制区的范围，自公路初步设计批准之日起三十日内划定并公告；已经建成但尚未划定建筑控制区的公路，自本条例施行之日起一年内划定。\n" +
                "公路建筑控制区与铁路线路安全保护区、航道保护范围、河道管理范围或者水工程管理和保护范围重叠的，按照国务院《公路安全保护条例》的规定协商划定。\n" +
                "第十二条  县级以上人民政府交通运输主管部门应当在依法划定的公路两侧建筑控制区外缘设置标桩、界桩。\n" +
                "第十三条  临近公路规划和新建村镇、开发区以及农贸市场等，应当与公路保持规定的距离，并在公路一侧与公路垂直布局，避免在公路两侧对应建设。\n" +
                "第十四条  城乡规划主管部门审批临近公路建筑控制区的建设项目，应当按照规定明确建筑物与公路的控制距离。\n" +
                "第十五条  公路、公路用地和公路建筑控制区范围内设置的照明、通信、标志、管线、信号灯等设施，以及在公路和公路两侧种植的绿化物，不得遮挡公路标志，不得妨碍安全视距。\n" +
                "前款规定的设施和绿化物，其所有权人或者管理人应当加强管理和维护，影响公路通行的，应当及时修复、处理；影响交通安全的，应当及时报告公安交通管理部门。\n" +
                "第十六条  在公路用地范围内设置非公路标志的，应当向县级以上人民政府交通运输主管部门申请，并提供申请书、设计图纸和施工方案。申请书的内容包括设置理由，标志的颜色、外廓尺寸和结构，设置地点，设置时间以及保持期限。\n" +
                "县级以上人民政府交通运输主管部门应当自收到申请之日起二十日内作出许可决定。在城市规划区公路用地范围内批准设置非公路标志的，县级以上人民政府交通运输主管部门应当征求城市管理主管部门的意见。\n" +
                "第十七条  非公路标志设置人应当加强非公路标志的管理，对破损、污浊的非公路标志，应当及时修复、清洗或者更换；对存在安全隐患的非公路标志，应当及时修缮加固或者拆除。\n" +
                "经批准设立的立柱式非公路标志，其设置人应当定期对结构物进行安全检查，并将检查结果抄送许可机关。\n" +
                "第十八条  高速公路用地范围内广告标牌设施的设置，应当符合省人民政府交通运输主管部门的规划。\n" +
                "第十九条  公路两侧边沟应当保持畅通。确需占用的，应当报县级以上人民政府交通运输主管部门批准，并按照公路工程技术标准重建排水设施；对公路造成损坏的，应当依法承担赔偿责任。\n" +
                "第二十条  禁止破坏公路、公路用地范围内的绿化物。需要更新砍伐护路林的，应当符合下列条件，并经县级以上人民政府交通运输主管部门批准：\n" +
                "（一）书面说明砍伐理由、砍伐树木的位置、种类和蓄积量；\n" +
                "（二）补种方案符合公路绿化工程技术标准；\n" +
                "（三）作业方案符合保障公路安全、畅通要求。\n" +
                "第二十一条  造成公路损坏的，责任者应当及时报告交通运输综合执法机构或者公路经营企业，并接受交通运输综合执法机构现场调查处理。因交通事故造成公路损坏的，公安交通管理部门应当及时通知交通运输综合执法机构查验路产损坏情况。\n" +
                "第二十二条  公路需要调整为城市道路的，应当由所在地的市、县人民政府提出调整意见，按照国家和省有关公路规划调整、修改的规定办理。\n" +
                "公路调整为城市道路的，应当自调整之日起三十日内办理交接手续，由市、县人民政府指定有关部门接收并履行管理和养护职责。\n" +
                "因公路新建、改建，原有干线公路或者部分路段服务功能降低，不再承担主要通行功能，但是仍需要继续使用的，县级以上人民政府交通运输主管部门应当及时向本级人民政府提出调整意见，经有权机关同意后，由所在地市、县人民政府指定有关部门接收并履行管理和养护职责。\n" +
                "第二十三条  公路永久性停止使用，经规定程序核准作报废处理的，县级以上人民政府交通运输主管部门应当向社会公告，并设置必要的警示标志或者隔离设施。\n" +
                "公路报废后的土地使用管理依照有关土地管理的法律、行政法规执行。\n" +
                "第二十四条  公路工程建设单位应当按照国家和省有关档案管理的规定，及时收集、整理公路建设项目各环节的文件资料，建立公路建设项目档案，并在公路建设工程竣工验收合格之日起三个月内，向县级以上人民政府交通运输主管部门移交档案资料。\n" +
                "\n" +
                "第三章   公路养护\n" +
                "\n" +
                "第二十五条  国道、省道、县道的养护由县级以上人民政府交通运输主管部门按照职责分级负责；乡道、村道的养护由乡镇人民政府负责；依法受让公路收费权或者投资建成经营的公路的养护由公路经营企业负责。\n" +
                "第二十六条  公路养护分为日常养护和养护工程，养护工程分为预防养护、修复养护、专项养护和应急养护。\n" +
                "组织实施养护工程，应当依照有关法律、法规、规章的规定，通过招标投标、政府采购等方式选择具有相应技术能力和资质条件的单位承担。应急养护，可以根据应急处置工作需要，直接委托具备相应能力的专业队伍实施。\n" +
                "第二十七条  公路公益服务机构、公路经营企业应当按照规定对公路进行巡查，并制作巡查记录；发现公路坍塌、坑槽、隆起等损毁的，应当及时设置警示标志，并采取措施修复；危及交通安全的，应当及时通知公安交通管理部门。\n" +
                "公安交通管理部门发现公路坍塌、坑槽、隆起等损毁，危及交通安全的，应当及时采取措施，疏导交通，并通知公路公益服务机构或者公路经营企业。\n" +
                "其他人员发现公路坍塌、坑槽、隆起等损毁的，应当及时向公路公益服务机构、公安交通管理部门报告。\n" +
                "第二十八条  公路公益服务机构、公路经营企业应当按照公路里程、技术状况、养护规范等，编制公路养护计划，抄送县级以上人民政府交通运输主管部门。\n" +
                "县级以上人民政府交通运输主管部门按照职责统筹安排公路养护计划，避免集中进行公路养护作业造成交通堵塞。\n" +
                "第二十九条  公路养护作业应当根据公路技术等级、交通流量、地形地貌和对公路通行的影响程度等因素，编制养护作业方案和应急预案。\n" +
                "公路养护作业需要封闭公路的，或者占用半幅公路进行作业，作业路段长度在两公里以上，并且作业期限超过三十日的，除紧急情况外，养护作业单位应当在作业开始之日前五日向社会公告施工路段、施工时间、绕行路线等信息，并在距离施工作业地点来车方向安全距离处及绕行处设置公告牌；不能绕行的，应当修建临时道路。\n" +
                "县级以上人民政府交通运输主管部门应当通过广播、电视、报纸、网络、电子显示屏等方式提前向社会发布前款规定的公路养护作业信息；交通运输综合执法机构应当定期巡查，督促养护作业单位落实养护作业方案和应急预案。\n" +
                "第三十条  公路养护作业应当按照技术规范和操作规程实施，设置明显的施工标志、安全标志和相应的防护设施。完工后，应当及时清除公路上的障碍物。\n" +
                "交通流量较大的特殊时段，或者发生公路突发事件影响通行的，公路公益服务机构、公路经营企业应当按照设区的市以上人民政府交通运输主管部门下达的路网调度指令，调整或者暂停养护作业；养护作业可能造成大面积拥堵以及其他影响交通安全的，应当及时通知公安交通管理部门。\n" +
                "第三十一条  公路公益服务机构、公路经营企业应当定期对公路、公路桥梁、公路隧道进行检测和评定。经检测达不到原设定标准的，应当设置明显的限载、绕行标志，并采取维修和加固等措施；经检测发现影响通行安全的，应当先行设置禁行、绕行或者限行标志，向社会发布公告，及时采取修复措施，并通知公安交通管理部门。\n" +
                "第三十二条  公路公益服务机构、公路经营企业、养护施工单位应当建立健全养护工程质量检查管理制度，通过抽查、委托专业机构检查、自查等方式确保养护工程质量。\n" +
                "第三十三条  公路公益服务机构和公路经营企业应当加强交通安全设施的管理和维护，确保交通安全设施符合有关技术标准和规范。\n" +
                "公安交通管理部门发现已经投入使用的公路存在交通安全隐患，需要对公路限速进行调整的，或者配套设施存在交通安全隐患，需要完善安全设施的，应当向当地人民政府提出书面建议，当地人民政府应当及时作出处理决定。县级以上人民政府交通运输主管部门接到处理决定后，应当按照公路工程技术标准进行排查和处置。\n" +
                "第三十四条  县级人民政府应当按照“四好农村路”建设要求，建立健全符合本地实际的农村公路养护机制，完善农村公路安全防护设施，加强农村公路的管理和养护。\n" +
                "乡镇人民政府、街道办事处应当发挥村（居）民委员会和村（居）民对农村公路养护的作用，可以采取建立群众性、专业性养护组织或者个人分段承包等方式，对乡道、村道实施日常养护。\n" +
                "\n" +
                "第四章  公路通行\n" +
                "\n" +
                "第三十五条  车辆载运不可解体物品，车货总体的外廓尺寸或者总质量超过公路、公路桥梁、公路隧道的限载、限高、限宽、限长标准，确需在公路、公路桥梁、公路隧道行驶的，从事运输的单位和个人应当向县级以上人民政府交通运输主管部门申请公路超限运输许可。\n" +
                "县级以上人民政府交通运输主管部门审批公路超限运输申请时，需要验算通行线路上桥梁承载能力的，应当编制荷载验算报告。\n" +
                "第三十六条  公路突发事件的应急管理应当纳入县级以上人民政府突发事件应急管理体系。县级以上人民政府交通运输主管部门依据有关规定编制公路突发事件应急预案，报本级人民政府批准后组织实施。其他有关部门按照应急预案的规定做好公路突发事件应急处置的相关工作。\n" +
                "公路公益服务机构、公路经营企业应当组建应急队伍，定期组织应急培训和演练，储备必要的应急救援物资。\n" +
                "第三十七条  县级以上人民政府应当组织建立公路突发事件影响的预测、预警体系，建立预警联系人常备通讯录及信息库、风险源数据库、公路突发事件影响的预测评估系统。\n" +
                "县级以上人民政府交通运输主管部门应当联合相关应急协作部门，建立长效预测、预警机制。\n" +
                "第三十八条  县级以上人民政府交通运输主管部门应当会同气象、国土资源、水行政、公安等管理部门，建立公路沿线大风、暴雨、浓雾、团雾、冰雪等恶劣天气和地质、洪涝灾害的信息互通与应急联动工作机制，加强监测、预报和预警服务工作。\n" +
                "遇恶劣天气影响车辆正常行驶的，公路公益服务机构、公路经营企业应当及时发布安全提示信息，提前做好车辆分流等工作。\n" +
                "第三十九条  发生公路突发事件影响通行的，公路公益服务机构、公路经营企业应当及时修复公路、恢复通行。设区的市以上人民政府交通运输主管部门应当根据修复公路、恢复通行的需要，及时调集抢修力量，统筹安排有关作业计划，下达路网调度指令，配合有关部门组织绕行、分流。\n" +
                "设区的市以上人民政府交通运输主管部门应当收集、汇总公路损毁、公路交通流量等信息，开展公路突发事件的监测、预报和预警工作，并利用多种方式及时向社会发布有关公路运行信息。\n" +
                "第四十条  故障车辆需要救援的，由当事人自行选择施救单位；影响交通安全需要立即清除，当事人不能清除的，公安交通管理部门可以决定立即实施代履行。\n" +
                "公路清障救援车辆应当安装标志灯具并喷涂明显的标志。执行清障救援任务时，应当开启标志灯具和危险报警闪光灯，并设置必要的安全警戒区。\n" +
                "第四十一条　公路公益服务机构、公路经营企业应当加强公路服务设施建设，保持服务设施的完好。公路沿线的服务区和加油站应当设立免费使用的清洁的卫生设施。\n" +
                "高速公路服务区应当为公路提供停车、如厕、饮水等免费服务，以及餐饮和汽车加油、充电或者加气、维修等经营性服务。因故无法提供服务的，应当提前发布信息。\n" +
                "\n" +
                "第五章  监督管理\n" +
                "\n" +
                "第四十二条  交通运输综合执法机构依法对公路、公路用地、建筑控制区、收费站点、服务区、车辆停放场所，以及公路公益服务机构、公路经营企业、车辆所属单位进行监督检查。\n" +
                "交通运输综合执法机构监督检查时，可以向有关单位、个人了解情况，查阅、复制有关资料；有关单位和个人应当配合，不得拒绝、阻碍。\n" +
                "第四十三条  交通运输综合执法机构应当开展公路巡查。巡查时，应当将巡查情况和处理结果予以记录，由巡查人员签字后归档。\n" +
                "第四十四条  县级以上人民政府交通运输主管部门及其交通运输综合执法机构，应当加强执法队伍建设，配备必要的执法人员、执法装备。\n" +
                "交通运输综合执法人员应当具备相应的专业知识和业务能力，按照规定取得行政执法证件后，方可上岗执法。\n" +
                "交通运输综合执法专用车辆，应当设置统一标志和示警灯。\n" +
                "第四十五条  农村公路实行县、乡、村三级路长制。各级路长按照职责协调解决农村公路管理中的重点难点问题，加大农村公路联合执法监管力度，排查整改交通安全隐患，加强农村公路灾毁应急保障工作，提升农村公路管理服务水平。\n" +
                "第四十六条  任何单位和个人都有爱护公路、公路用地及公路附属设施的义务，有权检举和控告破坏、损坏公路、公路用地、公路附属设施和影响公路安全的行为。\n" +
                "县级以上人民政府交通运输主管部门应当完善公路安全保护举报制度，公开举报电话、通信地址或者电子邮件信箱，依法调查处理举报事项，将调查处理结果向举报人反馈，并为其保密。\n" +
                "\n" +
                "第六章  法律责任\n" +
                "\n" +
                "第四十七条  各级人民政府、县级以上人民政府交通运输主管部门、交通运输综合执法机构及其工作人员有下列情形之一的，由有权机关对负有直接责任的主管人员和其他直接责任人员依法给予处分：\n" +
                "（一）不按照规定划定公路建筑控制区，影响公路通行安全的；\n" +
                "（二）违法实施行政许可的；\n" +
                "（三）违反规定拦截、检查正常行驶的车辆的；\n" +
                "（四）违反规定设站卡、罚款的；\n" +
                "（五）违法扣留车辆、工具或者使用依法扣留的车辆、工具的； \n" +
                "（六）刁难或者勒索司乘人员的；\n" +
                "（七）不依法及时处理公路突发事件的；\n" +
                "（八）其他玩忽职守、徇私舞弊、滥用职权的行为。\n" +
                "第四十八条  违反本条例第十六条规定，擅自设置非公路标志的，由交通运输综合执法机构责令限期拆除，可以处五千元以上两万元以下罚款；逾期不拆除的，由交通运输综合执法机构拆除，有关费用由设置人负担。\n" +
                "第四十九条  违反本条例第十九条规定，未经批准占用边沟，未按照公路工程技术标准重建排水设施或者重建排水设施不符合公路工程技术标准的，交通运输综合执法机构应当责令改正，处以五千元以上三万元以下罚款。\n" +
                "第五十条  对经动态检测技术监控设备检测显示超限的货运车辆，以逃逸等方式拒绝称重检测的，违法行为地或者车籍所在地交通运输综合执法机构应当责令货运车辆所有权人在一个月内到指定地点接受处理。逾期不接受处理的，交通运输综合执法机构或者公安交通管理部门可以根据有关技术监控记录资料依法对超限运输车辆实施处罚。\n" +
                "第五十一条  违反本条例规定的行为，有关法律、行政法规已有行政处罚规定的，适用其规定；构成犯罪的，依法追究刑事责任。\n" +
                "\n" +
                "第七章  附    则\n" +
                "\n" +
                "第五十二条  本条例自2019年5月1日起施行。2000年12月28日安徽省第九届人民代表大会常务委员会第二十次会议通过的《安徽省公路路政管理条例》同时废止。\n");
    }
}