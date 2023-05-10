package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.DeviceData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DeviceTopicRepository extends CassandraRepository<DeviceData,String> {
}
