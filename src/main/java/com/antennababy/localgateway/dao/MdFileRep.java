package com.antennababy.localgateway.dao;

import com.antennababy.localgateway.entity.MdFile;
import org.springframework.data.repository.CrudRepository;

import java.net.ContentHandler;

public interface MdFileRep extends CrudRepository<MdFile, Integer> {

    MdFile findByFileName(String s);

    void deleteByFileName(String s);

}