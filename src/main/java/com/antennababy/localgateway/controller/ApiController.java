package com.antennababy.localgateway.controller;

import com.antennababy.localgateway.dao.MdFileRep;
import com.antennababy.localgateway.entity.MdFile;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Date;
import java.util.Optional;

/**
 * 需要登录才能访问的控制器
 *
 * @author 黑龙江省瑜美科技发展有限公司
 * @since 2022-12-11
 */
@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class ApiController {

    @Autowired
    MdFileRep mdFileRep;
    @RequestMapping("/index.md")
    public String index(){
        return mdFileRep.findByFileName("index.md")==null?null:mdFileRep.findByFileName("index.md").getFileContent();
    }
    @RequestMapping("/update/{fileName}")
    @Transactional
    public String update(@PathVariable String fileName, String content){
        mdFileRep.deleteByFileName(fileName);
        MdFile mdFile = new MdFile();
        mdFile.setFileName(fileName);
        mdFile.setFileContent(content);
        mdFile.setUpdateDate(new Date());
        mdFileRep.save(mdFile);
        return "success";
    }
}
