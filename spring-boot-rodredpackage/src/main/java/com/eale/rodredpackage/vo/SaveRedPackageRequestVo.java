package com.eale.rodredpackage.vo;

import lombok.Data;

import java.io.Serializable;
import java.math.BigDecimal;

/**
 * @Author Admin
 * @Date 2020/9/14
 * @Description
 * @Version 1.0
 **/
@Data
public class SaveRedPackageRequestVo implements Serializable {

    private Integer uid;

    private BigDecimal totalAmount;

    private Integer totalPacket;


}
