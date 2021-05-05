package com.btbullet.githubwebhook.controller;

import com.btbullet.githubwebhook.service.HookService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Controller;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import java.util.Base64;

/**
 * *************************************************************************
 * <p/>
 *
 * @文件名称: HookController.java
 * @包 路 径： com.btbullet.githubwebhook.controller
 * @版权所有：北京数字认证股份有限公司 (C) 2021
 * @类描述:
 * @版本: V1.0
 * @author：chengqj
 * @创建时间：2021/4/20 8:53
 */
@Controller
@RequestMapping("hook")
@Slf4j
public class HookController {

    @Value("${hook.passport}")
    private String passport;

    @Autowired
    HookService hookService;

    @PostMapping("pull")
    @ResponseBody
    public String pull(String inPassport){
        if(inPassport == null || "".equals(inPassport.trim())){
            log.error("passport is null");
        return "fail";
        }

        if(inPassport.contains(",")){
            inPassport = inPassport.split(",")[0];
        }

        byte[] decode = Base64.getDecoder().decode(inPassport);
        String passwordNeedValidate = new String(decode);
        if(!passport.equals(passwordNeedValidate)){
            log.error("passport wrong");
            return "fail";
        }
        hookService.doPull();
        return "success";
    }
}
