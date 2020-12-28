package com.botscrew;

import com.botscrew.dao.UniversityDao;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.util.List;


@Component
public class CommandExecutor {

    @Autowired
    private UniversityDao dao;

    public String execute(String query){
        StringBuilder result;

       if(query.startsWith("Who is head of department ")){
           String str = query.substring(26).trim();
           result = new StringBuilder(dao.getHeadOfDepartment(str));
           return result.toString();
       }else if(query.startsWith("Show the average salary for the department ")){
           String str = query.substring(43).trim();
           result = new StringBuilder(dao.getAverageSalaryOfDepartment(str)+"");
           return result.toString();
       }else if(query.startsWith("Show count of employee for ")){
           String str = query.substring(27).trim();
           result = new StringBuilder(dao.getCountOfEmployeeOfDepartment(str)+"");
           return result.toString();
       }else if(query.startsWith("Show ") && query.endsWith(" statistics.")){
           String str = query.substring(5,query.lastIndexOf(" statistics.")).trim();
           result = new StringBuilder(dao.getDepartmentStatistic(str));
           return result.toString();
       }else if(query.startsWith("Global search by ")){
           String str = query.substring(17).trim();
           List<String> list = dao.globalSearch(str);
           return list.toString();
       }

        return "incorrect query";
    }
}
