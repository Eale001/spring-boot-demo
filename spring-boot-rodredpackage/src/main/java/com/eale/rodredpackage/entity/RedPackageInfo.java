package com.eale.rodredpackage.entity;

import lombok.Data;
import org.hibernate.annotations.GeneratorType;

import javax.persistence.*;
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
@Table(name = "red_package_info")
public class RedPackageInfo {

    @Id
    @GeneratedValue
    private Integer id;

    @Column(name = "red_package_id")
    private Long redPackageId;

    @Column(name = "total_amount")
    private BigDecimal totalAmount;

    private Integer totalPacket;

    private BigDecimal remainingAmount;

    private Integer remainingPacket;

    private Integer uid;

    private Date createTime;

    private Date updateTime;




}
