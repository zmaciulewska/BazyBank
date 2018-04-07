package DataAccessObject;

import models.Client;

import java.sql.SQLException;
import java.util.List;

public interface ClientDao {
    void save(Client client) throws SQLException;
    void delete(Client client) throws SQLException;
    void update(Client client) throws SQLException;
    Client findById(int id);
    List<Client> findByName(String name);
    List<Client> findAll();

    //class NotUnique
}
