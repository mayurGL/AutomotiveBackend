package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.DeviceHistorical;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface DeviceHistoricalRepository extends CassandraRepository<DeviceHistorical, UUID> {
}
