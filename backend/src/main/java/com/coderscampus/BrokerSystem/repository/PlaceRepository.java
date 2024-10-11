package com.coderscampus.BrokerSystem.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.coderscampus.BrokerSystem.domain.Place;

public interface PlaceRepository extends JpaRepository<Place,Long> {
    
}
