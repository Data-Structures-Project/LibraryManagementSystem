package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;

import java.util.List;

public interface AccountRepository {
    List<Account> findAll();
    Account findById(Long id);
    List<Account> findBySurName(String surname);
    void create(Account account);
    Account update(Account account);
    void remove(Account account);
    String viewInfo(Long id);
}
