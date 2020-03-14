package com.example.multidatasource.entity.studbEntity;

import javax.persistence.Entity;
import javax.persistence.Id;

/**
 * @ClassName Student
 * @Description TODO
 * @Author caoti
 * @Date 2020/3/13 17:30
 * @since Jdk 1.8
 **/
@Entity
public class Student {
    private String id;
    private String name;
    private String password;
    private String sex;

    @Id
    public String getId() {
        return id;
    }

    public void setId(String id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getSex() {
        return sex;
    }

    public void setSex(String sex) {
        this.sex = sex;
    }
}
