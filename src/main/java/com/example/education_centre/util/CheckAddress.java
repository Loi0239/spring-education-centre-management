package com.example.education_centre.util;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.List;

public class CheckAddress {

    public static String[] checkandfilterAddress(String[] addressParts){
        String province = "";
        String district = "";
        String ward = "";
        String detailedAddress = "";

        if (addressParts.length > 0) {
            // Kiểm tra nếu phần tử đầu tiên (province) có thể là "address" hay không
            if (!addressParts[0].contains("Thành phố") && !addressParts[0].contains("Tỉnh")) {
                detailedAddress = addressParts[0]; // Nếu không phải "province", đây là địa chỉ chi tiết
            } else {
                province = addressParts[0];
            }
        }

        if (addressParts.length > 1) {
            // Kiểm tra nếu phần tử thứ hai (district) có thể là "address" hay không
            if (!addressParts[1].contains("Quận") && !addressParts[1].contains("Huyện")) {
                detailedAddress = addressParts[1]; // Nếu không phải "district", đây là địa chỉ chi tiết
            } else {
                district = addressParts[1];
            }
        }

        if (addressParts.length > 2) {
            // Kiểm tra nếu phần tử thứ ba (ward) có thể là "address" hay không
            if (!addressParts[2].contains("Phường") && !addressParts[2].contains("Xã")) {
                detailedAddress = addressParts[2]; // Nếu không phải "ward", đây là địa chỉ chi tiết
            } else {
                ward = addressParts[2];
            }
        }

        // Nếu mảng có phần tử thứ tư, đó chắc chắn là địa chỉ chi tiết
        if (addressParts.length > 3) {
            detailedAddress = addressParts[3];
        }

        return new String[]{province, district, ward, detailedAddress};
    }

}
