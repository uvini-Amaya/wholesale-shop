package lk.ijse.cmjd.model;

/*
    @author DanujaV
    @created 5/28/23 - 10:57 AM   
*/

import lk.ijse.cmjd.db.DbConnection;
import lk.ijse.cmjd.dto.ItemDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class ItemModel {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }
    public static boolean save(ItemDTO dto) throws SQLException {
//        Connection con = DriverManager.getConnection(URL, props);

        Connection con = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Item VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, dto.getCode());
        pstm.setString(2, dto.getDescription());
        pstm.setInt(3, dto.getQtyOnHand());
        pstm.setDouble(4, dto.getUnitPrice());

        return pstm.executeUpdate() > 0;
    }

    public static List<String> loadCodes() throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();

        String sql = "SELECT code FROM Item";
        Statement stm = con.createStatement();

        ResultSet resultSet = stm.executeQuery(sql);

        List<String> codeList = new ArrayList<>();
        while (resultSet.next()) {
            String code = resultSet.getString(1);
            codeList.add(code);
        }
        return codeList;
    }

    public static ItemDTO searchById(String code) throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Item WHERE code = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, code);

        ResultSet resultSet = pstm.executeQuery();
        if(resultSet.next()) {
            String colCode = resultSet.getString(1);
            String colDescription = resultSet.getString(2);
            int colQtyOnHand = resultSet.getInt(3);
            double colUnitPrice = resultSet.getDouble(4);

            return new ItemDTO(colCode, colDescription, colQtyOnHand, colUnitPrice);
        }
        return null;
    }
}
