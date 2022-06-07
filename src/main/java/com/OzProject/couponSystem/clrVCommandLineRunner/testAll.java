package com.OzProject.couponSystem.clrVCommandLineRunner;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;
import com.OzProject.couponSystem.repository.*;
import com.OzProject.couponSystem.security.*;
import com.OzProject.couponSystem.servies.*;
import com.OzProject.couponSystem.utils.*;
import org.springframework.beans.factory.annotation.*;
import org.springframework.boot.*;
import org.springframework.core.annotation.*;
import org.springframework.stereotype.*;

import java.sql.Date;
import java.time.*;
import java.util.*;

@Service
@Order(1)
public class testAll implements CommandLineRunner {

    Date date = Date.valueOf(LocalDate.now());
    Date endDate = Date.valueOf(LocalDate.now());
    Date expiredDate = Date.valueOf(LocalDate.now().minusDays(2));
//Date.valueOf(LocalDate.now().minusDays(7)\plusDays(7))

    @Autowired
    private CompanyRepo companyRepo;
    @Autowired
    private CouponRepo couponRepo;
    @Autowired
    private CustomerRepo customerRepo;
    @Autowired
    private CompanyService companyService;
    @Autowired
    private CustomerService customerService;
    @Autowired
    private LoginManager loginManager;

