package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;

import java.util.HashMap;
import java.util.HashSet;
import java.util.List;

public interface AccountRepository {
    HashMap<String, Account> findAll();
    void create(Account account);
    Account update(Account newAccount, Account targetAccount);
    void remove(Account account);
    String viewInfo(String name);
}
