package com.qinweizhao.generator.controller;

import cn.hutool.core.lang.UUID;
import com.qinweizhao.generator.model.LoginParam;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.LinkedHashMap;
import java.util.Map;

/**
 * @author qinweizhao
 * @since 2023-04-24
 */
@CrossOrigin
@RestController
@RequestMapping
@RequiredArgsConstructor
public class LoginController {

    @PostMapping("/login")
    public Map<String, Object> login(LoginParam param) {

        System.out.println(param.getUsername());
        System.out.println(param.getPassword());
        Map<String,Object> result = new LinkedHashMap<>();
        result.put("access_token", UUID.randomUUID().toString());
        result.put("expires_in",12312324234L);
        result.put("refresh_token",UUID.randomUUID().toString());

        return result;
    }

    @DeleteMapping("/logout")
    public void logout() {

        System.out.println("执行退出操作");
    }


}
