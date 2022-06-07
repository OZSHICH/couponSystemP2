package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;
import org.springframework.stereotype.*;

import java.util.*;

@Service

public class AdminServiceImpl extends ClientService implements AdminService {


    @Override
    public boolean login(String email, String password) {
        System.out.println("*********The Admin Has Logged in ********");
        return email.equals("admin@admin.com") && password.equals("admin");
    }

    @Override
    public void addCompany(Company company) throws CustomException {
        if (companyRepo.existsByEmail(company.getEmail()) || companyRepo.existsByName(company.getName())) {
            throw new CustomException(ErrorMessage.COMPANY_ALREADY_EXIST);
        }
        companyRepo.save(company);
    }

    @Override
    public void addCompanies(List<Company> companies) throws CustomException {
        for (Company company : companies) {
            if (companyRepo.existsByName(company.getName()) || (companyRepo.existsByEmail(company.getEmail()))) {
                throw new CustomException(ErrorMessage.COMPANY_ALREADY_EXIST);
            }
        }
        companyRepo.saveAllAndFlush(companies);
    }


    @Override
    public void updateCompany(Company company) throws CustomException {
        if (!companyRepo.existsById(company.getId())) {
            throw new CustomException(ErrorMessage.COMPANY_NOT_EXIST);
        }
        company.setId(company.getId());
        companyRepo.saveAndFlush(company);
    }

    @Override
    public void deleteCompany(int companyId) throws CustomException {
        if (!companyRepo.existsById(companyId)) {
            throw new CustomException(ErrorMessage.COMPANY_NOT_EXIST);
        }

        companyRepo.deleteById(companyId);
    }

    @Override
    public List<Company> getAllCompanies() {
        return companyRepo.findAll();
    }


    @Override
    public Company getOneCompany(int id) throws CustomException {
        return companyRepo.findById(id).orElseThrow(()->new CustomException(ErrorMessage.COMPANY_NOT_EXIST));
    }

    @Override
    public void addCustomer(Customer customer) throws CustomException {
        if (customerRepo.existsByEmail(customer.getEmail())) {
            throw new CustomException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
        }
        customerRepo.save(customer);
    }

    @Override
    public void addCustomers(List<Customer> customers) throws CustomException {
        for (Customer customer : customers) {
            if (customerRepo.existsByEmail(customer.getEmail())) {
                throw new CustomException(ErrorMessage.CUSTOMER_ALREADY_EXIST);
            }
        }
        customerRepo.saveAllAndFlush(customers);
    }

    @Override
    public void updateCustomer(Customer customer) throws CustomException {
        if (!customerRepo.existsById(customer.getId())) {
            throw new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST);
        }
        customer.setId(customer.getId());
        customerRepo.saveAndFlush(customer);

    }

    @Override
    public void deleteCustomer(int customerId) throws CustomException {
        if (!customerRepo.existsById(customerId)) {
            throw new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST);
        }
        customerRepo.deleteById(customerId);
    }

    @Override
    public List<Customer> getAllCustomers() {
        return customerRepo.findAll();
    }

    @Override
    public Customer getOneCustomer(int customerId) throws CustomException {
        return customerRepo.findById(customerId).orElseThrow(() -> new CustomException(ErrorMessage.CUSTOMER_NOT_EXIST));
    }

}
