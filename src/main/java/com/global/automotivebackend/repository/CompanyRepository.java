package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Company;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface CompanyRepository extends CassandraRepository <Company, Instant>{

    @Query
    @AllowFiltering
    Optional<Company> findByCompanyId(String companyId);

    @Query
    @Transactional
    @AllowFiltering
    void deleteByCompanyId(String companyId);

}
