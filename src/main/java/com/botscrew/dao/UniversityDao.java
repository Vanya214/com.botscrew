package com.botscrew.dao;



import java.util.List;

public interface UniversityDao {

     String getHeadOfDepartment(String department);
     String getDepartmentStatistic(String department);
     double getAverageSalaryOfDepartment(String department);
     int getCountOfEmployeeOfDepartment(String department);
     List<String> globalSearch(String template);

}
