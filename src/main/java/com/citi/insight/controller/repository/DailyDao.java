package com.citi.insight.controller.repository;

import com.citi.insight.controller.model.Daily;
import org.springframework.stereotype.Repository;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

@Repository
public class DailyDao {
    //TODO: Fill in the fields for SQL_SERVER_CONNECTION and test (Check with MySQL workbench)
    private static final String SQL_SERVER_CONNECTION = "jdbc:mysql://localhost:3306/stockschema/?autoReconnect=true&useSSL=false";

    private static final String SQL_RETURN_CONDITIONAL_TEST = "SELECT * FROM stock limit 5";

    private static final String SQL_RETURN_ALL_TESTS = "SELECT * FROM stock";

    public List<Daily> returnTestByFieldAndValue(String field, String value) throws SQLException {

        List<Daily> dailyModels = new ArrayList<>();

        Connection connection = DriverManager.getConnection(SQL_SERVER_CONNECTION);

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_RETURN_CONDITIONAL_TEST);

        preparedStatement.setString(0, field);
        preparedStatement.setString(1, value);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Daily daily = new Daily();
            daily.setTicker(resultSet.getString(1));
            daily.setOpenPrice(resultSet.getDouble(2));
            daily.setHighPrice(resultSet.getDouble(3));
            daily.setLowPrice(resultSet.getDouble(4));
            daily.setClosePrice(resultSet.getDouble(5));
            daily.setVolume(resultSet.getInt(6));

            dailyModels.add(daily);
            //TODO: check that these are parsing correctly, else refactor
        }

        preparedStatement.close();
        connection.close();
        resultSet.close();

        return dailyModels;
    }

    public List<Daily> returnAllTests() throws SQLException{
        List<Daily> dailyModels = new ArrayList<>();

        Connection connection = DriverManager.getConnection(SQL_SERVER_CONNECTION);

        PreparedStatement preparedStatement = connection.prepareStatement(SQL_RETURN_ALL_TESTS);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Daily daily = new Daily();
            daily.setTicker(resultSet.getString(1));
            daily.setOpenPrice(resultSet.getDouble(2));
            daily.setHighPrice(resultSet.getDouble(3));
            daily.setLowPrice(resultSet.getDouble(4));
            daily.setClosePrice(resultSet.getDouble(5));
            daily.setVolume(resultSet.getInt(6));


            dailyModels.add(daily);
        }

        preparedStatement.close();
        connection.close();
        resultSet.close();

        return dailyModels;
    }


}
