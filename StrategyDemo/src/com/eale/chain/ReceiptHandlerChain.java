package com.eale.chain;

import com.eale.model.Receipt;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description //TODO
 * @Version 1.0
 **/
public interface ReceiptHandlerChain {

    void handleReceipt(Receipt receipt);

}
