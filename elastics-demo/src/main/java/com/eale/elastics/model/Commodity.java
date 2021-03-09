package com.eale.elastics.model;

import lombok.Data;
import org.springframework.data.annotation.Id;
import org.springframework.data.elasticsearch.annotations.Document;

import java.io.Serializable;

/**
 * @Author Admin
 * @Date 2021/3/9
 * @Description //Commodity
 * @Version 1.0
 **/
@Data
@Document(indexName = "commodity")
public class Commodity implements Serializable {

    @Id
    private String skuId;

    private String name;

    private String category;

    private Integer price;

    private String brand;

    private Integer stock;

}
