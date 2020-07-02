package com.chengqj.springbootsenior.tcp.util;

import org.dom4j.*;
import org.dom4j.io.SAXReader;

import java.util.Objects;

/**
 * @Author chengqiujing
 * @Date 2020/6/9 15:37
 * @Desc
 */
public class XmlUtil {

    private static final SAXReader reader = new SAXReader();

    public static synchronized String getTextByElement(String xml,String xpath) throws DocumentException {
        if(Objects.isNull(xml)){
            throw new RuntimeException("要解析的xml文本为空");
        }
        if (Objects.isNull(xpath)) {
            throw new RuntimeException("取text的elementName为空");
        }
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        Node node = root.selectSingleNode(xpath);
        if(node != null){
            return node.getText();
        }else {
            return null;
        }
    }


//    public static void main(String[] args) {
//
//        BigDecimal bigDecimal = new BigDecimal("2.6848435E+4");  //
//
//        System.out.println(bigDecimal.toPlainString());
//    }

}
