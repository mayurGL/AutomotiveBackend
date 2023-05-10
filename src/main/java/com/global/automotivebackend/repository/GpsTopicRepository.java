package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.GpsData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

import java.util.UUID;

@Repository
public interface GpsTopicRepository extends CassandraRepository<GpsData, UUID> {
}
