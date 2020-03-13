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
 * @Description TODO studb数据库jpa配置
 * @Author caoti
 * @Date 2020/3/13 16:00
 * @since Jdk 1.8
 **/
@Configuration
@EnableTransactionManagement
@EnableJpaRepositories(
        entityManagerFactoryRef = "entityManagerFactoryStudb",//配置连接工厂 entityManagerFactory
        transactionManagerRef = "transactionManagerStudb", //配置 事务管理器  transactionManager
        basePackages = {"com.example.multidatasource.repository.studbRepository"}//设置持久层所在位置，可配置多个，每个包之间需用逗号隔开
)
public class StudbJpaConfig {
 
    @Autowired
    private JpaProperties jpaProperties;
    @Autowired
    private HibernateProperties hibernateProperties;


    @Autowired
    @Qualifier("studbDataSource")
    private DataSource studbDataSource;// 自动注入配置好的数据源
    
    @Value("${spring.jpa.hibernate.studb-dialect}")
    private String studbDialect;// 获取对应的数据库方言
 
 
    /**
     *
     * @param builder
     * @return
     */
    @Bean(name = "entityManagerFactoryStudb")
    @Primary
    public LocalContainerEntityManagerFactoryBean entityManagerFactoryStudb(EntityManagerFactoryBuilder builder) {
        Map<String, Object> properties = hibernateProperties.determineHibernateProperties(
                jpaProperties.getProperties(), new HibernateSettings());
        return builder
                //设置数据源
                .dataSource(studbDataSource)
                //设置数据源属性
                .properties(properties)
                //设置实体类所在位置.扫描所有带有 @Entity 注解的类 可配置多个，每个包之间需用逗号隔开
                .packages("com.example.multidatasource.entity.studbEntity")
                // Spring会将EntityManagerFactory注入到Repository之中.有了 EntityManagerFactory之后,
                // Repository就能用它来创建 EntityManager 了,然后 EntityManager 就可以针对数据库执行操作
                .persistenceUnit("studbPersistenceUnit")
                .build();
 
    }
 
//    private Map<String, String> getVendorProperties(DataSource dataSource) {
//        Map<String,String> map = new HashMap<>();
//        map.put("hibernate.dialect",studbDialect);// 设置对应的数据库方言
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
    @Bean(name = "transactionManagerStudb")
    @Primary
    PlatformTransactionManager transactionManagerStudb(EntityManagerFactoryBuilder builder) {
        return new JpaTransactionManager(entityManagerFactoryStudb(builder).getObject());
    }

    @Bean(name = "entityManagerStudb")
    @Primary
    public EntityManager entityManager(EntityManagerFactoryBuilder builder) {
        return entityManagerFactoryStudb(builder).getObject().createEntityManager();
    }
 
}