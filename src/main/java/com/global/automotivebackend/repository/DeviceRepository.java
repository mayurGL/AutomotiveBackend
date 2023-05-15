package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.Device;
import org.springframework.data.cassandra.repository.AllowFiltering;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.data.cassandra.repository.Query;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.Instant;
import java.util.List;
import java.util.Optional;

@Repository
public interface DeviceRepository extends CassandraRepository<Device, String> {

    @AllowFiltering
    Optional<List<Device>> findByDeviceId(String deviceId);

}
