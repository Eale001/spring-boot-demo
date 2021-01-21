package com.eale.factory;

import com.eale.strategy.ReceiptHandleStrategy;
import com.eale.strategy.impl.*;

import java.util.HashMap;
import java.util.Map;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description 策略工厂
 * @Version 1.0
 **/
public class ReceiptHandleStrategyFactory {

    private static Map<String,ReceiptHandleStrategy> receiptHandleStrategyMap;

    public ReceiptHandleStrategyFactory(){
        receiptHandleStrategyMap = new HashMap<>();



        receiptHandleStrategyMap.put("MT2101", new Mt2101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT1101", new Mt1101ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT8104", new Mt8104ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT9999", new Mt9999ReceiptHandleStrategy());
        receiptHandleStrategyMap.put("MT6666", new Mt6666ReceiptHandleStrategy());
    }

    public static ReceiptHandleStrategy getReceiptHandleStrategy(String receiptType){
        return receiptHandleStrategyMap.get(receiptType);
    }


}
