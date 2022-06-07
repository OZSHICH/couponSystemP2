package com.OzProject.couponSystem.servies;

import com.OzProject.couponSystem.beans.*;
import com.OzProject.couponSystem.exception.*;

import java.util.*;

public interface AdminService {
    boolean login(String email, String password);

    void addCompany(Company company) throws CustomException;

    void addCompanies(List<Company> companies) throws CustomException;

    void updateCompany(Company company) throws CustomException;

    void deleteCompany(int companyId) throws CustomException;

    List<Company> getAllCompanies();

    Company getOneCompany(int id) throws CustomException;

    void addCustomer(Customer customer) throws CustomException;

    void addCustomers(List<Customer> customers) throws CustomException;

    void updateCustomer(Customer customer) throws CustomException;

    void deleteCustomer(int customerId) throws CustomException;

    List<Customer> getAllCustomers();

    Customer getOneCustomer(int customerId) throws CustomException;
}
