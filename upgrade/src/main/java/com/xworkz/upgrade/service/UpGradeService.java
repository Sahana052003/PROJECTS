package com.xworkz.upgrade.service;

import com.xworkz.upgrade.dto.UpGradeDTO;

public interface UpGradeService {

    String loginData(UpGradeDTO upGradeDTO);
    UpGradeDTO checkEmailExist(String email);


   boolean verifyLogin(String email,String password);


}
