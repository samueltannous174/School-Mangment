package application;

import application.Null;

import javax.security.auth.DestroyFailedException;
import java.security.KeyStore.PasswordProtection;
import java.sql.*;

public class Sql {
    static Connection connection = null;

    public static boolean connect(PasswordProtection pass) {///check password and connection
        Class driver = null;
        try {
            driver = Class.forName("com.mysql.cj.jdbc.Driver");
        } catch (ClassNotFoundException e1) {
            // TODO Auto-generated catch block
            return false;
        }

        char[] p = pass.getPassword();
        String Password = String.valueOf(p);
        try {
///password=1234
            connection = DriverManager.getConnection("jdbc:mysql://localhost:3306/test?" +
                    "user=root&password=123");
            Password = null;
        } catch (SQLException e) {
            Password = null;
            return false;
        }
        try {
            pass.destroy();
        } catch (DestroyFailedException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        Password = null;
        return true;
    }

    public static boolean excute(String st) {
        try {
            Statement p = connection.createStatement();
            p.execute(st);
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Null.showSqlError(e.getMessage());
            return false;
        }
        return true;
    }

    public static void update(String table, String valueCh, String value1, String value2, String att) {
        String type = getType(table, valueCh);
        String s;
        if (type.contains("CHAR"))
            value1 = '\"' + value1 + '\"';
        s = "update " + table + " set " + valueCh + "=" + value1 + " where " + att + "=" + value2;
        excute(s);
    }

    public static void delete(String table, String att, String value) {
        String s = "delete from " + table + " where " + att + "=" + value;
        excute(s);
    }

    public static String[][] getResults(String table, String con, String value) {
        Statement p = null;
        int rows = 0;
        String query;
        String row_query;
        try {
            p = connection.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        String type = getType(table, con);
        if (type.contains("CHAR")) //char ahead
            query = "select * from " + table + " where " + con + " like " + '"' + '%' + value + '%' + '"';
        else
            query = "select * from " + table + " where " + con + "=" + value;
        if (type.contains("CHAR"))
            row_query = "select count(*) from " + table + " where " + con + " like " + '"' + '%' + value + '%' + '"';
        else
            row_query = "select count(*) from " + table + " where " + con + "=" + value;
        try {
            Statement r = connection.createStatement();
            ResultSet v = r.executeQuery(query);
            ResultSetMetaData r1 = v.getMetaData();
            int columns = r1.getColumnCount();
            Statement s = connection.createStatement();
            ResultSet i = s.executeQuery(row_query);
            while (i.next())
                rows = i.getInt(1);
            String[][] res = null;
            p = connection.createStatement();
            ResultSet n = p.executeQuery(query);
            int j = 0;
            if (rows != 0) {
                res = new String[rows][columns];
                while (n.next()) {
                    for (int w = 0; w < columns; w++)
                        res[j][w] = n.getString(w + 1);
                    j++;
                }
            }
            return res;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Null.showSqlError(e.getMessage());
        }
        return null;

    }

    public static String[][] getResultsTable(String table) {
        final String query = "Select * from " + table;
        int rows = getRowsCount(table);
        int columns = getColumnsCount(table);
        int j = 0;
        String[][] result = new String[rows][columns];

        Statement p;
        try {
            p = connection.createStatement();
            ResultSet res = p.executeQuery(query);
            while (res.next()) {
                for (int i = 0; i < columns; i++)
                    result[j][i] = res.getString(i + 1);
                j++;
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            Null.showSqlError(e.getMessage());
        }
        return result;
    }

    public static int getRowsCount(String table) {
        int rows = 0;
        String query = "Select Count(*) from " + table;
        try {
            Statement p = connection.createStatement();
            ResultSet res = p.executeQuery(query);
            while (res.next()) {
                rows = res.getInt(1);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }


        return rows;

    }

    public static int getColumnsCount(String table) {
        int columns = 0;
        String query = "select * from " + table;
        try {
            Statement p = connection.createStatement();
            ResultSet res = p.executeQuery(query);
            ResultSetMetaData rsmd = res.getMetaData(); //get columns size
            columns = rsmd.getColumnCount(); //get columns size
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return columns;
    }

    public static String[] getTableLabels(String table) {
        int columns = getColumnsCount(table);
        String[] labelName = new String[columns];
        String query = "Select * from " + table;
        Statement stmt = null;
        try {
            stmt = connection.createStatement();
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        ResultSet rs;
        try {
            rs = stmt.executeQuery(query);
            ResultSetMetaData rsmd = rs.getMetaData();
            // The column count starts from 1
            for (int i = 1; i <= columns; i++) {
                labelName[i - 1] = rsmd.getColumnName(i);
            }
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }

        return labelName;

    }

    public static int getColumnIndex(String[] labels, String att) {
        for (int i = 0; i < labels.length; i++)
            if (labels[i].matches(att))
                return i + 1;
        return -1;
    }

    public static String getType(String table, String con) { //get sql column types
        String[] labels = getTableLabels(table);
        int index = getColumnIndex(labels, con);
        try {
            Statement p = connection.createStatement();
            String query = "Select * from " + table;
            ResultSet res = p.executeQuery(query);
            ResultSetMetaData rsmd = res.getMetaData();
            String type = rsmd.getColumnTypeName(index);
            return type;
        } catch (SQLException e) {
            // TODO Auto-generated catch block
            e.printStackTrace();
        }
        return null;
    }



    public String[][] studentDistribution() {
        return null;
    }
}
