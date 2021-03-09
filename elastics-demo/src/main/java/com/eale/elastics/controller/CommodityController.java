package com.eale.elastics.controller;

import com.eale.elastics.model.Commodity;
import com.eale.elastics.service.CommodityService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @Author Admin
 * @Date 2021/3/9
 * @Description //CommodityController
 * @Version 1.0
 **/
@RestController
public class CommodityController {


    @Autowired
    private CommodityService commodityService;


    @GetMapping("count")
    public ResponseEntity<Long> count(){
        return ResponseEntity.ok(commodityService.count());
    }

    @PostMapping("insert")
    public ResponseEntity<Commodity> insert(@RequestBody Commodity commodity){
        return ResponseEntity.ok(commodityService.save(commodity));
    }

    @PostMapping("delete")
    public ResponseEntity<String> delete(@RequestBody Commodity commodity){
        commodityService.delete(commodity);
        return ResponseEntity.ok("success");
    }

    @GetMapping("getAll")
    public ResponseEntity<Iterable<Commodity>> getAll(){
        return ResponseEntity.ok(commodityService.getAll());
    }

    @GetMapping("getByName")
    public ResponseEntity<Iterable<Commodity>> getByName(String name){
        return ResponseEntity.ok(commodityService.getByName(name));
    }

    @GetMapping("getAllByPage")
    public ResponseEntity<Page<Commodity>> getAllByPage(Integer page, Integer pageSize, String name){
        return ResponseEntity.ok(commodityService.pageQuery(page,pageSize,name));
    }

}
