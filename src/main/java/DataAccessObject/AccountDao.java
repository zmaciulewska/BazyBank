package DataAccessObject;

import models.Account;
import models.Client;

import java.util.List;

public interface AccountDao {
    void save(Account account) throws RuntimeException;
    void delete(Account account);
    void update(Account account);
    Account findById(int id);
    List<Account> findAll();
}
