package com.hzgg.test.mapper;

import com.hzgg.test.domain.ShiroUser;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/12/3 20:47
 */
public interface UserMapper {

    public ShiroUser findByName(String name);
}
