package com.OzProject.couponSystem.security;

import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.servies.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.context.*;
import org.springframework.context.annotation.*;
import org.springframework.stereotype.*;

@Service
@Lazy
public class LoginManager {
    @Autowired
    private ApplicationContext ctx;
    @Autowired
    private AdminService adminService;

        public ClientService login(String email, String password, ClientType clientType) throws CustomException {
        switch (clientType) {
            case ADMINISTRATOR: {
                if (adminService.login(email, password)) {
                    return (ClientService) adminService;
                }
            }
            case COMPANY: {
                CompanyService companyService = ctx.getBean(CompanyService.class);
                if (companyService.login(email, password)) {
                    return (ClientService)companyService;
                }
            }
            case CUSTOMER: {
                CustomerService customerService = ctx.getBean(CustomerService.class);
                if (customerService.login(email, password)) {
                    return (ClientService) customerService;
                }
            }
        }
        return null;
    }
}
