/**
 * Copyright (c) 2020, 中电万维公司 All rights reserved.
 * 中电万维公司 专有/保密源代码,未经许可禁止任何人通过任何渠道使用、修改源代码.
 */
package com.example.multidatasource.entity.test1Entity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName User
 * @Description TODO
 * @Author caoti
 * @Date 2020/3/13 17:32
 * @since Jdk 1.8
 **/
@Entity
public class User {
    private String logname;
    private String password;
    private String email;

    @Id
    public String getLogname() {
        return logname;
    }

    public void setLogname(String logname) {
        this.logname = logname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }
}
