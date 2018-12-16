package com.yuewen.service;

import com.yuewen.entity.Flight;
import org.springframework.data.domain.Page;

public interface FlightService {

     Flight save(Flight flight);
     Page<Flight> findByCategory(String category, int page, int pageSize);

}
