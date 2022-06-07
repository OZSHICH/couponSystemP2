package com.OzProject.couponSystem.job;


import com.OzProject.couponSystem.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.scheduling.annotation.*;
import org.springframework.stereotype.*;

@Component
public class DailyRemoveExpired {

    @Autowired
    private CouponRepo couponRepo;
    private int couponId;

    @Scheduled(fixedRate = 1000 * 60 * 60* 24)
    public void run() {

            couponRepo.deleteExpiredCoupon();
            System.out.println("Expired Coupons Has Been Deleted");

        }
    }

