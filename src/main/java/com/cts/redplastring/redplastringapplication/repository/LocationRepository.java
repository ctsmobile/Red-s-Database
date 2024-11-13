package com.cts.redplastring.redplastringapplication.repository;

import com.cts.redplastring.redplastringapplication.model.LocationInfo;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface LocationRepository extends JpaRepository<LocationInfo,Integer>{

    List<LocationInfo> findByDeleted(boolean b);
}
