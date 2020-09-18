package com.eale.rodredpackage.entity;

import lombok.Data;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import javax.persistence.Table;
import java.io.Serializable;
import java.math.BigDecimal;
import java.util.Date;

/**
 * @Author Admin
 * @Date 2020/9/14
 * @Description
 * @Version 1.0
 **/
@Entity
@Data
@Table(name = "red_package_record")
public class RedPackageRecord implements Serializable {

    @Id
    @GeneratedValue()
    private Integer id;

    private BigDecimal amount;

    private String nickName;

    private String imgUrl;

    private Integer uid;

    private Long redPackageId;

    private Date createTime;

    private Date updateTime;



}
