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
