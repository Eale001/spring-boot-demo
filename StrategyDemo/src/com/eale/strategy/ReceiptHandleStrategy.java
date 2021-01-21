package com.eale.strategy;

import com.eale.model.Receipt;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description //回执处理策略接口
 * @Version 1.0
 **/
public interface ReceiptHandleStrategy {

    void handleReceipt(Receipt receipt);

}
