/**
 * Copyright (c) 2020, 中电万维公司 All rights reserved.
 * 中电万维公司 专有/保密源代码,未经许可禁止任何人通过任何渠道使用、修改源代码.
 */
package com.example.multidatasource.repository.test1Repository;

import com.example.multidatasource.entity.test1Entity.User;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName UserRepository
 * @Description TODO
 * @Author caoti
 * @Date 2020/3/13 17:44
 * @since Jdk 1.8
 **/
public interface UserRepository extends JpaRepository<User,String> {
}
