package com.eale.rodredpackage.dao;

import com.eale.rodredpackage.entity.RedPackageInfo;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

/**
 * @Author Admin
 * @Date 2020/9/14
 * @Description //TODO
 * @Version 1.0
 **/
public interface RedPackageInfoDao extends JpaRepository<RedPackageInfo,Integer> {
    List<RedPackageInfo> findAllByRedPackageId(Long redPackageId);
}
