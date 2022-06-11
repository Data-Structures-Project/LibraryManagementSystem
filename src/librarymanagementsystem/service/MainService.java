package librarymanagementsystem.service;

import java.util.ArrayList;
import java.util.Date;

import librarymanagementsystem.dao.AccountRepositoryImpl;
import librarymanagementsystem.dao.AuthorRepositoryImpl;
import librarymanagementsystem.dao.LibraryRepositoryImpl;
import librarymanagementsystem.dao.MaterialRepositoryImpl;
import librarymanagementsystem.dao.PublisherRepositoryImpl;
import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.Administrator;
import librarymanagementsystem.model.Author;
import librarymanagementsystem.model.City;
import librarymanagementsystem.model.Library;
import librarymanagementsystem.model.Material;
import librarymanagementsystem.model.MaterialType;
import librarymanagementsystem.model.Personnel;
import librarymanagementsystem.model.Publisher;
import librarymanagementsystem.model.Situation;
import librarymanagementsystem.model.User;
import librarymanagementsystem.model.Category;
import librarymanagementsystem.model.Location;

public class MainService {
    static AccountRepositoryImpl accounts = new AccountRepositoryImpl();
    static AuthorRepositoryImpl authors = new AuthorRepositoryImpl();
    static LibraryRepositoryImpl libraries = new LibraryRepositoryImpl();
    static MaterialRepositoryImpl materials = new MaterialRepositoryImpl();
    static PublisherRepositoryImpl publishers = new PublisherRepositoryImpl();

    public static void mockData() {
        Library library1 = new Library("Library1", City.ANKARA);
        Library library2 = new Library("Library2", City.ISTANBUL);
        Library library3 = new Library("Library3", City.IZMIR);
        libraries.create(library1);
        libraries.create(library2);
        libraries.create(library3);

        User user1 = new User("Sefa", "Cahyir", "scahyir", "1234", library1);
        User user2 = new User("Mustafa", "Mert", "Mustafa52", "1234", library2);
        User user3 = new User("Emre", "Yılmaz", "Emre9180", "1234", library3);

        Personnel personal1 = new Personnel("SefaPersonal", "Cahyir", "scahyirPersonal", "1234", library1);
        Personnel personal2 = new Personnel("MustafaPersonal", "Mert", "Mustafa52Personal", "1234", library2);
        Personnel personal3 = new Personnel("EmrePersonal", "Yılmaz", "Emre9180Personal", "1234", library3);

        Administrator admin1 = new Administrator("SefaAdmin", "Cahyir", "scahyirAdmin", "1234", library1);
        Administrator admin2 = new Administrator("MustafaAdmin", "Mert", "Mustafa52Admin", "1234", library2);
        Administrator admin3 = new Administrator("EmreAdmin", "Yılmaz", "Emre9180Admin", "1234", library3);

        accounts.create(user1);
        accounts.create(user2);
        accounts.create(user3);
        accounts.create(personal1);
        accounts.create(personal2);
        accounts.create(personal3);
        accounts.create(admin1);
        accounts.create(admin2);
        accounts.create(admin3);

        Publisher publisher1 = new Publisher("Is Bankasi", "Is Bankasi, klasik yayinlar");
        Publisher publisher2 = new Publisher("Yapikredi", "Yapikredi, klasik yayinlar");
        Publisher publisher3 = new Publisher("Can Yayinlari", "Can yayinlari, klasik yayinlar");

        publishers.create(publisher1);
        publishers.create(publisher2);
        publishers.create(publisher3);

        Author author1 = new Author("JRR Tolkien", "Tolkien", "Born in Turkey");
        Author author2 = new Author("Lev Nicolovig", "Tolstoy", "Born in Russia");
        Author author3 = new Author("Stefan", "Zweig", "Born in Germany");

        authors.create(author1);
        authors.create(author2);
        authors.create(author3);

        Material material1 = new Material(MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513),
                author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material2 = new Material(MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513),
                author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material3 = new Material(MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513),
                author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material4 = new Material(MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513),
                author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material5 = new Material(MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513),
                author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");

            
        materials.create(material1);
        materials.create(material2);
        materials.create(material3);
        materials.create(material4);
        materials.create(material5);

    }

    public static void createLibrary(String name, City city) {
        libraries.create(new Library(name, city));
    }

    public static Account login(String username, String password) {
        Account tempAccount = accounts.viewInfo(username);
        if(accounts.viewInfo(username) != null)
        {
            if (tempAccount.getPassword().equals(password))
            return tempAccount;
        }
        return null;
    }

    // TODO intları düzenleme
    // TODO Id otomatik sectirme

    public static boolean register(String name, String surname, String username, String password, int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new User(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }

    public static boolean addLibraryManager(String name, String surname, String username, String password, int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new Personnel(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }

    public static boolean addLibrarian(String name, String surname, String username, String password, int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new Personnel(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }

    public static boolean isUniqueUserName(String username) {
        if (accounts.viewInfo(username) == null)
            return true;
        return false;
    }

    public static ArrayList<Library> listLibraries() {
        ArrayList<Library> libList = libraries.findAll().getVertexList();
        return libList;
    }
}
