package com.eale.elastics.repository;

import com.eale.elastics.model.Commodity;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;
import org.springframework.stereotype.Repository;

/**
 * @Author Admin
 * @Date 2021/3/9
 * @Description //CommodityRepository
 * @Version 1.0
 **/
@Repository
public interface CommodityRepository extends ElasticsearchRepository<Commodity,String> {

}
