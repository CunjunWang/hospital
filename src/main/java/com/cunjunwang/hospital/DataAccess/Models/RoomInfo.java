package com.cunjunwang.hospital.DataAccess.Models;

import com.cunjunwang.hospital.DataAccess.Dao;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.List;

/**
 * Created by CunjunWang on 16/10/12.
 */
public class RoomInfo {

    private int room_number;
    private int room_p_ID;
    private boolean room_is_assigned;

    // default constructor
    public RoomInfo(){}

    // mininal constructor
    public RoomInfo(int room_number){
        this.room_number = room_number;
    }

    // full constructor
    public RoomInfo(int room_number, int room_p_ID, boolean room_is_assigned){
        this.room_number = room_number;
        this.room_p_ID = room_p_ID;
        this.room_is_assigned = room_is_assigned;
    }

    public Integer getRoom_number() {
        return room_number;
    }

    public int getRoom_p_ID() {
        return room_p_ID;
    }

    public boolean getRoom_is_assigned() {
        return room_is_assigned;
    }

    public void setRoom_number(int room_number) {
        this.room_number = room_number;
    }

    public void setRoom_p_ID(int room_p_ID) {
        this.room_p_ID = room_p_ID;
    }

    public void setRoom_is_assigned(boolean room_is_assigned) {
        this.room_is_assigned = room_is_assigned;
    }

    public static RoomInfo getRoomInfo(List priKeyList) {
        String where = "room_number='" + priKeyList.get(0) + "'";

        RoomInfo roomInfo = new RoomInfo();
        ResultSet resultSet = Dao.findForResultSet("SELECT * FROM Room_livein WHERE " + where);
        try {
            if (resultSet.next()) {
                roomInfo.setRoom_number(resultSet.getInt("room_number"));
                roomInfo.setRoom_p_ID(resultSet.getInt("r_p_ID"));
                roomInfo.setRoom_is_assigned(resultSet.getBoolean("r_is_assigned"));
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
        return roomInfo;
    }

    // get multiple doctors data
    public static List getRoomInfos() {
        List list = Dao.findForList("SELECT room_number FROM Room_livein");
        return list;
    }
}
