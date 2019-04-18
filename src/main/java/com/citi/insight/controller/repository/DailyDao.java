package com.citi.insight.controller.repository;

import com.citi.insight.controller.model.Daily;
import org.springframework.stereotype.Repository;

import java.math.BigDecimal;
import java.math.RoundingMode;
import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

@Repository
public class DailyDao {
    //TODO: Fill in the fields for SQL_SERVER_CONNECTION and test (Check with MySQL workbench)
    private static final String SQL_SERVER_CONNECTION = "jdbc:mysql://localhost:3306/stockdatabase?autoReconnect=true&useSSL=false";

    private static final String SQL_RETURN_BY_DAY = "select ticker, open, high, low, close, vol, (close - open) " +
            "as absoluteDiff, (((close - open) / open) * 100) as percentageDiff " +
            "from nasdaq_20160314 WHERE date = ? " +
            "order by absoluteDiff";

    private static final String SQL_RETURN_ALL_TESTS = "SELECT * FROM stock";

//    public List<Daily> returnTestByFieldAndValue(String field, String value) throws SQLException {
//
//        List<Daily> dailyModels = new ArrayList<>();
//
//        Connection connection = DriverManager.getConnection(SQL_SERVER_CONNECTION);
//
//        PreparedStatement preparedStatement = connection.prepareStatement(SQL_RETURN_CONDITIONAL_TEST);
//
//        preparedStatement.setString(0, field);
//        preparedStatement.setString(1, value);
//
//        ResultSet resultSet = preparedStatement.executeQuery();
//
//        while(resultSet.next()){
//            Daily daily = new Daily();
//            daily.setTicker(resultSet.getString(1));
//            daily.setOpenPrice(resultSet.getDouble(2));
//            daily.setHighPrice(resultSet.getDouble(3));
//            daily.setLowPrice(resultSet.getDouble(4));
//            daily.setClosePrice(resultSet.getDouble(5));
//            daily.setVolume(resultSet.getInt(6));
//
//            dailyModels.add(daily);
//            //TODO: check that these are parsing correctly, else refactor
//        }
//
//        preparedStatement.close();
//        connection.close();
//        resultSet.close();
//
//        return dailyModels;
//    }

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


    public LinkedList<Daily> returnStocksByDay(String day, Boolean best) throws SQLException {

        LinkedList<Daily> stocks = new LinkedList<>();

        DriverManager.registerDriver(new com.mysql.jdbc.Driver());
        Connection connection = DriverManager.getConnection(SQL_SERVER_CONNECTION, "root", "c0nygre");

        String ascOrDesc = null;

        if(best){
            ascOrDesc = " desc limit 5";
        } else {
            ascOrDesc = " asc limit 5";
        }

        System.out.println(ascOrDesc);

        String fullSql = SQL_RETURN_BY_DAY + ascOrDesc;

        PreparedStatement preparedStatement = connection.prepareStatement(fullSql);

        preparedStatement.setString(1, day);
//        preparedStatement.setString(2, bestOrWorst);

        ResultSet resultSet = preparedStatement.executeQuery();

        while(resultSet.next()){
            Daily stock = new Daily();
            stock.setTicker(resultSet.getString("ticker"));
            stock.setOpenPrice(resultSet.getDouble("open"));
            stock.setClosePrice(resultSet.getDouble("close"));
            stock.setLowPrice(resultSet.getDouble("low"));
            stock.setHighPrice(resultSet.getDouble("high"));
            stock.setVolume(resultSet.getInt("vol"));
            stock.setAbsoluteDifference(round(resultSet.getDouble("absoluteDiff"),2));
            stock.setPercentageDifference(round(resultSet.getDouble("percentageDiff"),2));

            stocks.add(stock);
        }

        preparedStatement.close();
        connection.close();
        resultSet.close();

        return stocks;
    }

    public static double round(double value, int places) {
        if (places < 0) throw new IllegalArgumentException();

        BigDecimal bd = new BigDecimal(value);
        bd = bd.setScale(places, RoundingMode.HALF_UP);
        return bd.doubleValue();
    }
}
