package com.chengqj.springbootsenior.tcp.util;


/**
 * @Author chengqiujing
 * @Date 2020/6/9 16:15
 * @Desc
 */
public class ReportUtil {

    private static String getReport(String buildingNo, String collectorNo, String type, String bzReport) {
        StringBuilder sb = new StringBuilder();
        sb.append("<?xml version=\"1.0\" encoding=\"utf-8\"?>")
                .append("<root>")
                .append("<common>")
                .append("<building_id>")
                .append(buildingNo)
                .append("</building_id>")
                .append("<gateway_id>")
                .append(collectorNo)
                .append("</gateway_id>")
                .append("<type>")
                .append(type)
                .append("</type>")
                .append("</common>")
                .append(bzReport)
                .append("</root>");
        return sb.toString();
    }


    /**
     * 身份验证 request
     * @param buildingNo
     * @param collectorNo
     * @return
     */
    public static String getValidateRequest(String buildingNo, String collectorNo) {
//        return "<?xml version=\"1.0\" encoding=\"utf-8\"?>\n" +
//                "<root>\n" +
//                "    <common>\n" +
//                "      <building_id>123</building_id>\n" +
//                "      <gateway_id>123</gateway_id>\n" +
//                "      <type>request</type>\n" +
//                "    </common>\n" +
//                "    <id_validate operation=\"request\"/>\n" +
//                "</root>";
        String bzReport = "<id_validate operation=\"request\"/>";
        String type = "request";
        return getReport(buildingNo, collectorNo, type, bzReport);
    }

    /**
     * 身份验证 md5
     * @param buildingNo
     * @param collectorNo
     * @param sequence
     * @return
     */
    public static String getValidateMD5(String buildingNo, String collectorNo, String sequence) {
        String bzReport = "<id_validate operation=\"md5\"><md5>" + MD5Util.md5Encode(sequence, null) + "</md5></id_validate>"; // md5编码
        String type = "md5";
        return getReport(buildingNo, collectorNo, type, bzReport);
    }








    public static String byteToHex(byte[] data) {
        StringBuilder sb = new StringBuilder();
        for (byte b : data) {
            sb.append(String.format("%02x", b));
        }
        return sb.toString();
    }

    public static int byteArrayToInt(byte[] bytes) {
        reverseArray(bytes);
        int value = 0;
        // 由高位到低位
        for (int i = 0; i < 4; i++) {
            int shift = (4 - 1 - i) * 8;
            value += (bytes[i] & 0x000000FF) << shift;// 往高位游
        }
        return value;
    }

    public static void reverseArray(byte[] arr) {
        for (int i = 0; i < arr.length / 2; i++) {
            byte temp = arr[i];
            arr[i] = arr[arr.length - 1 - i];
            arr[arr.length - 1 - i] = temp;
        }
    }


}
