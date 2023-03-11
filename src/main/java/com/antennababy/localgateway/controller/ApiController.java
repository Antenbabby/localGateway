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


@RestController
@RequestMapping("/api")
@PreAuthorize("hasAuthority('ROLE_USER')")
public class ApiController {

    @Autowired
    MdFileRep mdFileRep;
    @RequestMapping("/getFile/{fileName}")
    public String index(@PathVariable String fileName ){
        MdFile byFileName = mdFileRep.findByFileName(fileName);
        return byFileName==null?null:byFileName.getFileContent();
    }
    @RequestMapping("/updateFile/{fileName}")
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
