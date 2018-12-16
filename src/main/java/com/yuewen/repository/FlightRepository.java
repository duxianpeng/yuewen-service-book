package com.yuewen.repository;

import com.yuewen.entity.Flight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.elasticsearch.repository.ElasticsearchRepository;

import java.util.List;

public interface FlightRepository extends ElasticsearchRepository<Flight, String> {


    Page<Flight> findByCategory(String category, Pageable pageRequest);



}
