package com.library.utilities;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class MyDB_Util {
    private static Connection connection;
    private static Statement statement;
    private static ResultSet resultSet;
    private static ResultSetMetaData resultSetMetaData;

    /**
     * Create Connection by jdbc url and username, password provided
     *
     * @param url      jdbc used for any DB
     * @param username username for DB
     * @param password password for DB
     */

    public static void connectionDB(String url, String username, String password) {
        try {
            connection = DriverManager.getConnection(url, username, password);
            System.out.println("CONNECTION SUCCESSFUL");
        } catch (Exception e) {
            System.out.println("CONNECTION FAILED " + e.getMessage());
        }
    }

    /**
     * Run the SQL query provided and return object
     *
     * @param sqlQuery the query run
     * @return ResultSet object that contains data
     */

    public static ResultSet runQuery(String sqlQuery) {
        try {
            statement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
            resultSet = statement.executeQuery(sqlQuery); //setting the value of ResultSet object
            resultSetMetaData = resultSet.getMetaData(); // setting the value of ResultSetMetaData for use
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE RUNNING QUERY " + e);
        }

        return resultSet;
    }

    /**
     * find out the column count
     *
     * @return colun of this ResultSet
     */

    public static int getColumnCount() {
        int columnCount = 0;

        try {
            columnCount = resultSetMetaData.getColumnCount();

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE GETTING COLUMN COUNT " + e.getMessage());
        }

        return columnCount;
    }


    /**
     * Get all the column names into a list object
     *
     * @return List<String>
     */

    public static List<String> getAllColumnNamesAsList() {
        List<String> columnNameList = new ArrayList<>();

        try {
            for (int colIndex = 1; colIndex <= getColumnCount(); colIndex++) {
                String columnName = resultSetMetaData.getColumnName(colIndex);
                columnNameList.add(columnName);
            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getAllColumnNamesAsList " + e.getMessage());
        }
        return columnNameList;
    }


    /**
     * getting entire column data as list according to column number
     *
     * @param columnNum column number to get all data
     * @return List object that contains all rows of that column
     */

    public static List<String> getColumnDataAsList(int columnNum) {
        List<String> columnDataList = new ArrayList<>();

        try {
            resultSet.beforeFirst(); // make sure the cursor is at before first location
            while (resultSet.next()) {

                String cellValue = resultSet.getString(columnNum);
                columnDataList.add(cellValue);

            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataList " + e.getMessage());
        } finally {
            resetCursor();
        }
        return columnDataList;
    }


    /**
     * getting entire column data as list according to column name
     *
     * @param columnName column number to get all data
     * @return List object that contains all rows of that column
     */

    public static List<String> getColumnDataAsList(String columnName) {
        List<String> columnDataList = new ArrayList<>();

        try {
            resultSet.beforeFirst(); // make sure the cursor is at before first location
            while (resultSet.next()) {

                String cellValue = resultSet.getString(columnName);
                columnDataList.add(cellValue);

            }
        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getColumnDataList " + e.getMessage());
        } finally {
            resetCursor();
        }
        return columnDataList;
    }

    private static void resetCursor() {

        try {
            resultSet.beforeFirst();
        } catch (Exception e) {
            e.printStackTrace();
        }

    }

    /**
     * Get first cell value at first row firs column
     */

    public static String getFirstRowFirstColumn(){
        return getCellValue(1,1);
    }

    /**
     * getting the cell value according to row num and column name
     * @param rowNum  row number to get the data from
     * @param columnName column Name to get the data from
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum ,String columnName){

        String cellValue = "" ;

        try {
            resultSet.absolute(rowNum) ;
            cellValue = resultSet.getString( columnName ) ;

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }


    /**
     * getting the cell value according to row num and column index
     * @param rowNum  row number to get the data from
     * @param columnIndex column number to get the data from
     * @return the value in String at that location
     */
    public static String getCellValue(int rowNum , int columnIndex) {

        String cellValue = "" ;

        try {
            resultSet.absolute(rowNum) ;
            cellValue = resultSet.getString(columnIndex ) ;

        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getCellValue " + e.getMessage() );
        }finally {
            resetCursor();
        }
        return cellValue ;

    }

    /**
     * Getting entire row of data in a List of String
     * @param rowNum row number to get as a list
     * @return row data as List of String
     */
    public static List<String> getRowDataAsList( int rowNum ){

        List<String> rowDataAsLst = new ArrayList<>();
        int colCount =  getColumnCount() ;

        try {
            resultSet.absolute( rowNum );

            for (int colIndex = 1; colIndex <= colCount ; colIndex++) {

                String cellValue =  resultSet.getString( colIndex ) ;
                rowDataAsLst.add(   cellValue  ) ;

            }


        } catch (Exception e) {
            System.out.println("ERROR OCCURRED WHILE getRowDataAsList " + e.getMessage() );
        }finally {
            resetCursor();
        }


        return rowDataAsLst ;
    }

}
