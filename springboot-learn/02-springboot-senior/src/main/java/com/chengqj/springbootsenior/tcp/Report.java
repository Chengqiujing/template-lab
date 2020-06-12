package com.chengqj.springbootsenior.tcp;

import com.chengqj.springbootsenior.tcp.util.XmlUtil;
import lombok.Data;
import org.dom4j.DocumentException;

/**
 * @Author chengqiujing
 * @Date 2020/6/11 17:34
 * @Desc
 */
@Data
public class Report {

    private String text;


    public String getContentByPath(String elementName){
        try {
            return XmlUtil.getTextByElement(text,elementName);
        } catch (DocumentException e) {
            throw new RuntimeException("无法取到该元素："+elementName);
        }
    }
}