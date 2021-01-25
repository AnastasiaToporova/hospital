package app.config;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.jdbc.datasource.DriverManagerDataSource;

import javax.sql.DataSource;

@Configuration
@ComponentScan("app.repository")
public class RepositoryConfig {

    @Bean
    public DataSource postgresqlDataSource() {
        DriverManagerDataSource dataSource = new DriverManagerDataSource();
        dataSource.setDriverClassName("org.postgresql.Driver");
        dataSource.setUrl("jdbc:mysql://localhost:3306/hospital?serverTimezone=Europe/Kiyev&useSSL=false");
        dataSource.setUsername("root");
        dataSource.setPassword("root");

        return dataSource;
    }

    @Bean
    public JdbcTemplate jdbcTemplate(DataSource postgresqlDataSource) {
        return new JdbcTemplate(postgresqlDataSource);
    }

    @Bean
    public NamedParameterJdbcTemplate namedParameterJdbcTemplate(DataSource postgresqlDataSource) {
        return new NamedParameterJdbcTemplate(postgresqlDataSource);
    }

    @Bean
    public SimpleJdbcInsert simpleJdbcInsert(DataSource postgresqlDataSource) {
        return new SimpleJdbcInsert(postgresqlDataSource);
    }

}
