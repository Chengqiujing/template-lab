package com.ganwei.datapush.tcp.starter;

import com.ganwei.datapush.tcp.service.DataPushClient;
import org.springframework.boot.ApplicationArguments;
import org.springframework.boot.ApplicationRunner;
import org.springframework.stereotype.Component;

/**
 * @Author chengqiujing
 * @Date 2020/6/16 15:20
 * @Desc
 */
@Component
public class DataPushRunner implements ApplicationRunner {
    @Override
    public void run(ApplicationArguments args) throws Exception {
        DataPushClient client = new DataPushClient();
        client.start();
    }
}
