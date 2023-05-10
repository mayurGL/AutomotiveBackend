package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.VehicleData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTopicRepository extends CassandraRepository<VehicleData,String> {
}
