package vn.codingt.clean.presenter.config;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EntityScan(basePackages = { "vn.codingt.clean.data.db.jpa.entities" })
@EnableJpaRepositories(basePackages = { "vn.codingt.clean.data.db.jpa.repositories" })
public class DatabaseConfig {

}
