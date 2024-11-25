package co.istad.reporting.config;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.boot.jdbc.DataSourceBuilder;
import org.springframework.boot.orm.jpa.EntityManagerFactoryBuilder;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.Primary;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.orm.jpa.JpaTransactionManager;
import org.springframework.orm.jpa.LocalContainerEntityManagerFactoryBean;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

@Configuration
@EnableJpaRepositories(
        basePackages = "co.istad.reporting.features.report",
        entityManagerFactoryRef = "oracleEntityManagerFactory",
        transactionManagerRef = "oracleTransactionManager"
)
public class OracleDataSourceConfig {

    @Bean
    @ConfigurationProperties(prefix = "spring.oracle-datasource")
    public DataSource oracleDataSource() {
        return DataSourceBuilder.create().build();
    }

    @Bean
    public LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory(EntityManagerFactoryBuilder oracleBuilder,
                                                                             DataSource oracleDataSource) {

        Map<String, Object> properties = new HashMap<>();
        properties.put("hibernate.hbm2ddl.auto", "none");
        properties.put("hibernate.dialect", "org.hibernate.dialect.OracleDialect");

        return oracleBuilder
                .dataSource(oracleDataSource)
                .packages("co.istad.reporting.domain.oracle")
                .properties(properties)
                .persistenceUnit("oracle")
                .build();
    }

    @Bean
    public PlatformTransactionManager oracleTransactionManager(LocalContainerEntityManagerFactoryBean oracleEntityManagerFactory) {
        return new JpaTransactionManager(Objects.requireNonNull(oracleEntityManagerFactory.getObject()));
    }

}
