package com.OzProject.couponSystem.repository;

import com.OzProject.couponSystem.beans.*;
import org.springframework.data.jpa.repository.*;
import org.springframework.stereotype.*;

@Repository
public interface CompanyRepo extends JpaRepository<Company, Integer> {


    boolean existsByEmail(String email);

    boolean existsByEmailAndPassword(String email, String password);

    Company findByEmail(String email);

    boolean existsByName(String name);


}

