package com.liusure.springboot;


import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.log4j.Logger;
import org.apache.tomcat.jdbc.pool.DataSource;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.PlatformTransactionManager;

//启用自动配置，该注解会使Spring Boot根据项目中依赖的jar包自动配置项目的配置项
@EnableAutoConfiguration
//这是一个配置Spring的配置类
@Configuration
//@Configuration
//@ComponentScan
//@org.springframework.boot.autoconfigure.SpringBootApplication

//不是找不到包，需要在springboot启动类上添加注解：@SpringBootApplication(scanBasePackages = "com")，这个就会让Spring容器启动的时候去扫描哪些包底下的类需要初始化。
//Spring Boot项目的核心注解，主要目的是开启自动配置
@SpringBootApplication(scanBasePackages = "com")
@MapperScan("com.liusure.springboot.dao")
class Application {
    private static Logger logger = Logger.getLogger(Application.class);

    //DataSource配置
    @Bean
    @ConfigurationProperties(prefix="spring.datasource")
    public DataSource dataSource() {
        return new DataSource();
    }

    //提供SqlSeesion
    @Bean
    public SqlSessionFactory sqlSessionFactoryBean() throws Exception {

        SqlSessionFactoryBean sqlSessionFactoryBean = new SqlSessionFactoryBean();
        sqlSessionFactoryBean.setDataSource(dataSource());

        PathMatchingResourcePatternResolver resolver = new PathMatchingResourcePatternResolver();

        sqlSessionFactoryBean.setMapperLocations(resolver.getResources("classpath:/mapper/*.xml"));

        return sqlSessionFactoryBean.getObject();
    }

    @Bean
    public PlatformTransactionManager transactionManager() {
        return new DataSourceTransactionManager(dataSource());
    }


    /**
     * 测试地址：http://localhost:8080/my/getuserByid
     * @param args
     */
    public static void main(String []args){
        System.out.println("********************springBoot启动开始");
        SpringApplication.run(Application.class,args);
        System.out.println("********************springBoot启动成功");
        logger.error("已经启动成功！");
    }

}

