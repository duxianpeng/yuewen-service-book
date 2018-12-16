package com.yuewen.service;

import com.yuewen.entity.Flight;
import com.yuewen.repository.FlightRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.PageRequest;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
@Transactional
public class FlightServiceImpl implements  FlightService {

    @Autowired
    private FlightRepository flightRepository;

    @Override
    public Flight save(Flight flight) {
        return flightRepository.save(flight);
    }

    @Override
    public Page<Flight> findByCategory(String category, int page, int pageSize) {
        return flightRepository.findByCategory(category, new PageRequest(page, pageSize));
    }
}
