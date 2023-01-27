/*
 * Reksoft. Do not reproduce without permission in writing.
 * Copyright (c) 2022 Reksoft. All rights reserved.
 */

package com.geekbrains.demoboot.exception;

import lombok.NoArgsConstructor;

/**
 * Базовое исключение.
 */
@NoArgsConstructor
public class BaseException extends RuntimeException {
    public BaseException(String message) {
        super(message);
    }
}
