package com.eale.chain;

import com.eale.service.ReceiptHandler;
import com.eale.service.impl.Mt2101ReceiptHandler;
import com.eale.service.impl.Mt8104ReceiptHandler;
import com.eale.util.ReflectionUtil;

import java.util.ArrayList;
import java.util.List;
import java.util.Set;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class ReceiptHandlerContainer {

    private ReceiptHandlerContainer(){}

    public static List<ReceiptHandler> getReceiptHandlerList() {
        List<ReceiptHandler> receiptHandlers = new ArrayList<>();

        // 获取ReceiptHandler接口的实现类
        Set<Class<?>> classSet = ReflectionUtil.getClazzSetBySuper(ReceiptHandler.class);
        if (classSet != null && classSet.size()>0){
            for (Class<?> clazz : classSet) {
                try {
                    receiptHandlers.add((ReceiptHandler) clazz.newInstance());
                } catch (Exception e) {
                    e.printStackTrace();
                }

            }
        }
        return receiptHandlers;
    }
}
