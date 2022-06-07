package com.OzProject.couponSystem.repository;

import com.OzProject.couponSystem.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

import javax.transaction.*;
import java.util.*;

@Repository
public interface CouponRepo extends JpaRepository<Coupon, Integer> {

    boolean existsByTitleAndCompanyId(String title, int companyId);

    List<Coupon> findAllByCategoryAndCompany(Category category,Company company);

    @Query(value = "select id from couponsystemphase2.coupons where current_date() > end_date;", nativeQuery = true)
    List<Integer> findAllExpiredCoupon();

    List<Coupon> findByPriceLessThanAndCompany(double price, Company company);

    List<Coupon> findByCompany(Company company);

    @Transactional
    @Modifying
    @Query(value = "delete from couponsystemphase2.coupons where current_date() > end_date;", nativeQuery = true)
    void deleteExpiredCoupon();

}
