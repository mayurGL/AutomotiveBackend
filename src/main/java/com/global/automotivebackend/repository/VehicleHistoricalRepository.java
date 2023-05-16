package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.VehicleHistorical;
import org.springframework.data.cassandra.repository.CassandraRepository;

import java.util.UUID;

public interface VehicleHistoricalRepository extends CassandraRepository<VehicleHistorical, UUID> {
}
