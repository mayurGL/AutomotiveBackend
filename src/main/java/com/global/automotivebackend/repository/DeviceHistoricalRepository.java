package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.CompanyHistorical;
import com.global.automotivebackend.model.DeviceHistorical;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface DeviceHistoricalRepository extends CassandraRepository<DeviceHistorical, UUID> {
}
