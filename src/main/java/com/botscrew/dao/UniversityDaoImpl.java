package com.botscrew.dao;

import com.botscrew.dao.daoFactory.DaoFactory;
import org.springframework.stereotype.Component;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

@Component
public class UniversityDaoImpl implements UniversityDao {

    @Override
    public String getHeadOfDepartment(String department){
        String query = "select * from university.departaments where departaments.name='"+department+"'";

        Connection connection = null;

        Statement statement = null;
        ResultSet resultSet;
        try {
             connection = DaoFactory.getConnection();
             statement = connection.createStatement();
             resultSet = statement.executeQuery(query);
             resultSet.next();
             return resultSet.getString("head");

        }catch (SQLException e){
            return "No such department";
        }finally {
            try {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }

        }

    }

    @Override
    public String getDepartmentStatistic(String department){
        String query = "select (lectors.degree) from university.lectors where lectors.departament='"+department+"'";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = DaoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            int assistants =0;
            int associate_professors =0;
            int professors = 0;

            while(resultSet.next()){
                if(resultSet.getString("degree").equals("assistant"))assistants++;
                if(resultSet.getString("degree").equals("associate professor"))associate_professors++;
                if(resultSet.getString("degree").equals("professor"))professors++;
            }

            return "assistants - " + assistants +"\n"+
            "associate professors - " + associate_professors +"\n"+
            "professors - " + professors;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }

    @Override
    public double getAverageSalaryOfDepartment(String department){
        String query = "select (university.lectors.salary) from university.lectors where departament='"+department+"'";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = DaoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            int sum=0;
            int count=0;
            while(resultSet.next()){
                count++;
                sum+=Integer.parseInt(resultSet.getString("salary"));
            }

            return sum/count;

        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public int getCountOfEmployeeOfDepartment(String department){
        String query = "select * from university.lectors where lectors.departament='"+department+"'";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;

        try {
            connection = DaoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);

            int count = 0;
            while(resultSet.next()){
                count++;
            }

            return count;


        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }
        return 0;
    }

    @Override
    public List<String> globalSearch(String template){
        String query = "select university.lectors.name from university.lectors where regexp_like(university.lectors.name,'"+template+"','i')";
        Connection connection = null;
        Statement statement = null;
        ResultSet resultSet;
        try {
            connection = DaoFactory.getConnection();
            statement = connection.createStatement();
            resultSet = statement.executeQuery(query);
            List<String> list = new ArrayList<>();
            while(resultSet.next()){
                list.add(resultSet.getString("name"));
            }
            return list;
        }catch (SQLException e){
            e.printStackTrace();
        }finally {
            try {
                assert connection != null;
                connection.close();
                assert statement != null;
                statement.close();
            }catch (SQLException e){
                e.printStackTrace();
            }
        }

        return null;
    }
}
