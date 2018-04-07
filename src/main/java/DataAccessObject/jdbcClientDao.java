package DataAccessObject;

import com.pabwoopj.DatabaseConnector;

import models.Client;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

public class jdbcClientDao implements ClientDao {

    public void save(Client client) throws SQLException {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "INSERT INTO CLIENT VALUES (DEFAULT, ?, ?, ?, ?)";

            PreparedStatement statement = connection.prepareStatement(sql);
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getPesel());
            statement.setString(4, client.getEmail());

            statement.executeUpdate();
            statement.close();
            connection.close();
        }
    }

    public void delete(Client client) throws SQLException {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "DELETE FROM CLIENT WHERE id=?";
                PreparedStatement statement=connection.prepareStatement(sql);
                statement.setInt(1, client.getId());
                statement.executeUpdate();

                statement.close();
                connection.close();
        }

    }

    public void update(Client client) throws SQLException {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "UPDATE CLIENT SET FIRSTNAME=?, LASTNAME=?, PESEL=?, EMAIL=? WHERE ID=?";

            PreparedStatement statement=connection.prepareStatement(sql);
            statement.setInt(5, client.getId());
            statement.setString(1, client.getFirstName());
            statement.setString(2, client.getLastName());
            statement.setString(3, client.getPesel());
            statement.setString(4, client.getEmail());
            statement.executeUpdate();

            statement.close();
            connection.close();
        }
    }

    public Client findById(int id)  {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "SELECT * FROM CLIENT WHERE id=?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setInt(1, id);
                ResultSet rs = statement.executeQuery();
                Client client = new Client();
                while (rs.next()) {
                    client.setId(rs.getInt("id"));
                    client.setFirstName(rs.getString("firstname"));
                    client.setLastName(rs.getString("lastname"));
                    client.setPesel(rs.getString("pesel"));
                    client.setEmail(rs.getString("email"));
                }

                statement.close();
                connection.close();
                if (client.getId() != null) return client;
                else return null;
            } catch (SQLException e) {
                e.printStackTrace();
            }

        }
        return null;
    }


    public List<Client> findByName(String name) {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            final String sql = "SELECT * FROM CLIENT WHERE LASTNAME=?";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                statement.setString(1, name);
                ResultSet rs = statement.executeQuery();
                List<Client> list = new ArrayList<Client>();
                while(rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    if( client.getId() == null) return null;
                    client.setFirstName(rs.getString("firstname"));
                    client.setLastName(rs.getString("lastname"));
                    client.setPesel(rs.getString("pesel"));
                    client.setEmail(rs.getString("email"));
                    list.add(client);
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

    public List<Client> findAll() {

        Connection connection = DatabaseConnector.getConnection();
        if(connection != null){
            List<Client> list = new ArrayList<Client>();
            final String sql = "SELECT * FROM CLIENT";
            try {
                PreparedStatement statement = connection.prepareStatement(sql);
                ResultSet rs = statement.executeQuery();

                while(rs.next()) {
                    Client client = new Client();
                    client.setId(rs.getInt("id"));
                    if( client.getId() == null) return null;
                    client.setFirstName(rs.getString("firstname"));
                    client.setLastName(rs.getString("lastname"));
                    client.setPesel(rs.getString("pesel"));
                    client.setEmail(rs.getString("email"));
                    list.add(client);
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
