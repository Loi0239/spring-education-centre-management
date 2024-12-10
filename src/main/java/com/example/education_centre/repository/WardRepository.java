package com.example.education_centre.repository;

import com.example.education_centre.model.District;
import com.example.education_centre.model.Ward;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface WardRepository extends JpaRepository<Ward, String> {
    List<Ward> findByDistrict_Code(String districtCode);
}
