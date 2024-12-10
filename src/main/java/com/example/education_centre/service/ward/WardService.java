package com.example.education_centre.service.ward;

import com.example.education_centre.model.District;
import com.example.education_centre.model.Ward;

import java.util.List;

public interface WardService {
    List<Ward> findAll();
    List<Ward> findByDistrict_Code(String districtCode);
}
