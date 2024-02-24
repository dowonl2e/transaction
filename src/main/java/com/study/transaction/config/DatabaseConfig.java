package com.study.transaction.config;

import com.zaxxer.hikari.HikariConfig;
import com.zaxxer.hikari.HikariDataSource;
import lombok.extern.slf4j.Slf4j;
import org.apache.commons.dbcp2.BasicDataSource;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.mybatis.spring.SqlSessionTemplate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.context.properties.ConfigurationPropertiesScan;
import org.springframework.context.ApplicationContext;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.PropertySource;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;
import org.springframework.transaction.TransactionManager;

import javax.sql.DataSource;

/**
 * 애플리케이션 구성 파일에 설정된 옵션들 중 hikariCP의 옵션을 통해 DB 커넥션을 구성
 */
@Configuration
public class DatabaseConfig {

  @Autowired
  private ApplicationContext context;

  @Bean
  public DataSource dataSource() {  // DataSource 객체 생성
    BasicDataSource dataSource = new BasicDataSource();

    dataSource.setDriverClassName(context.getEnvironment().getProperty("spring.datasource.driver-class-name"));
    dataSource.setUrl(context.getEnvironment().getProperty("spring.datasource.url"));
    dataSource.setUsername(context.getEnvironment().getProperty("spring.datasource.username"));
    dataSource.setPassword(context.getEnvironment().getProperty("spring.datasource.password"));

    return dataSource;
  }

  @Bean
  public SqlSessionFactory sqlSessionFactory() throws Exception {
    SqlSessionFactoryBean factoryBean = new SqlSessionFactoryBean();
    factoryBean.setDataSource(dataSource());
    factoryBean.setMapperLocations(context.getResources("classpath:/mappers/**/*Mapper.xml"));
    factoryBean.setTypeAliasesPackage("com.study.*");
    factoryBean.setConfiguration(mybatisConfig());
    return factoryBean.getObject();
  }

  @Bean
  public SqlSessionTemplate sqlSession() throws Exception {
    return new SqlSessionTemplate(sqlSessionFactory());
  }

  @Bean
  @ConfigurationProperties(prefix = "mybatis.configuration")
  public org.apache.ibatis.session.Configuration mybatisConfig() {
    return new org.apache.ibatis.session.Configuration();
  }

  @Bean
  public TransactionManager transactionManager() {
    return new DataSourceTransactionManager(dataSource());
  }

}
