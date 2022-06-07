package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.repository.*;
import lombok.*;
import org.springframework.beans.factory.config.*;
import org.springframework.context.annotation.Scope;
import org.springframework.stereotype.*;

import java.util.*;

@Service
@Scope(BeanDefinition.SCOPE_PROTOTYPE)
public class CompanyServiceImpl extends ClientService implements CompanyService {


    private Company company;

    @Override
    public boolean login(String email, String password) throws CustomException {
        if (!companyRepo.existsByEmailAndPassword(email, password)) {
            throw new CustomException(ErrorMessage.COMPANY_NOT_EXIST);
        }
        company = companyRepo.findByEmail(email);
        System.out.println("*********Company Number " + company.getId() + " Has Logged in" + " ********");
        return true;
    }

    @Override
    public void addCoupon(Coupon coupon) throws CustomException {

        if (couponRepo.existsByTitleAndCompanyId(coupon.getTitle(), company.getId())) {
            throw new CustomException(ErrorMessage.COUPON_ALREADY_EXIST);
        }
        coupon.setCompany(company);
        couponRepo.saveAndFlush(coupon);
    }


    @Override
    public void updateCoupon(Coupon coupon) throws CustomException {
        if (!couponRepo.existsById(coupon.getId())) {
            throw new CustomException(ErrorMessage.COUPON_NOT_EXIST);
        }
        coupon.setId(coupon.getId());
        couponRepo.saveAndFlush(coupon);
    }

    @Override
    public void deleteCoupon(int couponId) throws CustomException {
        if (!couponRepo.existsById(couponId)) {
            throw new CustomException(ErrorMessage.COUPON_NOT_EXIST);
        }
        couponRepo.deleteById(couponId);
    }

    @Override
    public List<Coupon> getAllCoupons(Company company) {
        return couponRepo.findByCompany(company);
    }

    @Override
    public List<Coupon> getAllCouponsByCategory(Category category) {

        return couponRepo.findAllByCategoryAndCompany(category,company);
    }

    @Override
    public List<Coupon> getAllCouponsByPrice(double price) {
        return couponRepo.findByPriceLessThanAndCompany(price,company);
    }

    @Override
    public Coupon getOneCoupon(int id) throws CustomException {
        if (!couponRepo.existsById(id)) {
            throw new CustomException(ErrorMessage.COUPON_NOT_EXIST);
        }
        return couponRepo.getById(id);
    }

    @Override
    public Company getOneCompany(int id) throws CustomException {
        return companyRepo.findById(id).orElseThrow(()->new CustomException(ErrorMessage.COMPANY_NOT_EXIST));
    }
}




