package com.eale.rodredpackage.service.impl;

import com.eale.rodredpackage.dao.RedPackageInfoDao;
import com.eale.rodredpackage.dao.RedPackageRecordDao;
import com.eale.rodredpackage.entity.RedPackageInfo;
import com.eale.rodredpackage.entity.RedPackageRecord;
import com.eale.rodredpackage.service.RedPackageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @Author Admin
 * @Date 2020/9/15
 * @Description
 * @Version 1.0
 **/
@Service
public class RedPackageServiceImpl implements RedPackageService {

    @Autowired
    private RedPackageInfoDao redPackageInfoDao;

    @Autowired
    private RedPackageRecordDao redPackageRecordDao;

    @Override
    public RedPackageInfo save(RedPackageInfo packageInfo) {

        return redPackageInfoDao.save(packageInfo);
    }

    @Override
    public RedPackageRecord save(RedPackageRecord redPackageRecord) {
        return redPackageRecordDao.save(redPackageRecord);
    }

    @Override
    public List<RedPackageRecord> getRecordById(Long redPackageId) {
        return redPackageRecordDao.findAllByRedPackageId(redPackageId);
    }

    @Override
    public RedPackageInfo getRedPackageByRedPackageId(Long redPackageId) {
        return redPackageInfoDao.findAllByRedPackageId(redPackageId).get(0);
    }


}
