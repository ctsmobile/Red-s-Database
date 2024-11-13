package com.cts.redplastring.redplastringapplication.repository;

import com.cts.redplastring.redplastringapplication.model.TagDetails;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface TagDetailsRepository extends JpaRepository<TagDetails,Integer> {

}
