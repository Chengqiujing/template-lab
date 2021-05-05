package com.btbullet.githubwebhook.service;

import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: HookService.java
 * @包 路 径： com.btbullet.githubwebhook.service
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @author：chengqj
 * @创建时间：2021/4/20 8:55
 */
@Service
@Slf4j
public class HookService {

    public void doPull(){
        String[] commonds = {"sh","/opt/web-hook/webhook.sh"};
//        String[] commonds1 = {"cmd","/c","dir"};
        ProcessBuilder processBuilder = new ProcessBuilder(commonds);
        BufferedReader bufferedReader = null;
        try {
            log.info(">>>命令执行开始<<<");
            processBuilder.redirectErrorStream(true);
            Process start = processBuilder.start();
            bufferedReader = new BufferedReader(new InputStreamReader(start.getInputStream(),"gbk"));
            String temp = null;
            while ((temp = bufferedReader.readLine()) != null){
                log.info(temp);
            }
            start.waitFor();
            log.info("...命令执行结束...");
        } catch (IOException | InterruptedException e) {
            log.error("发生IO错误或者被打断",e);
        }finally {
            if(bufferedReader == null){
                try {
                    bufferedReader.close();
                } catch (IOException e) {
                    log.error("关闭流异常",e);
                }
            }
        }
    }
}
