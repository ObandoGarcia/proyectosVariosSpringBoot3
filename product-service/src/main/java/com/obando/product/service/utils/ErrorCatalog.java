package com.obando.product.service.utils;

public enum ErrorCatalog {

    PRODUCT_NOT_FOUND("ERR_PROD_001","Product not found!"),
    INVALID_PRODUCT("ERR_PROD_002", "Invalid product parameters"),
    CATEGORY_NOT_FOUND("ERR_CAT_001", "Category not found"),
    GENERIC_ERROR("ERROR_GEN_001", "An unexpected error occurred");

    private final String code;
    private final String message;

    ErrorCatalog(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getMessage() {
        return message;
    }

    public String getCode() {
        return code;
    }
}
