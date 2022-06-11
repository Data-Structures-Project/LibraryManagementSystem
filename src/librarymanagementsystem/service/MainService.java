package librarymanagementsystem.service;

import librarymanagementsystem.dao.AccountRepositoryImpl;
import librarymanagementsystem.dao.AuthorRepositoryImpl;
import librarymanagementsystem.dao.LibraryRepositoryImpl;
import librarymanagementsystem.dao.MaterialRepositoryImpl;
import librarymanagementsystem.dao.PublisherRepositoryImpl;

public class MainService {
    AccountRepositoryImpl accounts = new AccountRepositoryImpl();
    AuthorRepositoryImpl authors = new AuthorRepositoryImpl();
    LibraryRepositoryImpl libraries = new LibraryRepositoryImpl();
    MaterialRepositoryImpl materials = new MaterialRepositoryImpl();
    PublisherRepositoryImpl publishers = new PublisherRepositoryImpl();
}
