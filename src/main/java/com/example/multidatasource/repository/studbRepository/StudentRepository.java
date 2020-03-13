package com.example.multidatasource.repository.studbRepository;

import com.example.multidatasource.entity.studbEntity.Student;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * @ClassName StudentJpa
 * @Description TODO
 * @Author caoti
 * @Date 2020/3/13 17:02
 * @since Jdk 1.8
 **/
public interface StudentRepository extends JpaRepository<Student,String> {
}
