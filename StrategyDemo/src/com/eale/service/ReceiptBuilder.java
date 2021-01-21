package com.eale.service;

import com.eale.model.Receipt;

import java.util.ArrayList;
import java.util.List;

/**
 * @Author Admin
 * @Date 2020/7/2
 * @Description
 * @Version 1.0
 **/
public class ReceiptBuilder {

    public static List<Receipt> generateReceiptList(){

        List<Receipt> list = new ArrayList<>();
        list.add(new Receipt("我是MT2101回执！","MT2101"));
        list.add(new Receipt("我是MT1101回执！","MT1101"));
        list.add(new Receipt("我是MT8104回执！","MT8104"));
        list.add(new Receipt("我是MT9999回执！","MT9999"));
        list.add(new Receipt("我是MT6666回执！","MT6666"));
        return list;
    }

}
