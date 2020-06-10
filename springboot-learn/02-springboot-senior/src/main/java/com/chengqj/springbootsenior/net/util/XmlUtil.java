package com.chengqj.springbootsenior.net.util;

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

    public static synchronized String getTextByElement(String xml,String elementName) throws DocumentException {
        if(Objects.isNull(xml)){
            throw new RuntimeException("要解析的xml文本为空");
        }
        if (Objects.isNull(elementName)) {
            throw new RuntimeException("取text的elementName为空");
        }
        Document document = DocumentHelper.parseText(xml);
        Element root = document.getRootElement();
        String textTrim = root.elementTextTrim(elementName);
        return textTrim;
    }

}
