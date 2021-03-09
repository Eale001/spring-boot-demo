package com.eale.elastics.service;

import com.eale.elastics.model.Commodity;
import org.springframework.data.domain.Page;

import java.util.List;

/**
 * @Author Admin
 * @Date 2021/3/9
 * @Description //CommodityService
 * @Version 1.0
 **/
public interface CommodityService {

    long count();

    Commodity save(Commodity commodity);

    void delete(Commodity commodity);

    Iterable<Commodity> getAll();

    List<Commodity> getByName(String name);

    Page<Commodity> pageQuery(Integer pageNo, Integer pageSize, String kw);
}
