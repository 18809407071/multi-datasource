package com.example.multidatasource.config;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateProperties;
import org.springframework.boot.autoconfigure.orm.jpa.HibernateSettings;
import org.springframework.boot.autoconfigure.orm.jpa.JpaProperties;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.persistence.EntityManager;
import javax.sql.DataSource;
import java.util.Map;

/**
 * @ClassName Test1JpaConfig
 * @Description TODO test1数据库jpa配置
 * @Author caoti
 * @Date 2020/3/13 16:00
 * @since Jdk 1.8
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryTest1",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerTest1", //配置 事务管理器  transactionManager
        basePackages = {"com.example.multidatasource.repository.test1Repository"}//设置持久层所在位置，可配置多个，每个包之间需用逗号隔开
)
public class Test1JpaConfig {
 
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;


    @Autowired
    @Qualifier("test1DataSource")
    private DataSource test1DataSource;// 自动注入配置好的数据源
    
    @Value("${spring.jpa.hibernate.test1-dialect}")
    private String test1Dialect;// 获取对应的数据库方言
 
 
    /**
     *
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactoryTest1")
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryTest1(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                //设置数据源
                .dataSource(test1DataSource)
                //设置数据源属性
                .properties(properties)
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类 可配置多个，每个包之间需用逗号隔开
                .packages("com.example.multidatasource.entity.test1Entity")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("test1PersistenceUnit")
                .build();
 
    }
 
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        Map<String,String> map = new HashMap<>();
//        map.put("hibernate.dialect",test1Dialect);// 设置对应的数据库方言
//        jpaProperties.setProperties(map);
//        jpaProperties.
//        return jpaProperties.getHibernateProperties(dataSource);
//    }
 
    /**
     * 配置事务管理器
     *
     * @param builder
     * @return
     */
    @Bean(name = "transactionManagerTest1")
    PlatformTransactionManager transactionManagerTest1(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryTest1(builder).getObject());
    }

    @Bean(name = "entityManagerTest1")
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryTest1(builder).getObject().createEntityManager();
    }
 
}