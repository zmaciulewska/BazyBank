package DataAccessObject;

import models.Account;
import models.Client;

import java.util.List;

public interface AccountDao {
    void save(Account account);
    void delete(Account account);
    void update(Account account);
    Client findById(int id);
    Client findByName(String name);
    List<Client> findAll();
}
