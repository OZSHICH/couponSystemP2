package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;

import java.util.*;

public interface CustomerService {
    boolean login(String email, String password) throws CustomException;

    List<Coupon> getAllPurchaseCouponsByCategory(Category category);

    List<Coupon> getAllPurchaseCouponsByPrice(double price);

    void purchaseCoupon(Coupon coupon) throws CustomException;

    List<Customer> getAllCustomers();

    List<Coupon> getAllPurchaseCouponsByCustomerId(int customerId);

    Customer getCustomerDetails(int customerId);


}
