package com.xbcx.renting.service.impl;

import com.xbcx.renting.entity.Permission;
import com.xbcx.renting.mapper.PermissionMapper;
import com.xbcx.renting.service.PermissionService;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import org.springframework.stereotype.Service;

/**
 * <p>
 * 权限表 服务实现类
 * </p>
 *
 * @author hzh
 * @since 2020-09-23
 */
@Service
public class PermissionServiceImpl extends ServiceImpl<PermissionMapper, Permission> implements PermissionService {

}
