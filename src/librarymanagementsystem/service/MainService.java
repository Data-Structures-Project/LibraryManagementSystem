package librarymanagementsystem.service;

import java.util.Date;
import java.util.List;

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

    private void mockData() {
        Library lib1 = new Library((long) 1, "Library1", City.ANKARA);
        Library lib2 = new Library((long) 2, "Library2", City.ISTANBUL);
        Library lib3 = new Library((long) 3, "Library3", City.IZMIR);

        User user1 = new User(1, "Sefa", "Cahyir", "scahyir", "1234", lib1);
        User user2 = new User(2, "Mustafa", "Mert", "Mustafa52", "1234", lib1);
        User user3 = new User(3, "Emre", "Yılmaz", "Emre9180", "1234", lib3);

        Personnel personal1 = new Personnel(1, "SefaPersonal", "Cahyir", "scahyirPersonal", "1234", lib1);
        Personnel personal2 = new Personnel(2, "MustafaPersonal", "Mert", "Mustafa52Personal", "1234", lib1);
        Personnel personal3 = new Personnel(3, "EmrePersonal", "Yılmaz", "Emre9180Personal", "1234", lib3);

        Administrator admin1 = new Administrator(1, "SefaAdmin", "Cahyir", "scahyirAdmin", "1234", lib1);
        Administrator admin2 = new Administrator(2, "MustafaAdmin", "Mert", "Mustafa52Admin", "1234", lib1);
        Administrator admin3 = new Administrator(3, "EmreAdmin", "Yılmaz", "Emre9180Admin", "1234", lib3);

        Publisher publisher1 = new Publisher((long)1, "Is Bankasi", "Is Bankasi, klasik yayinlar");
        Publisher publisher2 = new Publisher((long)2, "Yapikredi", "Yapikredi, klasik yayinlar");
        Publisher publisher3 = new Publisher((long)3, "Can Yayinlari", "Can yayinlari, klasik yayinlar");

        Author author1 = new Author((long)1, "JRR Tolkien", "Tolkien", "Born in Turkey");
        Author author2 = new Author((long)1, "Lev Nicolovig", "Tolstoy", "Born in Russia");
        Author author3 = new Author((long)1, "Stefan", "Zweig", "Born in Germany");

        Material material1 = new Material((long)1, MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513), author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material2 = new Material((long)2, MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513), author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material3 = new Material((long)3, MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513), author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material4 = new Material((long)4, MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513), author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");
        Material material5 = new Material((long)5, MaterialType.BOOK, "Beyaz Diş", Category.MYSTERY, new Date(1997513), author1, publisher1, 10, Situation.LIBRARY, Location.A1, "New Info");

    }

    public static void createLibrary(String name, City city) {
        Library tempLib = new Library((long) 10, name, city);
    }

    public static Account login(String username, String password) {
        Account tempAccount = accounts.viewInfo(username);
        if (tempAccount.getPassword().equals(password))
            return tempAccount;
        return null;
    }

    //TODO Register olurken kütüphane seçtirme
    //TODO Longları düzenleme 
    //TODO Id otomatik sectirme  
    public static boolean register(String username, String password) {
        if (accounts.viewInfo(username) == null) {
            Library tempLib = new Library((long) 10, "sefaLib", City.ANKARA);
            User tempNewAccount = new User(10, "sefa", "cahyir", username, password, tempLib);
            accounts.create(tempNewAccount);
            return true;
        } else
            return false;
    }

    public static boolean isUniqueUserName(String username) {
        if (accounts.viewInfo(username) == null)
            return true;
        return false; 
    }

    public static List<Library> listLibraries() {
        return libraries.findAll();
    }
}
