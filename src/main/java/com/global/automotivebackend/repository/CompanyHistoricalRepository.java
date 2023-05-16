package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Company;
import com.global.automotivebackend.model.CompanyHistorical;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface CompanyHistoricalRepository extends CassandraRepository<CompanyHistorical, UUID> {
}