    @Override
    public void run(String... args) throws Exception {

        Company c1 = Company
                .builder()
                .name("seaw")
                .email("seaw@gmail.com")
                .password("sda")
                .build();

        Company c2 = Company
                .builder()
                .name("sfgheaw")
                .email("seaFGDGfghw@gmail.com")
                .password("asdas")
                .build();

        Company c3 = Company
                .builder()
                .name("seafgdhw")
                .email("seafghdw@gmail.com")
                .password("sdad")
                .build();

        Company c4 = Company
                .builder()
                .name("sedhw")
                .email("sedfw@gmail.com")
                .password("sasdd")
                .build();

        Company c5 = Company
                .builder()
                .name("fdg")
                .email("sedfdgfw@gmail.com")
                .password("sagfsdd")
                .build();


        Coupon co1 = Coupon.builder()
                .category(Category.Electricity)
                .title("xiaomi poco")
                .description("15% off")
                .startDate(date)
                .endDate(endDate)
                .amount(1)
                .price(1.5)
                .image("image")
                .build();

        Coupon co2 = Coupon.builder()
                .category(Category.Food)
                .title("doritos")
                .description("10% off")
                .startDate(date)
                .endDate(endDate)
                .amount(4)
                .price(4.5)
                .image("image")
                .build();

        Coupon co3 = Coupon.builder()
                .category(Category.Food)
                .title("chips")
                .description("10% off")
                .startDate(date)
                .endDate(endDate)
                .amount(2)
                .price(2.5)
                .image("image")
                .build();

        Coupon co4 = Coupon.builder()
                .category(Category.Electricity)
                .title("chips")
                .description("5% off")
                .startDate(date)
                .endDate(endDate)
                .amount(3)
                .price(2.5)
                .image("image")
                .build();

        Coupon co5 = Coupon.builder()
                .category(Category.Electricity)
                .title("lg oled55inchtv")
                .description("10% off")
                .startDate(date)
                .endDate(expiredDate)
                .amount(5)
                .price(2.5)
                .image("image")
                .build();

        Customer cu1 = Customer.builder()
                .firstName("asd")
                .lastName("Asdasd")
                .email("eafghw@gmail.com")
                .password("asdsa")
                .build();

        Customer cu2 = Customer.builder()
                .firstName("gfdhasd")
                .lastName("Asddgfhasd")
                .email("eafdghghw@gmail.com")
                .password("Asdfdgas")
                .build();

        Customer cu3 = Customer.builder()
                .firstName("asdgfhd")
                .lastName("Asdgfhdasd")
                .email("eafdghjgfhghw@gmail.com")
                .password("Asddas")
                .build();

        Customer cu4 = Customer.builder()
                .firstName("asdgfhd")
                .lastName("Asdasd")
                .email("eafdgw@gmail.com")
                .password("dAsddfd")
                .build();

        Customer cu5 = Customer.builder()
                .firstName("asddgfhd")
                .lastName("Asdghasd")
                .email("fhghw@gmail.com")
                .password("Ashjh")
                .build();


        System.out.println("       " + "*********AdminLogin********");

        AdminService adminService = (AdminService) loginManager.login("admin@admin.com", "admin", ClientType.ADMINISTRATOR);
        System.out.println("*********InsertedCompanies/Customers/Coupons********");

        adminService.addCompanies(Arrays.asList(c1, c2, c3, c4, c5));
        System.out.println(Art.COMPANIES);
        System.out.println("*********addCompaniesToTheSystem********");
        adminService.getAllCompanies().forEach(System.out::println);


        System.out.println("  " +"*********addCompanyToTheSystem********");
        System.out.println("                " +"TEST FAILURE");
        try {
            adminService.addCompany(c2);
        } catch (CustomException e) {
            System.out.println("            " +e.getMessage());
        }


        adminService.addCustomers(Arrays.asList(cu1, cu2, cu3, cu4, cu5));
        System.out.println(Art.CUSTOMER);
        System.out.println("*********addCustomersToTheSystem********");
        adminService.getAllCustomers().forEach(System.out::println);

        couponRepo.saveAll(Arrays.asList(co1, co2, co3, co4, co5));
        System.out.println(Art.COUPONS);
        System.out.println("*********addCouponsToTheSystem********");
        couponRepo.findAll().forEach(System.out::println);

        System.out.println("        " + "*********ACTIONS********");
        System.out.println();
        System.out.println("*********updateCompanyAdminService********");
        c2.setName("IGotUpdated");
        adminService.updateCompany(c2);
        companyRepo.findAll().forEach(System.out::println);

        System.out.println("*********deleteCompanyAdminService********");
        adminService.deleteCompany(5);
        companyRepo.findAll().forEach(System.out::println);

        System.out.println("*********getAllCompaniesAdminService********");
        adminService.getAllCompanies().forEach(System.out::println);

        System.out.println("*********getOneCompanyAdminService********");
        System.out.println(adminService.getOneCompany(2));

        System.out.println("*********updateCustomerAdminService********");
        cu2.setFirstName("IGotUpdated");
        adminService.updateCustomer(cu2);
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("*********deleteCustomerAdminService********");
        adminService.deleteCustomer(3);
        adminService.getAllCustomers().forEach(System.out::println);

        System.out.println("  " +"*********deleteCustomerAdminService********");
        System.out.println("                " +"TEST FAILURE");
        try {
            adminService.deleteCustomer(7);
        } catch (CustomException e) {
            System.out.println("             " +e.getMessage());
        }



        System.out.println("*********getAllCustomersAdminService********");
        adminService.getAllCustomers();
        adminService.getAllCustomers().forEach(System.out::println);


        System.out.println("*********getOneCustomerAdminService********");
        System.out.println(adminService.getOneCustomer(2));

        System.out.println("       " + "*********CompanyLogin********");
        CompanyService companyService1 = (CompanyService) loginManager.login("seaFGDGfghw@gmail.com", "asdas", ClientType.COMPANY);

        System.out.println("*********addCouponsToCompany********");

        companyService1.addCoupon(co2);
        companyService1.addCoupon(co3);
        companyService1.getAllCoupons(c2).forEach(System.out::println);

        System.out.println("*********updateCouponCompanyService********");
        co2.setPrice(3.5);
        companyService1.updateCoupon(co2);
        companyService1.getAllCoupons(c2).forEach(System.out::println);

        System.out.println("*********getAllCouponsByCategory********");
        companyService1.getAllCouponsByCategory(Category.Food).forEach(System.out::println);

        System.out.println("*********getAllCouponsByMaxPrice********");
        companyService1.getAllCouponsByPrice(5.5).forEach(System.out::println);

        System.out.println("*********getCompanyDetails********");
        System.out.println(companyService1.getOneCompany(2));

        System.out.println("*********getAllCouponsByCompany********");
        System.out.println(companyService1.getAllCoupons(c2));

        System.out.println("       " + "*********CompanyLogin********");
        CompanyService companyService2 = (CompanyService) loginManager.login("sedfw@gmail.com", "sasdd", ClientType.COMPANY);
        System.out.println("*********addCouponsToCompany********");

        companyService2.addCoupon(co1);
        companyService2.addCoupon(co4);

        System.out.println("     " +"*********addCouponsToCompany********");
        System.out.println("                " +"TEST FAILURE");
        try {
            companyService2.addCoupon(co4);
        } catch (CustomException e) {
            System.out.println("            " +e.getMessage());
        }
        companyService2.getAllCoupons(c4).forEach(System.out::println);


        System.out.println("*********deleteCouponCompanyService********");

        companyService2.deleteCoupon(4);

        System.out.println("       " + "*********CustomerLogin********");
        CustomerService customerService = (CustomerService) loginManager.login("eafdgw@gmail.com", "dAsddfd", ClientType.CUSTOMER);

        System.out.println("*********CustomerPurchaseCoupon********");
        customerService.purchaseCoupon(co2);
        customerService.purchaseCoupon(co3);


        System.out.println("    " +"*********CustomerPurchaseCoupon********");
        System.out.println("                " +"TEST FAILURE");
        try {
            customerService.purchaseCoupon(co2);
        } catch (CustomException e) {
            System.out.println("       " +e.getMessage());
        }
        customerService.getAllCustomers().forEach(System.out::println);

        System.out.println("*********getAllPurchaseCouponByCustomerId********");
        customerService.getAllPurchaseCouponsByCustomerId(4).forEach(System.out::println);

        System.out.println("*********getAllPurchaseCouponByCategory********");
        customerService.getAllPurchaseCouponsByCategory(Category.Food).forEach(System.out::println);

        System.out.println("*********getAllPurchaseCouponByPrice********");
        customerService.getAllPurchaseCouponsByPrice(3).forEach(System.out::println);

        System.out.println("*********getCustomerDetails********");
        System.out.println(customerService.getCustomerDetails(4));




        System.out.println("     " + "*********CustomerLogin********");
        System.out.println("              " +"TEST FAILURE");
        try {
            CustomerService customerService1 = (CustomerService) loginManager.login("eafDSFSDFdgw@gmail.com", "dAsddfDSFSDd", ClientType.CUSTOMER);
        } catch (CustomException e) {
            System.out.println("           " +e.getMessage());
        }


        System.out.println("*********ListExpiredCoupons********");
        couponRepo.findAllExpiredCoupon().forEach(System.out::println);

    }


}
