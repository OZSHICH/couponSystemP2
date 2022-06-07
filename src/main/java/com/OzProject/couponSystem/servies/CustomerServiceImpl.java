package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CustomerServiceImpl extends ClientService implements CustomerService {

    private Customer customer;

    @Override
    public boolean login(String email, String password) throws CustomException {
        if (!customerRepo.existsByEmailAndPassword(email, password)) {
            throw new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST);
        }
        customer = customerRepo.findByEmail(email);
        System.out.println("*********Customer Number " + customer.getId() + " Has Logged in" + " ********");
        return true;
    }


    @Override
    public void purchaseCoupon(Coupon coupon) throws CustomException {
        int couponId = customerRepo.getCouponByCompanyIdAndTitle(coupon.getCompany().getId(),coupon.getTitle());
        if (customerRepo.purchaseCouponIsExist(customer.getId(),couponId).intValue()==1){
            throw new CustomException(ErrorMessage.COUPON_UNAVAILABLE);
        }
        if (coupon.getAmount()<= 0){
            throw new CustomException(ErrorMessage.COUPON_UNAVAILABLE);
        }
        if (customerRepo.isCouponExpired(couponId).intValue()==1){
            throw new CustomException(ErrorMessage.COUPON_UNAVAILABLE);
        }
        System.out.println("*********Coupon Number " + couponId + " Been Purchased By Customer Number " + customer.getId() + " ********");
        customerRepo.addCouponPurchase(customer.getId(),couponId);
        coupon.setAmount(coupon.getAmount() - 1);
        couponRepo.saveAndFlush(coupon);


    }
    public List<Coupon> getAllPurchaseCouponsByCategory(Category category){
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon:customer.getCoupons()) {
            if (coupon.getCategory() == category) {
                coupons.add(coupon);
            }
        }
        return coupons;
    }


    public List<Coupon> getAllPurchaseCouponsByPrice(double price){
        List<Coupon> coupons = new ArrayList<>();
        for (Coupon coupon:customer.getCoupons()) {
            if (coupon.getPrice() < price){
                coupons.add(coupon);
            }
        }
        return coupons;
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public List<Coupon> getAllPurchaseCouponsByCustomerId(int customerId) {
        List<Coupon> coupons = new ArrayList<>();
        List<Integer>  couponsId = customerRepo.getAllPurchaseCouponsByCustomerId(customer.getId());
        for (Integer i:couponsId ) {
            coupons.add(couponRepo.getById(i));

        }
        return coupons;
    }

    @Override
    public Customer getCustomerDetails(int customerId) {
        return customerRepo.getById(customerId);

    }
}
