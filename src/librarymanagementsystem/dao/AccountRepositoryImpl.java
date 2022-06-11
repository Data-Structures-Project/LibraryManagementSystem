package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;

import java.util.HashMap;

public class AccountRepositoryImpl implements AccountRepository{
    private HashMap<String, Account> accounts;

    public AccountRepositoryImpl(){
        accounts = new HashMap<>();
    }

    @Override
    public HashMap<String,Account> findAll() {
        return accounts;
    }

    @Override
    public void create(Account account) {
        accounts.put(account.getUsername(), account);
    }

    @Override
    public Account update(Account TargetAccount, Account newAccount) {
        accounts.remove(TargetAccount.getUsername());
        accounts.put(newAccount.getUsername(), newAccount);
        return newAccount;
    }

    @Override
    public void remove(Account account) {
        accounts.remove(account);
    }

    @Override
    public Account viewInfo(String username) {
        Account targetAccount = accounts.remove(username);
        accounts.put(username, targetAccount);
        return targetAccount;
    }
}
