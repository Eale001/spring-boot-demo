package com.eale.strategy.impl;

import com.eale.model.Receipt;
import com.eale.strategy.ReceiptHandleStrategy;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class Mt1101ReceiptHandleStrategy implements ReceiptHandleStrategy {
    @Override
    public void handleReceipt(Receipt receipt) {
        System.out.println("这是在解析 Mt1101 回执报文："+receipt.getMessage());
    }
}
