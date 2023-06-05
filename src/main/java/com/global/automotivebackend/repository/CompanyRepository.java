package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Company;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository class for company entity
 */
@Repository
public interface CompanyRepository extends CassandraRepository<Company, Integer> {
}
