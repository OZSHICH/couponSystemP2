package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;

import java.util.*;

public interface CompanyService {
    boolean login(String email, String password) throws CustomException;
    void addCoupon(Coupon coupon) throws CustomException;

    void updateCoupon(Coupon coupon) throws CustomException;

    void deleteCoupon(int couponId) throws CustomException;

    List<Coupon> getAllCoupons(Company company);

    List<Coupon> getAllCouponsByCategory(Category category);

    List<Coupon> getAllCouponsByPrice(double price);

    Coupon getOneCoupon(int id) throws CustomException;

    Company getOneCompany(int id) throws CustomException;
}
