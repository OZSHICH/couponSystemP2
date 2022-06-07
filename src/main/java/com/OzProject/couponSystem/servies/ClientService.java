package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.repository.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.stereotype.*;

@Service
public abstract class ClientService {

    @Autowired
    protected CompanyRepo companyRepo;

    @Autowired
    protected CouponRepo couponRepo;

    @Autowired
    protected CustomerRepo customerRepo;

    public abstract boolean login(String email, String password) throws CustomException;

}
