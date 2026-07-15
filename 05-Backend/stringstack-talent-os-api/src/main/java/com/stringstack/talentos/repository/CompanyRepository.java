package com.stringstack.talentos.repository;

import com.stringstack.talentos.entity.Company;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CompanyRepository extends JpaRepository<Company,Long>{

    boolean existsByCompanyCode(String companyCode);

    boolean existsByCompanyName(String companyName);

    Optional<Company> findByCompanyCode(String companyCode);

}