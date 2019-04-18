package com.citi.insight.controller.controller;

import com.citi.insight.controller.model.Daily;
import com.citi.insight.controller.repository.DailyDao;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.sql.SQLException;
import java.util.List;
@RequestMapping("/daily")
@RestController
public class DailyController {

         private static DailyDao dailyDao = new DailyDao();

        @RequestMapping("/returnAllStocks")
        @ResponseBody
        public List<Daily> returnAllTests() throws SQLException {
            return dailyDao.returnAllTests();
        }

        @RequestMapping("/returnTestsWhere/{field}/equals/{value}")
        @ResponseBody
        public List<Daily> returnTestsByFieldAndValue(@PathVariable("field") String field, @PathVariable("value") String value) throws SQLException{
            return dailyDao.returnTestByFieldAndValue(field, value);
        }

    }

