package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.Librarian;
import librarymanagementsystem.model.LibraryManager;
import librarymanagementsystem.model.User;

import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
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

    public ArrayList<User> getReaderList()
    {
        ArrayList<User> accountList = new ArrayList<>();

        // using for-each loop for iteration over Map.entrySet()
        for (HashMap.Entry<String,Account> entry : accounts.entrySet())
           if(entry.getValue().getClass().getName().compareTo("User")==0) accountList.add((User) entry.getValue());

        return accountList;
    }

    public ArrayList<Librarian> getLibrarianList()
    {
        ArrayList<Librarian> accountList = new ArrayList<>();

        // using for-each loop for iteration over Map.entrySet()
        for (HashMap.Entry<String,Account> entry : accounts.entrySet())
            if(entry.getValue().getClass().getName().compareTo("Librarian")==0) accountList.add((Librarian) entry.getValue());

        return accountList;
    }

    public ArrayList<LibraryManager> getLibraryManagerList()
    {
        ArrayList<LibraryManager> accountList = new ArrayList<>();

        // using for-each loop for iteration over Map.entrySet()
        for (HashMap.Entry<String,Account> entry : accounts.entrySet())
            if(entry.getValue().getClass().getName().compareTo("LibraryManager")==0) accountList.add((LibraryManager) entry.getValue());

        return accountList;
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
