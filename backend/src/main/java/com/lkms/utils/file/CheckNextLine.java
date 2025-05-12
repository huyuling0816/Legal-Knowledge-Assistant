package com.lkms.utils.file;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

public class CheckNextLine {
    public String checkNextLineState(String nextLine, String state){
        String regex = "第[一二三四五六七八九十百千]+";
        String regexSubparagraph = "（[一二三四五六七八九十百千万亿]+）";
        String regexItem = "（\\d+）";
        //匹配编、章、节、条
        Pattern pattern = Pattern.compile(regex);
        Matcher matcher = pattern.matcher(nextLine);
        nextLine = nextLine.replaceAll("\\p{Z}"," ");
        nextLine = nextLine.trim();
        if (matcher.find() && nextLine.contains(" ")){
            int spaceIndex = nextLine.indexOf(" ");
            String regexSpace = "\\p{Z}";
            Pattern patternSpace = Pattern.compile(regexSpace);
            Matcher matcherSpace = patternSpace.matcher(nextLine);
            if (matcherSpace.find()){
                spaceIndex = matcherSpace.start();
            }
            char keyChar = nextLine.charAt(spaceIndex-1);
            if (keyChar == '编'){
                return "part";
            }
            else if(keyChar == '章'){
                return "chapter";
            }
            else if(keyChar == '节'){
                return "section";
            }
            else if (keyChar == '条'){
                return "article";
            }
        }

        //匹配项
        Pattern patternSubparagraph = Pattern.compile(regexSubparagraph);
        Matcher matcherSubparagraph = patternSubparagraph.matcher(nextLine);
        if (matcherSubparagraph.find()){
            return "subparagraph";
        }

        //匹配目
        Pattern patternItem = Pattern.compile(regexItem);
        Matcher matcherItem = patternItem.matcher(nextLine);
        if (matcherItem.find()){
            return "item";
        }

        if (nextLine.replaceAll(" ","").equals("附则")){
            return "appendix";
        }


        //匹配款
        if (state.equals("article")){
            return "paragraph";
        }

        return "paragraph";
    }
}
