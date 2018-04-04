package DataAccessObject;

import models.Client;
import java.util.List;

public interface ClientDao {
    void save(Client client);
    void delete(Client client);
    void update(Client client);
    Client findById(int id);
    Client findByName(String name);
    List<Client> findAll();
}
