package com.xworkz.upgrade.dao;

import com.xworkz.upgrade.entity.UpGradeEntity;

public interface UpGradeDAO {
    boolean saveUpGradeData(UpGradeEntity upGradeEntity);
    UpGradeEntity isEmailExist(String email);

    UpGradeEntity validateLogin(String email);

}
