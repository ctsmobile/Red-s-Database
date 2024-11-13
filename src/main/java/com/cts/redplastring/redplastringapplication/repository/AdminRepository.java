package com.cts.redplastring.redplastringapplication.repository;

import com.cts.redplastring.redplastringapplication.model.AdminDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface AdminRepository extends JpaRepository<AdminDetails,String> {

    AdminDetails findByEmail(String email);

    AdminDetails findByUserId(String userId);
}
