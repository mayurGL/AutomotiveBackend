package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.CompanyHistorical;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

/*
 * Repository class for company historical entity
 */
@Repository
public interface CompanyHistoricalRepository extends CassandraRepository<CompanyHistorical, UUID> {
}