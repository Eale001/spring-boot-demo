package com.eale.client;

import com.eale.chain.ReceiptHandlerChain;
import com.eale.chain.ReceiptHandlerChainImpl;
import com.eale.model.Receipt;
import com.eale.service.ReceiptBuilder;

import java.util.List;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class ChainClient {
    public static void main(String[] args) {
        List<Receipt> receiptList = ReceiptBuilder.generateReceiptList();
        for (Receipt receipt : receiptList) {
            ReceiptHandlerChainImpl receiptHandlerChain = new ReceiptHandlerChainImpl();
            receiptHandlerChain.handleReceipt(receipt);

        }
    }
}
