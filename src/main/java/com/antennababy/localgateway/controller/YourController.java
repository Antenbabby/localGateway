package com.antennababy.localgateway.controller;

import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * 需要登录才能访问的控制器
 *
 * @author 黑龙江省瑜美科技发展有限公司
 * @since 2022-12-11
 */
@RestController
@RequestMapping("/controller")
public class YourController {

    @RequestMapping("method")
//    @PreAuthorize("hasAuthority('admin')")
    public String yourMethod(){
        return "这个信息只有登录才能看到";
    }
}
