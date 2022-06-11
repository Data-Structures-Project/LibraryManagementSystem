package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;

import java.util.HashMap;

public interface AccountRepository {
    HashMap<String, Account> findAll();
    void create(Account account);
    Account update(Account newAccount, Account targetAccount);
    void remove(Account account);
    Account viewInfo(String name);
}
