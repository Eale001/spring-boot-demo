package com.eale.rodredpackage.controller;

import com.eale.rodredpackage.common.Result;
import com.eale.rodredpackage.entity.RedPackageInfo;
import com.eale.rodredpackage.entity.RedPackageRecord;
import com.eale.rodredpackage.service.RedPackageService;
import com.eale.rodredpackage.service.RedisService;
import com.eale.rodredpackage.vo.RodPackageRequestVo;
import com.eale.rodredpackage.vo.SaveRedPackageRequestVo;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;
import java.util.Date;
import java.util.Random;

/**
 * @Author Admin
 * @Date 2020/9/14
 * @Description
 * @Version 1.0
 **/
@RestController
@RequestMapping("/redPackage")
public class RedPackageController {

    @Autowired
    private RedPackageService redPackageService;

    @Autowired
    private RedisService redisService;



    private static final String TOTAL_NUM = "_totalNum";
    private static final String TOTAL_AMOUNT = "_totalAmount";

    // 发红包
    @PostMapping("/sendRedPackage")
    public Result sendRedPackage(@RequestBody SaveRedPackageRequestVo packageRequestVo){
        RedPackageInfo packageInfo = new RedPackageInfo();
        packageInfo.setTotalAmount(packageRequestVo.getTotalAmount());
        packageInfo.setTotalPacket(packageRequestVo.getTotalPacket());
        packageInfo.setRemainingAmount(packageRequestVo.getTotalAmount());
        packageInfo.setRemainingPacket(packageRequestVo.getTotalPacket());
        packageInfo.setUid(packageRequestVo.getUid());
        packageInfo.setCreateTime(new Date());
        //分布式情况下，最好使用雪花算法生成
        long redPackageId = System.currentTimeMillis();
        packageInfo.setRedPackageId(redPackageId);
        RedPackageInfo save = redPackageService.save(packageInfo);

        // 往redis插入两条数据
        // 1,红包个数，2，红包金额
        redisService.set(redPackageId+TOTAL_NUM, packageInfo.getTotalPacket()+"");
        redisService.set(redPackageId+TOTAL_AMOUNT, packageInfo.getTotalAmount()+"");
        return new Result(0, "success", packageInfo);
    }


    // 抢红包
    @PostMapping("/rodRedPackage")
    public Result rodRedPackage(@RequestBody RodPackageRequestVo rodPackageRequestVo){
        BigDecimal randomAmount = BigDecimal.ZERO;
        String redPackageNum = rodPackageRequestVo.getRedPackageId()+TOTAL_NUM+"";
        String redPackageAmount = rodPackageRequestVo.getRedPackageId()+TOTAL_AMOUNT+"";
        // 从redis拿取红包个数 判断红包个数是否大于1
        if (redisService.exists(redPackageNum)){
            Integer num = Integer.valueOf(redisService.get(redPackageNum)+"");
            if (num != null && num >1){
                // 红包数量减1
                redisService.decr(redPackageNum);
                if (redisService.exists(redPackageAmount)){
                    Integer maxMoney = Integer.valueOf(redisService.get(redPackageAmount)+"")/num *2;
                    Random random = new Random();
                    // 随机抢到的红包金额
                    randomAmount = new BigDecimal(random.nextInt(maxMoney));

                    // redis红包金额减去指定值
                    redisService.decr(redPackageAmount, randomAmount.intValue());
                }
                updateRecordTpDB(rodPackageRequestVo.getUid(),rodPackageRequestVo.getRedPackageId(),randomAmount);
                return new Result(0,"抢到红包:"+randomAmount);
            }
            return new Result(1,"红包被抢完了");
        }else {
            return new Result(2,"红包不存在，请重试");

        }
    }


    private void updateRecordTpDB(Integer uid, Long redPackageId, BigDecimal randomAmount) {
        RedPackageRecord redPackageRecord = new RedPackageRecord();
        redPackageRecord.setAmount(randomAmount);
        redPackageRecord.setNickName("千寻");
        redPackageRecord.setImgUrl("https://images.cnblogs.com/cnblogs_com/chenyanbin/1560326/o_qianxun.jpg");
        redPackageRecord.setRedPackageId(redPackageId);
        redPackageRecord.setUid(uid);
        redPackageRecord.setCreateTime(new Date());
        RedPackageRecord packageRecord = redPackageService.save(redPackageRecord);
        // 更新redPackage
        RedPackageInfo packageInfo = redPackageService.getRedPackageByRedPackageId(redPackageId);
        if (packageInfo != null){
            packageInfo.setRemainingAmount(packageInfo.getRemainingAmount().subtract(randomAmount));
            packageInfo.setRemainingPacket(packageInfo.getRemainingPacket()-1);
            packageInfo.setUpdateTime(new Date());
            redPackageService.save(packageInfo);
        }
    }


}
