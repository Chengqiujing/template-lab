package com.ganwei.datapush.tcp.service.impl;

import com.alibaba.fastjson.JSON;
import com.ganwei.datapush.tcp.entity.Function;
import com.ganwei.datapush.tcp.entity.HttpModel;
import com.ganwei.datapush.tcp.entity.Meter;
import com.ganwei.datapush.tcp.service.PointDataService;
import com.ganwei.datapush.tcp.util.HttpUtil;

import java.math.BigDecimal;
import java.util.*;

/**
 * @Author chengqiujing
 * @Date 2020/6/16 17:15
 * @Desc
 */
public class PointDataServiceImpl implements PointDataService {
    public List<Meter> getData(){
        String url = "http://ieam.ganweitech.com/weijing/hbasedata/spot";

        Map<String, Meter> meterMap = getMeterMap();
        Set<String> keySet = meterMap.keySet();

        Map<String, Set<String>> param = new HashMap<>();
        param.put("dateAndPointId",keySet);
        String jsonString = JSON.toJSONString(param);
        HttpModel post = HttpUtil.post(url, jsonString);

        if(post.getStatus() == 200){
            Map dataMap = (Map) JSON.parse(post.getData());
            Object status = dataMap.get("status");
            if(status != null && (int)status == 1){
                Map data = (Map)dataMap.get("data");
                String time = (String)data.get("lastDate");
                time = time.replaceAll("[- :]*", "");

                List<Meter> meters = new ArrayList<>();
                Set set = data.keySet();
                for (Object o : set) {
                    String key = (String)o;
                    if(meterMap.containsKey(key)){
                        Meter meter = meterMap.get(key);
                        Object value = data.get(key);
                        if(value == null) continue;
                        meter.getFunctions().get(0).setFunctionData(((BigDecimal)value).doubleValue());
                        meter.getFunctions().get(0).setFunctionSampleTime(time);
                        meters.add(meter);
                    }
                }
//                Map<String,List<Meter>> mapResult = new HashMap<>();
//                mapResult.put(time,meters);
                return meters;
            }

        }
        return null;
    }

    private Map<String, Meter> getMeterMap(){
        Map<String,Meter> map = new HashMap<>();
        map.put("now#"+"600001",getMeter("制冷系统总表","600001"));
        map.put("now#"+"519",getMeter("电制冷机1#电表.配电柜E总","519"));
        map.put("now#"+"520",getMeter("电制冷机3#电表.配电柜E总","520"));
        map.put("now#"+"521",getMeter("冷冻泵1#电表.总电能E总","521"));
        map.put("now#"+"522",getMeter("冷冻泵2#电表.总电能E总","522"));
        map.put("now#"+"523",getMeter("冷冻泵3#电表.总电能E总","523"));
        map.put("now#"+"524",getMeter("冷却泵1#电表.总电能E总","524"));
        map.put("now#"+"525",getMeter("冷却泵2#电表.总电能E总","525"));
        map.put("now#"+"526",getMeter("冷却泵3#电表.总电能E总","526"));
        map.put("now#"+"527",getMeter("AA1_4柜电表.总电能E总","527"));
        map.put("now#"+"600002",getMeter("采暖系统总表","600002"));
        map.put("now#"+"530",getMeter("AA4_2柜W4_2电表.总电能E总","530"));
        map.put("now#"+"531",getMeter("AA4_2柜W4_3电表.总电能E总","531"));
        map.put("now#"+"532",getMeter("AA4_3柜W4_4电表.总电能E总","532"));
        map.put("now#"+"533",getMeter("AA4_3柜W4_5电表.总电能E总","533"));
        map.put("now#"+"534",getMeter("AA4_4柜W4_6电表.总电能E总","534"));
        map.put("now#"+"535",getMeter("AA4_4柜W4_7电表.总电能E总","535"));
        map.put("now#"+"536",getMeter("AA4_5柜W4_6电表.总电能E总","536"));
        map.put("now#"+"551",getMeter("AA39_1柜W39_1电表.总电能E总","551"));
        map.put("now#"+"552",getMeter("AA39_1柜W39_2电表.总电能E总","552"));
        map.put("now#"+"553",getMeter("AA39_2柜W39_2_9电表.总电能E总","553"));
        map.put("now#"+"600003",getMeter("热水系统总表","600003"));
        map.put("now#"+"546",getMeter("泳池配电柜电表.总电能E总","546"));
        map.put("now#"+"537",getMeter("AA4_6柜W4_8电表.总电能E总","537"));
        map.put("now#"+"541",getMeter("AA4_7柜W4_9电表.总电能E总","541"));
        map.put("now#"+"547",getMeter("AA25_1柜W25_1电表.总电能E总","547"));
        map.put("now#"+"554",getMeter("AA39_3柜W39_3电表.总电能E总","554"));
        return map;
    }

    private Meter getMeter(String meterName,String functionId){
        Meter meter = new Meter();
        meter.setMeterName(meterName); // 仪表名
        meter.setMeterId("B001"); // 仪表编号

        List<Function> funs = new ArrayList<>();
        Function function = new Function();
        function.setFunctionId(functionId); // 点id
        function.setFunctionCoding("01"); // 能耗数据分类
        function.setFunctionError("0"); // 是否有误
        funs.add(function);

        meter.setFunctions(funs);
        return meter;
    }
}
