package com.cts.redplastring.redplastringapplication.repository;

import com.cts.redplastring.redplastringapplication.model.TravelDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface TravelDetailRepository extends JpaRepository<TravelDetails, Integer> {
    TravelDetails findByEmployeeIdAndCreatedDateContainingAndTravelOutIsNull(String employeeId, String format);

    List<TravelDetails> findByEmployeeIdAndCreatedDateContaining(String employeeId, String format);

    List<TravelDetails> findByEmployeeId(String id);

    @Query(value = "SELECT * FROM `travel_details` WHERE employee_id = :employeeId AND `created_date` like Concat('%',:format,'%')", nativeQuery = true)
    TravelDetails findByEmployeeIdAndCreatedDate(String employeeId, String format);
}
