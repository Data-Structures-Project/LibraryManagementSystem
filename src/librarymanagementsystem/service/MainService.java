package librarymanagementsystem.service;

import librarymanagementsystem.dao.AccountRepositoryImpl;
import librarymanagementsystem.dao.AuthorRepositoryImpl;
import librarymanagementsystem.dao.LibraryRepositoryImpl;
import librarymanagementsystem.dao.MaterialRepositoryImpl;
import librarymanagementsystem.dao.PublisherRepositoryImpl;
import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.City;
import librarymanagementsystem.model.Library;

public class MainService {
    static AccountRepositoryImpl accounts = new AccountRepositoryImpl();
    static AuthorRepositoryImpl authors = new AuthorRepositoryImpl();
    static LibraryRepositoryImpl libraries = new LibraryRepositoryImpl();
    static MaterialRepositoryImpl materials = new MaterialRepositoryImpl();
    static PublisherRepositoryImpl publishers = new PublisherRepositoryImpl();

    public static Account login(String username, String password) {
        Account tempAccount = accounts.viewInfo(username);
        if (tempAccount.getPassword() == password)
            return tempAccount;
        return null;
    }

    // public static Account register(String username, String password) {
    //     Library tempLib = new Library((long) 10, "sefaLib", City.ANKARA);
    //     Account tempNewAccount = new Account(10, "sefa", "crazySefa", "cahyir", "1234", tempLib);
    //     Account tempAccount = accounts.create(tempNewAccount);
    //     if (tempAccount.getPassword() == password)
    //         return tempAccount;
    //     return null;
    // }
}
