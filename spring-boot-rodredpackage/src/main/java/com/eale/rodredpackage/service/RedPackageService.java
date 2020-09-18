package com.eale.rodredpackage.service;

import com.eale.rodredpackage.entity.RedPackageInfo;
import com.eale.rodredpackage.entity.RedPackageRecord;

import java.util.List;

/**
 * @Author Admin
 * @Date 2020/9/14
 * @Description //TODO
 * @Version 1.0
 **/
public interface RedPackageService {

    RedPackageInfo save(RedPackageInfo packageInfo);

    RedPackageRecord save(RedPackageRecord redPackageRecord);

    List<RedPackageRecord> getRecordById(Long redPackageId);

    RedPackageInfo getRedPackageByRedPackageId(Long redPackageId);
}
