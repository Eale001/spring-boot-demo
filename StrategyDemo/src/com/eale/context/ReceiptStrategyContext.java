package com.eale.context;

import com.eale.model.Receipt;
import com.eale.strategy.ReceiptHandleStrategy;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description 上下文类，持有策略接口
 * @Version 1.0
 **/
public class ReceiptStrategyContext {

    private ReceiptHandleStrategy receiptHandleStrategy;

    /**
     * 设置策略接口
     * @param receiptHandleStrategy
     */
    public void setReceiptHandleStrategy(ReceiptHandleStrategy receiptHandleStrategy){
        this.receiptHandleStrategy=receiptHandleStrategy;

    }

    public void handleReceipt(Receipt receipt){
        if (receiptHandleStrategy != null){
            receiptHandleStrategy.handleReceipt(receipt);
        }
    }

}
