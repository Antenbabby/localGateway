package com.antennababy.localgateway.entity;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import lombok.Data;

import java.io.Serializable;
import java.util.Date;

/**
* 操作记录表
* @TableName t_h_option_log
*/
@Entity
@Data
@Table(name = "MD_FILE")
public class MdFile  extends BaseEntity  implements Serializable {

   String fileName;
   String fileContent;
   Date updateDate;
}
