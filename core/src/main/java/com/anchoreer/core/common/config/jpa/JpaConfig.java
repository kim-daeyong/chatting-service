package com.anchoreer.core.common.config.jpa;

import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaAuditing;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

@Configuration
@EnableJpaAuditing
@EntityScan(basePackages = "com.anchoreer.core.domain.entity")
@EnableJpaRepositories(basePackages = "com.anchoreer.core.domain.*.repository")
public class JpaConfig {
}
