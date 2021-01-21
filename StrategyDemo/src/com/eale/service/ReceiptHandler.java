package com.eale.service;

import com.eale.chain.ReceiptHandlerChain;
import com.eale.model.Receipt;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description // 抽象处理接口
 * @Version 1.0
 **/
public interface ReceiptHandler {

    void handleReceipt(Receipt receipt, ReceiptHandlerChain handleChain);

}
