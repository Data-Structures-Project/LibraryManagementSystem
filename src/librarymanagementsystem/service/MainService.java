package librarymanagementsystem.service;

import librarymanagementsystem.dao.AccountRepositoryImpl;
import librarymanagementsystem.dao.AuthorRepositoryImpl;
import librarymanagementsystem.dao.LibraryRepositoryImpl;
import librarymanagementsystem.dao.MaterialRepositoryImpl;
import librarymanagementsystem.dao.PublisherRepositoryImpl;
import librarymanagementsystem.model.Account;

public class MainService {
    AccountRepositoryImpl accounts = new AccountRepositoryImpl();
    AuthorRepositoryImpl authors = new AuthorRepositoryImpl();
    LibraryRepositoryImpl libraries = new LibraryRepositoryImpl();
    MaterialRepositoryImpl materials = new MaterialRepositoryImpl();
    PublisherRepositoryImpl publishers = new PublisherRepositoryImpl();

    public Account login(String username, String password)
    {
        Account tempAccount = accounts.viewInfo(username);
        if(tempAccount.getPassword() == password)
            return tempAccount;
        return null;
    }
}
