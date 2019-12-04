package com.hzgg.test.service.impl;

import com.hzgg.test.domain.ShiroUser;
import com.hzgg.test.mapper.UserMapper;
import com.hzgg.test.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/12/3 20:58
 */
@Service
public class UserServiceImpl implements UserService {
    //注入Mapper接口
    @Autowired
    private UserMapper userMapper;

    @Override
    public ShiroUser findByName(String name) {
        return userMapper.findByName(name);
    }
}
