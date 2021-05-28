package com.rjgc.utils;

import com.rjgc.configs.cas.MinConcurrentLatency;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Timestamp;
import java.time.LocalDateTime;

@Component
public class CASUtils {

    @Autowired
    private DBUtils dbUtils;

    @Autowired
    private MinConcurrentLatency minConcurrentLatency;

    public Timestamp getUpdateTime(String tableName, int id) {
        try (PreparedStatement preparedStatement = dbUtils.getPrepareStatement("SELECT update_time from " + tableName + " where id=" + id)) {
            if (preparedStatement != null) {
                try (ResultSet resultSet = preparedStatement.executeQuery()) {
                    if (resultSet.next()) {
                        return resultSet.getTimestamp("update_time");
                    }
                }
            }
        } catch (SQLException throwables) {
            throwables.printStackTrace();
        }
        return null;
    }

    private void setUpdateTime(String tableName, int id) {
        dbUtils.executeSql("UPDATE " + tableName + " set update_time=" + "'" + Timestamp.valueOf(LocalDateTime.now()) + "'" + " where id=" + id);
    }

    public boolean compareAndSet(Timestamp timestamp, String tableName, int id) {
        try {
            Thread.sleep(minConcurrentLatency.getLatency());
        } catch (InterruptedException ignored) {
        }
        Timestamp updateTime = getUpdateTime(tableName, id);
        if (timestamp == null || timestamp.equals(updateTime)) {
            setUpdateTime(tableName, id);
            return true;
        }
        return false;
    }
}
