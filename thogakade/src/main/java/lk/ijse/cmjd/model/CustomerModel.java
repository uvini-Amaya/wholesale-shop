package lk.ijse.cmjd.model;

/*
    @author DanujaV
    @created 5/21/23 - 3:37 PM   
*/

import lk.ijse.cmjd.db.DbConnection;
import lk.ijse.cmjd.dto.CustomerDTO;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;
import java.util.Properties;

public class CustomerModel {
    private static final String URL = "jdbc:mysql://localhost:3306/ThogaKade";
    private static final Properties props = new Properties();

    static {
        props.setProperty("user", "root");
        props.setProperty("password", "Danu25412541@");
    }

    public static boolean save(CustomerDTO dto) throws SQLException {
//        Connection connection = DriverManager.getConnection(URL, props);

        Connection con = DbConnection.getInstance().getConnection();

        String sql = "INSERT INTO Customer VALUES(?, ?, ?, ?)";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, dto.getId());
        pstm.setString(2, dto.getName());
        pstm.setString(3, dto.getAddress());
        pstm.setDouble(4, dto.getSalary());

        boolean isAdded = pstm.executeUpdate() > 0;
        return isAdded;

    }

    public static CustomerDTO search(String id) throws SQLException {
//        Connection con = DriverManager.getConnection(URL, props);


        Connection con = DbConnection.getInstance().getConnection();

        String sql = "SELECT * FROM Customer WHERE id = ?";
        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        CustomerDTO dto = null;

        if(resultSet.next()) {
            String colId = resultSet.getString(1);
            String colName = resultSet.getString(2);
            String colAddress = resultSet.getString(3);
            double colSalary = resultSet.getDouble(4);

             dto = new CustomerDTO(colId, colName, colAddress, colSalary);
        }
        return dto;
    }

    public static List<CustomerDTO> getAll() throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer";

        Statement stm = con.createStatement();
        ResultSet resultSet = stm.executeQuery(sql);

        List<CustomerDTO> dtoList = new ArrayList<>();

        while (resultSet.next()) {
            CustomerDTO dto = new CustomerDTO(
                    resultSet.getString(1),
                    resultSet.getString(2),
                    resultSet.getString(3),
                    resultSet.getDouble(4)
            );
            dtoList.add(dto);
        }
        return dtoList;
    }

    public static List<String> getIds() throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();

        String sql = "SELECT id FROM Customer";
        Statement stm = con.createStatement();
        ResultSet resultSet = stm.executeQuery(sql);

        List<String> idList = new ArrayList<>();

        while(resultSet.next()) {
            String id = resultSet.getString(1);
            idList.add(id);
        }
        return idList;
    }

    public static CustomerDTO searchById(String id) throws SQLException {
        Connection con = DbConnection.getInstance().getConnection();
        String sql = "SELECT * FROM Customer WHERE id = ?";

        PreparedStatement pstm = con.prepareStatement(sql);
        pstm.setString(1, id);

        ResultSet resultSet = pstm.executeQuery();

        if(resultSet.next()) {
            String colId = resultSet.getString(1);
            String colName = resultSet.getString(2);
            String colAddress = resultSet.getString(3);
            double colSalary = resultSet.getDouble(4);

            return new CustomerDTO(colId, colName, colAddress, colSalary);
        }
        return null;
    }
}
