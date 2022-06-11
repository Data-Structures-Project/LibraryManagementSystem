package librarymanagementsystem.service;

import librarymanagementsystem.dao.AccountRepositoryImpl;
import librarymanagementsystem.dao.AuthorRepositoryImpl;
import librarymanagementsystem.dao.LibraryRepositoryImpl;
import librarymanagementsystem.dao.MaterialRepositoryImpl;
import librarymanagementsystem.dao.PublisherRepositoryImpl;
import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.City;
import librarymanagementsystem.model.Library;
import librarymanagementsystem.model.User;

public class MainService {
    static AccountRepositoryImpl accounts = new AccountRepositoryImpl();
    static AuthorRepositoryImpl authors = new AuthorRepositoryImpl();
    static LibraryRepositoryImpl libraries = new LibraryRepositoryImpl();
    static MaterialRepositoryImpl materials = new MaterialRepositoryImpl();
    static PublisherRepositoryImpl publishers = new PublisherRepositoryImpl();

    public static Account login(String username, String password) {
        Account tempAccount = accounts.viewInfo(username);
        if (tempAccount.getPassword().equals(password))
            return tempAccount;
        return null;
    }

    public static void register(String username, String password) {
        Library tempLib = new Library((long) 10, "sefaLib", City.ANKARA);
        User tempNewAccount = new User(10, "sefa", "cahyir", username, password, tempLib);
        accounts.create(tempNewAccount);
    }
}
