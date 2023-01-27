/*
 * Reksoft. Do not reproduce without permission in writing.
 * Copyright (c) 2022 Reksoft. All rights reserved.
 */

package com.geekbrains.demoboot.mapper;

import static org.mapstruct.ReportingPolicy.ERROR;

import org.mapstruct.MapperConfig;

/**
 * Базовый маппер для конфигурации
 */
@MapperConfig(componentModel = "spring", unmappedTargetPolicy = ERROR)
public interface BaseMapperConfig {
}
