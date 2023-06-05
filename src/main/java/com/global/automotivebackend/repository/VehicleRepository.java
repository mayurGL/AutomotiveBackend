package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Vehicle;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository class for vehicle entity
 */
@Repository
public interface VehicleRepository extends CassandraRepository<Vehicle, Integer> {
}