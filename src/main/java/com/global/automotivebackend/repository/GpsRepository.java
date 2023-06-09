package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Gps;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository class for gps entity
 */
@Repository
public interface GpsRepository extends CassandraRepository<Gps, String> {
}
