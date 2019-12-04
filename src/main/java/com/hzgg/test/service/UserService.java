package com.hzgg.test.service;

import com.hzgg.test.domain.ShiroUser;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/12/3 20:58
 */
public interface UserService {

    public ShiroUser findByName(String name);
}
