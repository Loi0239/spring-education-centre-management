package com.example.education_centre.controller;

import com.example.education_centre.model.District;
import com.example.education_centre.model.Province;
import com.example.education_centre.model.Ward;
import com.example.education_centre.service.district.DistrictService;
import com.example.education_centre.service.province.ProvinceService;
import com.example.education_centre.service.ward.WardService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping("/api")
public class LocationController {
    @Autowired
    private ProvinceService provinceService;
    @Autowired
    private DistrictService districtService;
    @Autowired
    private WardService wardService;

    @GetMapping("/province")
    public List<Province> getProvince(){
        return provinceService.findAll();
    }

    @GetMapping("/district")
    public List<District> getDistrict(@RequestParam("id")String provinceCode){
        return districtService.findByProvince_Code(provinceCode);
    }

    @GetMapping("/ward")
    public List<Ward> getWard(@RequestParam("id")String districtCode){
        return wardService.findByDistrict_Code(districtCode);
    }
}
