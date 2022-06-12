package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.Librarian;
import librarymanagementsystem.model.LibraryManager;
import librarymanagementsystem.model.User;

import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class is used to store and manage the accounts of the library
 */
public class AccountRepositoryImpl implements AccountRepository{
    // A HashMap that stores the accounts of the library.
    private HashMap<String, Account> accounts;

    public AccountRepositoryImpl(){
        accounts = new HashMap<>();
    }

    @Override
    // Returning the HashMap of accounts.
    public HashMap<String,Account> findAll() {
        return accounts;
    }

    /**
     * This function returns an ArrayList of all the users in the system
     *
     * @return An ArrayList of User objects.
     */
    public ArrayList<User> getReaderList()
    {
        ArrayList<User> accountList = new ArrayList<>();

        // using for-each loop for iteration over Map.entrySet()
        for (HashMap.Entry<String,Account> entry : accounts.entrySet())
           if(entry.getValue()!=null && entry.getValue().getClass().getName().compareTo("librarymanagementsystem.model.User")==0) accountList.add((User) entry.getValue());

        return accountList;
    }

    /**
     * This function returns an ArrayList of Librarian objects from the accounts HashMap
     *
     * @return An ArrayList of Librarian objects.
     */
    public ArrayList<Librarian> getLibrarianList()
    {
        ArrayList<Librarian> accountList = new ArrayList<>();

        // using for-each loop for iteration over Map.entrySet()
        for (HashMap.Entry<String,Account> entry : accounts.entrySet())
            if(entry.getValue()!=null && entry.getValue().getClass().getName().compareTo("librarymanagementsystem.model.Librarian")==0) accountList.add((Librarian) entry.getValue());

        return accountList;
    }

    /**
     * This function returns an ArrayList of LibraryManager objects
     *
     * @return An ArrayList of LibraryManager objects.
     */
    public ArrayList<LibraryManager> getLibraryManagerList()
    {
        ArrayList<LibraryManager> accountList = new ArrayList<>();

        // using for-each loop for iteration over Map.entrySet()
        for (HashMap.Entry<String,Account> entry : accounts.entrySet())
            if(entry.getValue()!=null && entry.getValue().getClass().getName().compareTo("librarymanagementsystem.model.LibraryManager")==0) accountList.add((LibraryManager) entry.getValue());

        return accountList;
    }

    @Override
    // Adding a new account to the HashMap.
    public void create(Account account) {
        accounts.put(account.getUsername(), account);
    }

    @Override
    // Updating the account information.
    public Account update(Account TargetAccount, Account newAccount) {
        accounts.remove(TargetAccount.getUsername());
        accounts.put(newAccount.getUsername(), newAccount);
        return newAccount;
    }

    @Override
    public void remove(Account account) {
        // Removing the account from the HashMap.
        accounts.remove(account.getUsername());
    }


    @Override
    // A method to view the information of an account.
    public Account viewInfo(String username) {
        Account targetAccount = accounts.remove(username);
        accounts.put(username, targetAccount);
        return targetAccount;
    }
}
