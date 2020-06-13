package com.chengqj.springbootsenior.net.test;

import org.dom4j.*;

/**
 * @Author chengqiujing
 * @Date 2020/6/10 10:47
 * @Desc
 */
public class Test {
    public static void main(String[] args) {
//        int a = 0x3D47;
//        System.out.println(a);

//        int[] a = {1,2,3,4,5};
//        int[] b =new  int[10];
//        System.arraycopy(a,0,b,3,a.length);
//        System.out.println(Arrays.toString(b));

//        int a = 1313;
//        byte[] bytes = new byte[4];
//        bytes[0] = (byte) (a >>> 24);
//        bytes[1] = (byte) (a >>> 16);
//        bytes[2] = (byte) (a >>> 8);
//        bytes[3] = (byte) a;
//        int a1 = bytes[0];
//        int a2 = bytes[1];
//        int a3 = bytes[2];
//        int a4 = bytes[3];
//        System.out.println(a1 + "-"+a2+ "-"+a3+ "-"+a4);
//        System.out.println((a1 << 24) + "-"+ (a2 << 16)+ "-" + (a3 << 8)+ "-" + a4);
//        int m = (a1 << 24) + (a2 << 16) + (a3 << 8)+a4;
//        System.out.println(m);

//        System.out.println(0x000000e4);
        String xml = "<?xml version=\"1.0\" encoding=\"utf-8\" ?>\n" +
                "<root>\n" +
                "<common>\n" +
                "<building_id>GW123</building_id>\n" +
                "<gateway_id>GW001</gateway_id>\n" +
                "<type>sequence</type>\n" +
                "</common>\n" +
                "<id_validate operation = \"sequence\">\n" +
                "<sequence>F8C7F276475510AFC09BA5E84F0E7674</sequence>\n" +
                "</id_validate>\n" +
                "</root>";
        try {

            Document document = DocumentHelper.parseText(xml);
            Element root = document.getRootElement();
            Node node = root.selectSingleNode("/root/id_validate/sequence");
            System.out.println(node.getText());
//            String sequence = XmlUtil.getTextByElement(xml, "/sequence");
        } catch (DocumentException e) {
            e.printStackTrace();
        }


    }

    public static String byte2String(byte[] data) {
        char[] hexArray = "0123456789ABCDEF".toCharArray();
        char[] hexChars = new char[data.length * 2];

        for (int j = 0; j < data.length; j++) {
            int v = data[j] & 0xFF;
            hexChars[j * 2] = hexArray[v >>> 4];
            hexChars[j * 2 + 1] = hexArray[v & 0x0F];
        }

        String result = new String(hexChars);
        result = result.replace(" ", "");
        return result;
    }
}
