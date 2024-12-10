package com.example.education_centre.service.district;

import com.example.education_centre.model.District;

import java.util.List;

public interface DistrictService {
    List<District> findAll();
    List<District> findByProvince_Code(String provinceCode);
}
