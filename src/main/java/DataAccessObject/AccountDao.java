package DataAccessObject;

import models.Account;
import models.Client;

import java.sql.SQLException;
import java.util.List;

public interface AccountDao {
    void save(Account account) throws SQLException;
    void delete(Account account);
    void update(Account account) throws SQLException;
    Account findById(int id);
    List<Account> findAll();
}
