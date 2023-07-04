package cn.edu.szu.cs.quickmonomer.config;

import com.alibaba.druid.pool.DruidDataSource;
import com.baomidou.mybatisplus.annotation.DbType;
import com.baomidou.mybatisplus.core.MybatisConfiguration;
import com.baomidou.mybatisplus.core.MybatisXMLLanguageDriver;
import com.baomidou.mybatisplus.extension.plugins.MybatisPlusInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.OptimisticLockerInnerInterceptor;
import com.baomidou.mybatisplus.extension.plugins.inner.PaginationInnerInterceptor;
import com.baomidou.mybatisplus.extension.spring.MybatisSqlSessionFactoryBean;
import org.apache.ibatis.session.SqlSessionFactory;
import org.apache.ibatis.type.JdbcType;
import org.mybatis.spring.annotation.MapperScan;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.support.PathMatchingResourcePatternResolver;
import org.springframework.jdbc.datasource.DataSourceTransactionManager;

import javax.sql.DataSource;

/**
 * @description: TODO
 * @author hjc
 * @date 2022/11/7 22:00
 * @version 1.0
 */
@Configuration
@MapperScan(basePackages = "cn.edu.szu.cs.quickmonomer.mapper", sqlSessionFactoryRef = "sqlSessionFactory")
public class MyBatisPlusConfig {

    static final String MAPPER_LOCATION = "classpath*:mapper/*.xml";

    /**
     * 数据源
     * @return
     */
    @Bean
    @ConfigurationProperties(prefix = "spring.datasource.druid")
    public DataSource dataSource() {
       return new DruidDataSource();
    }

    /**
     * 事务管理器
     * @param dataSource
     * @return
     */

    @Bean(name = "transactionManager")
    public DataSourceTransactionManager transactionManager(@Autowired DataSource dataSource) {
        return new DataSourceTransactionManager(dataSource);
    }

    /**
     *
     * @param dataSource
     * @return
     * @throws Exception
     */

    @Bean(name = "sqlSessionFactory")
    public SqlSessionFactory sqlSessionFactory(@Autowired DataSource dataSource) throws Exception {

        MybatisSqlSessionFactoryBean factoryBean = new MybatisSqlSessionFactoryBean();

        factoryBean.setDataSource(dataSource);

        MybatisConfiguration configuration = new MybatisConfiguration();

        configuration.setDefaultScriptingLanguage(MybatisXMLLanguageDriver.class);

        configuration.setJdbcTypeForNull(JdbcType.NULL);

        // 开启日志打印
        //configuration.setLogImpl(org.apache.ibatis.logging.stdout.StdOutImpl.class);

        factoryBean.setConfiguration(configuration);
        //
        factoryBean.setMapperLocations(new PathMatchingResourcePatternResolver().getResources(MyBatisPlusConfig.MAPPER_LOCATION));

        //加载插件
        factoryBean.setPlugins(mybatisPlusInterceptor());
        return factoryBean.getObject();
    }

    /**
     * 插件
     * @return
     */

    @Bean(name = "mybatisPlusInterceptor")
    public MybatisPlusInterceptor mybatisPlusInterceptor() {
        MybatisPlusInterceptor interceptor = new MybatisPlusInterceptor();
        // 乐观锁插件
        interceptor.addInnerInterceptor(new OptimisticLockerInnerInterceptor());
        // DbType：数据库类型(根据类型获取应使用的分页方言)
        // 分页插件
        interceptor.addInnerInterceptor(new PaginationInnerInterceptor(DbType.MYSQL));
        return interceptor;
    }



}

