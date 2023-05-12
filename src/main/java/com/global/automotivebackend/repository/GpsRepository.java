package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Gps;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GpsRepository extends CassandraRepository<Gps, UUID> {
}
