package com.eale.chain;

import com.eale.model.Receipt;
import com.eale.service.ReceiptHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class ReceiptHandlerChainImpl implements ReceiptHandlerChain {

    private int index = 0;

    private static List<ReceiptHandler> receiptHandlerList;

    static {
        receiptHandlerList = ReceiptHandlerContainer.getReceiptHandlerList();
    }

    @Override
    public void handleReceipt(Receipt receipt) {
        if (receiptHandlerList != null && receiptHandlerList.size()>0) {
            if (index != receiptHandlerList.size()){
                ReceiptHandler receiptHandler = receiptHandlerList.get(index++);
                receiptHandler.handleReceipt(receipt,this);
            }
        }
    }
}
