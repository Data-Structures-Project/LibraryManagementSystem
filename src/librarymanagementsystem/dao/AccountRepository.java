package librarymanagementsystem.dao;

import librarymanagementsystem.model.Account;

import java.util.HashMap;

// Defining the interface for the AccountRepository class.
public interface AccountRepository {
    /**
     * Find all accounts.
     *
     * @return A HashMap of all the accounts in the database.
     */
    HashMap<String, Account> findAll();
    /**
     * Creates a new account.
     *
     * @param account The account object to be created.
     */
    void create(Account account);
    /**
     * Update the target account with the new account.
     *
     * @param newAccount The new account object that will be used to update the target account.
     * @param targetAccount The account to be updated.
     * @return The updated account.
     */
    Account update(Account newAccount, Account targetAccount);
    /**
     * Remove the account from the database.
     *
     * @param account The account to remove.
     */
    void remove(Account account);
    /**
     * This function takes a string and returns an Account object.
     *
     * @param name The name of the account to view.
     * @return A single Account object.
     */
    Account viewInfo(String name);
}
