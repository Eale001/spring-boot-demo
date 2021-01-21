package com.eale.service.impl;

import com.eale.chain.ReceiptHandlerChain;
import com.eale.model.Receipt;
import com.eale.service.ReceiptHandler;

import java.util.Objects;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class Mt8104ReceiptHandler implements ReceiptHandler {

    @Override
    public void handleReceipt(Receipt receipt, ReceiptHandlerChain handleChain) {
        if (Objects.equals("MT8104",receipt.getType())) {
            System.out.println("解析报文MT8104:" + receipt.getMessage());
        }
        //处理不了该回执就往下传递
        else {
            handleChain.handleReceipt(receipt);
        }
    }
}
