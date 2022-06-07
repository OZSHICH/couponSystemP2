package com.OzProject.couponSystem.repository;

import com.OzProject.couponSystem.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.math.*;
import java.util.*;

@Repository
public interface CustomerRepo extends JpaRepository<Customer, Integer> {


    boolean existsByEmailAndPassword(String email, String password);

    boolean existsByEmail(String email);

    Customer findByEmail(String email);


    @Query(value = "SELECT exists(SELECT * FROM `couponsystemphase2`.customers_coupons where customer_id = ? and coupon_id = ?) as res;", nativeQuery = true)
    BigInteger purchaseCouponIsExist(int customerid,int couponId);

    @Query(value = "select exists (SELECT * FROM couponsystemphase2.coupons where id = ? and current_date() > end_date) as res;", nativeQuery = true)
    BigInteger isCouponExpired(int id);

    @Query(value = "SELECT * FROM `couponsystemphase2`.coupons WHERE company_id = ? AND title = ? ;", nativeQuery = true)
    int getCouponByCompanyIdAndTitle(int companyId, String title);

    @Transactional
    @Modifying
    @Query(value = "INSERT INTO `couponsystemphase2`.`customers_coupons` (`CUSTOMER_ID`, `COUPON_ID`) VALUES (?,?);", nativeQuery = true)
    void addCouponPurchase(int customerId, int couponId);

    @Query(value = "SELECT coupon_id FROM couponsystemphase2.customers_coupons join couponsystemphase2.coupons on customers_coupons.COUPON_ID = coupons.id where customers_coupons.CUSTOMER_ID = ? and price < ?;", nativeQuery = true)
    List<Integer> getAllPurchaseCouponsByPrice(int customerId, double price);

    @Query(value = "SELECT coupon_id FROM `couponsystemphase2`.customers_coupons where customer_id = ?;", nativeQuery = true)
    List<Integer> getAllPurchaseCouponsByCustomerId(int customerId);




}
