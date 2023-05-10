package com.global.automotivebackend.repository;

import com.global.automotivebackend.model.CompanyData;
import org.springframework.data.cassandra.repository.CassandraRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CompanyTopicRepository extends CassandraRepository <CompanyData,String>{
}
