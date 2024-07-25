package Utils;

import java.sql.*;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;

public class DBUtils {

    private static Connection conn;
    private static Statement st;
    private static ResultSet rs;

    // connect to DB
    public static void getConnection(){

        ConfigsReader.readProperties(Constants.CONFIGURATION_FILEPATH);

        try {
            conn = DriverManager.getConnection(
                    ConfigsReader.getProperty("dbUrl"),
                    ConfigsReader.getProperty("dbUserName"),
                    ConfigsReader.getProperty("dbPassword"));
        } catch (SQLException e) {
            System.out.println("Could NOT connect to DB!!!");
            e.printStackTrace();
        }
    }

    //get data
    public static List<Map<String, String>> storeDataFormDB(String sqlQuery){

        List<Map<String, String>> listOfData = new ArrayList<>();

        try{
            st = conn.createStatement();
            rs = st.executeQuery(sqlQuery);
            ResultSetMetaData rsMD = rs.getMetaData();
            Map<String, String> map;

            while(rs.next()) {

                map = new LinkedHashMap<>();
                for(int i = 1; i<= rsMD.getColumnCount(); i++){

                    map.put(rsMD.getColumnName(i), rs.getString(i));
                    // map.put(rsMD.getColumnName(i), rs.getObject(i).toString());
                }
                listOfData.add(map);
            }
        } catch (SQLException e) {
            e.printStackTrace();
        }
    return listOfData;
    }

    // close the connection
    public static void closeConnection(){
        try {
            if (rs != null) {
                rs.close();
            }
            if (st != null) {
                st.close();
            }
            if (conn != null) {
                conn.close();
            }
        } catch (SQLException e ){
            e.printStackTrace();
        }
    }









}
