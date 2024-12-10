package com.example.education_centre.service.district;

import com.example.education_centre.model.District;
import com.example.education_centre.repository.DistrictRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class DIstrictServiceImpl implements DistrictService{
    @Autowired
    DistrictRepository districtRepository;

    @Override
    public List<District> findAll() {
        return districtRepository.findAll();
    }

    @Override
    public List<District> findByProvince_Code(String provinceCode) {
        return districtRepository.findByProvince_Code(provinceCode);
    }
}
