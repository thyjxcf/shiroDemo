package com.hzgg.test.mapper;

import com.hzgg.test.domain.ShiroUser;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/12/5 10:56
 */
public interface ShiroUserMapper {
    public ShiroUser findByName(String name);
    public ShiroUser findById(Integer Id);
}
