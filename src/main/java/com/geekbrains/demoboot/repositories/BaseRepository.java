/*
 * Reksoft. Do not reproduce without permission in writing.
 * Copyright (c) 2022 Reksoft. All rights reserved.
 */

package com.geekbrains.demoboot.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.repository.NoRepositoryBean;

import java.io.Serializable;

/**
 * Базовый репозиторий для единой конфигурации.
 *
 * @param <T> entity type.
 * @param <I> identifier type.
 */
@NoRepositoryBean
public interface BaseRepository<T, I extends Serializable> extends JpaRepository<T, I>, JpaSpecificationExecutor<T> {
}
