package DataAccessObject;

import com.pabwoopj.DatabaseConnector;
import models.Account;


import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class jdbcAccountDao implements AccountDao {

    public void save(Account account) throws RuntimeException {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "INSERT INTO account VALUES (DEFAULT, ?, ?, ?)";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);


                statement.setString(1, account.getNotes());
                statement.setFloat(2, account.getBalance());
                statement.setInt(3, account.getIdClient());

                statement.executeUpdate();
                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void delete(Account account) {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "DELETE FROM account WHERE id=?";
            try {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setInt(1, account.getId());
                statement.executeUpdate();

                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
    }

    public void update(Account account) {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "UPDATE ACCOUNT SET NOTES=?, BALANCE=?, ID_CLIENT=? WHERE ID=?";
            try {
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setInt(4, account.getId());
                statement.setString(1, account.getNotes());
                statement.setFloat(2, account.getBalance());
                statement.setInt(3, account.getIdClient());
                statement.executeUpdate();

                statement.close();
                connection.close();
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }

    }

    public Account findById(int id) {
        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){

            final String sql = "SELECT * FROM ACCOUNT WHERE id=?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                Account acc = new Account();
                while(rs.next()) {

                    acc.setId(rs.getInt("id"));
                    acc.setNotes(rs.getString("notes"));
                    acc.setBalance(rs.getFloat("balance"));
                    acc.setIdClient(rs.getInt("id_client"));

                }

                statement.close();
                connection.close();
                if(acc.getId() !=null) return acc;
                else return null;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }



    public List<Account> findAll() {
        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            List<Account> list = new ArrayList<Account>();
            final String sql = "SELECT * FROM ACCOUNT";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();
                while(rs.next()) {
                    Account acc = new Account();
                    acc.setId(rs.getInt("id"));
                    acc.setNotes(rs.getString("notes"));
                    acc.setBalance(rs.getFloat("balance"));
                    acc.setIdClient(rs.getInt("id_client"));
                    list.add(acc);
                }

                statement.close();
                connection.close();
                return list;
            } catch (SQLException e) {
                e.printStackTrace();
            }
        }
        return null;
    }
}
