package com.hzgg.test.domain;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

/**
 * Designed By luf
 *
 * @author luf
 * @date 2019/12/3 20:46
 */
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ShiroUser {

    private Integer id;
    private String name;
    private String password;
    private String perms;

}
