package com.cts.redplastring.redplastringapplication.repository;

import com.cts.redplastring.redplastringapplication.model.ConfigDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface ConfigDetailsRepository  extends JpaRepository<ConfigDetails, Integer> {
    ConfigDetails findByType(String firebaseServerKey);
}
