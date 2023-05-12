package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Vehicle;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.Optional;

@Repository
public interface VehicleRepository extends CassandraRepository<Vehicle, Instant> {

    @Query
    @AllowFiltering
    Optional<Vehicle> findByVehicleId(String vehicleId);

    @Query
    @Transactional
    @AllowFiltering
    void deleteByVehicleId(String vehicleId);
}
