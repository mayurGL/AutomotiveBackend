package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Device;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

/*
 * Repository class for device entity
 */
@Repository
public interface DeviceRepository extends CassandraRepository<Device, Integer> {
}