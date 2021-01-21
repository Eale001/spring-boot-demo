package com.eale.client;

import com.eale.context.ReceiptStrategyContext;
import com.eale.factory.ReceiptHandleStrategyFactory;
import com.eale.model.Receipt;
import com.eale.service.ReceiptBuilder;
import com.eale.strategy.ReceiptHandleStrategy;

import java.util.List;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class StrategyClient {

    public static void main(String[] args) {


        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        ReceiptStrategyContext receiptStrategyContext = new ReceiptStrategyContext();
        ReceiptHandleStrategyFactory receiptHandleStrategyFactory = new ReceiptHandleStrategyFactory();
        for (Receipt receipt : receiptList) {
            ReceiptHandleStrategy receiptHandleStrategy = ReceiptHandleStrategyFactory.getReceiptHandleStrategy(receipt.getType());
            receiptStrategyContext.setReceiptHandleStrategy(receiptHandleStrategy);
            receiptStrategyContext.handleReceipt(receipt);
        }


    }

}
