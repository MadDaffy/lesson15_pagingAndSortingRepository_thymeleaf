package com.geekbrains.demoboot.exception;

/**
 * ProductNotFoundException
 *
 * @author efanov@reksoft.ru
 */
public class ProductNotFoundException extends BaseException {

    private static final long serialVersionUID = 3414875158646793158L;

    public ProductNotFoundException(String message) {
        super(message);
    }
}
