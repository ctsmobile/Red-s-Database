package com.cts.redplastring.redplastringapplication.repository;

import com.cts.redplastring.redplastringapplication.model.TimeTracking;
import io.lettuce.core.dynamic.annotation.Param;
import lombok.Data;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.Date;
import java.util.List;

@Repository
public interface TimeTrackingRepository extends JpaRepository<TimeTracking,Integer> {

    @Query(value = "SELECT * FROM `time_tracking` WHERE employee_id = :employeeId AND date(created_date) = :logindate", nativeQuery = true)
    TimeTracking findByEmployeeIdAndCreatedDateContaining(@Param("employeeId") String employeeId, @Param("logindate") String logindate);

    List<TimeTracking> findByEmployeeId(String id);

    List<TimeTracking> findByCreatedDateContaining(String format);

    List<TimeTracking> findByEmployeeId(String employeeId, Sort id);

    List<TimeTracking> findByCreatedDateContainingAndClockOutIsNull(String format);

    List<TimeTracking> findByJobId(Integer jobType);

    List<TimeTracking> findByEmployeeIdAndJobIdAndClockInLocationAndCreatedDateContaining(String employeeId, Integer jobId, Integer locationId, String date);

    List<TimeTracking> findByEmployeeIdAndJobIdAndCreatedDateContaining(String employeeId, Integer jobId, String date);

    List<TimeTracking> findByJobIdAndClockInLocationAndCreatedDateContaining(Integer jobId, Integer locationId, String date);

    List<TimeTracking> findByJobIdAndCreatedDateContaining(Integer jobId, String date);

    List<TimeTracking> findByEmployeeIdAndClockInLocationAndCreatedDateContaining(String employeeId, Integer locationId, String date);

    List<TimeTracking> findByClockInLocationAndCreatedDateContaining(Integer locationId, String date);

    List<TimeTracking> findByEmployeeIdAndJobIdAndClockInLocationAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(String employeeId, Integer jobId, Integer locationId, Date startDate, Date endDate);

    List<TimeTracking> findByEmployeeIdAndJobIdAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(String employeeId, Integer jobId, Date startDate, Date endDate);

    List<TimeTracking> findByJobIdAndClockInLocationAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(Integer jobId, Integer locationId, Date startDate, Date endDate);

    List<TimeTracking> findByJobIdAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(Integer jobId, Date startDate, Date endDate);

    List<TimeTracking> findByEmployeeIdAndClockInLocationAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(String employeeId, Integer locationId, Date startDate, Date endDate);

    List<TimeTracking> findByEmployeeIdAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(String employeeId, Date startDate, Date endDate);

    List<TimeTracking> findByClockInLocationAndCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(Integer locationId, Date startDate, Date endDate);

    List<TimeTracking> findByCreatedDateGreaterThanEqualAndCreatedDateLessThanEqual(Date startDate, Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE employee_id = :employeeId AND job_id = :jobId AND clock_in_location = :locationId AND created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByEmployeeIdAndJobIdAndClockInLocation(@Param("employeeId") String employeeId, @Param("jobId") Integer jobId, @Param("locationId") Integer locationId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE employee_id = :employeeId AND job_id = :jobId AND created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByEmployeeIdAndJobId(@Param("employeeId") String employeeId, @Param("jobId") Integer jobId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE clock_in_location = :locationId AND job_id = :jobId AND created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByJobIdAndClockInLocation(@Param("jobId") Integer jobId, @Param("locationId") Integer locationId, @Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE job_id = :jobId AND created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByJobId(@Param("jobId") Integer jobId,@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE employee_id = :employeeId AND clock_in_location = :locationId AND created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByEmployeeIdAndClockInLocation(@Param("employeeId") String employeeId, @Param("locationId") Integer locationId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE employee_id = :employeeId AND created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByEmployeeId(@Param("employeeId") String employeeId,@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE clock_in_location = :locationId AND created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByClockInLocation(@Param("locationId") Integer locationId,@Param("startDate") Date startDate, @Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE created_date BETWEEN :startDate AND :endDate",nativeQuery = true)
    List<TimeTracking> findByDate(@Param("startDate") Date startDate,@Param("endDate") Date endDate);

    @Query(value = "SELECT * FROM `time_tracking` WHERE employee_id = :employeeId AND `created_date` like Concat('%',:format,'%')", nativeQuery = true)
    TimeTracking findByEmployeeIdAndCreatedDate(String employeeId, String format);

    List<TimeTracking> findByClockOutIsNull();
}
