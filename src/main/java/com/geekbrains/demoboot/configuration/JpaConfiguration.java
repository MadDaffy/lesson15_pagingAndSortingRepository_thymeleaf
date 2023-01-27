/*
 * Reksoft. Do not reproduce without permission in writing.
 * Copyright (c) 2022 Reksoft. All rights reserved.
 */

package com.geekbrains.demoboot.configuration;

import com.geekbrains.demoboot.entities.Product;
import com.geekbrains.demoboot.repositories.BaseRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.Configuration;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

import javax.annotation.PostConstruct;

/**
 * Jpa configuration.
 */
@Slf4j
@Configuration
@EntityScan(basePackageClasses = Product.class)
@EnableJpaRepositories(basePackageClasses = BaseRepository.class)
@EnableTransactionManagement
public class JpaConfiguration {

    private static final String LOG_TAG = "[JPA_CONFIGURATION] ::";

    @PostConstruct
    public void init() {
        log.info(
                "{} has been initialized.",
                LOG_TAG
        );
    }
}
