package com.eale.service;

import com.eale.model.Receipt;

import java.util.List;
import java.util.Objects;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class ReceiveReceipt {

    public void receiveReceipt(){
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        for (Receipt receipt : receiptList) {
            if (Objects.equals("MT2101",receipt.getType())) {
                System.out.println("接收到MT2101回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
            } else if (Objects.equals("MT1101",receipt.getType())) {
                System.out.println("接收到MT1101回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
            } else if (Objects.equals("MT8104",receipt.getType())) {
                System.out.println("接收到MT8104回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
            } else if (Objects.equals("MT9999",receipt.getType())) {
                System.out.println("接收到MT9999回执");
                System.out.println("解析回执内容");
                System.out.println("执行业务逻辑");
                System.out.println("推送邮件");
            }
        }
    }






}
