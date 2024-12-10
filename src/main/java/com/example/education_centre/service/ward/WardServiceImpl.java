package com.example.education_centre.service.ward;

import com.example.education_centre.model.Ward;
import com.example.education_centre.repository.WardRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class WardServiceImpl implements WardService{
    @Autowired
    WardRepository wardRepository;

    @Override
    public List<Ward> findAll() {
        return wardRepository.findAll();
    }

    @Override
    public List<Ward> findByDistrict_Code(String districtCode) {
        return wardRepository.findByDistrict_Code(districtCode);
    }
}
