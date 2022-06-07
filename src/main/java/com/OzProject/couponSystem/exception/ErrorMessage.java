package com.OzProject.couponSystem.exception;

import lombok.*;

@Getter
public enum ErrorMessage {
    COMPANY_ALREADY_EXIST("Company already exists"),
    COMPANY_NOT_EXIST("Company not exists"),
    COUPON_ALREADY_EXIST("Coupon already exists"),
    COUPON_NOT_EXIST("Coupon not exists"),
    CUSTOMER_ALREADY_EXIST("Customer already exists"),
    CUSTOMER_NOT_EXIST("Customer not exists"),
    COUPON_UNAVAILABLE("Coupon not qualified for purchased"),
    COUPON_EXPIRED("Delete expired coupons");

    private String Message;

    ErrorMessage(String message) {
        Message = message;
    }
}
