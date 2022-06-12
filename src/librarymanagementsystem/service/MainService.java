package librarymanagementsystem.service;

import java.io.Reader;
import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import librarymanagementsystem.dao.AccountRepositoryImpl;
import librarymanagementsystem.dao.AuthorRepositoryImpl;
import librarymanagementsystem.dao.LibraryRepositoryImpl;
import librarymanagementsystem.dao.MaterialRepositoryImpl;
import librarymanagementsystem.dao.PublisherRepositoryImpl;
import librarymanagementsystem.model.*;

import static librarymanagementsystem.Main.ANSI_BLUE;
import static librarymanagementsystem.Main.ANSI_CYAN;

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
        User user2 = new User("Mustafa", "Mert", "q", "1", library2);
        User user3 = new User("Emre", "Yılmaz", "Emre9180", "1234", library3);

        LibraryManager libManager1 = new LibraryManager("SefaManager", "Cahyir", "scahyirPersonal", "1234", library1);
        LibraryManager libManager2 = new LibraryManager("MustafaManager", "Mert", "Mustafa52Personal", "1234",
                library2);
        LibraryManager libManager3 = new LibraryManager("EmreManager", "Yılmaz", "Emre9180Personal", "1234", library3);

        Administrator admin1 = new Administrator("SefaAdmin", "Cahyir", "scahyirAdmin", "1234", library1);
        Administrator admin2 = new Administrator("MustafaAdmin", "Mert", "Mustafa52Admin", "1234", library2);
        Administrator admin3 = new Administrator("EmreAdmin", "Yılmaz", "Emre9180Admin", "1234", library3);

        accounts.create(user1);
        accounts.create(user2);
        accounts.create(user3);
        accounts.create(libManager1);
        accounts.create(libManager2);
        accounts.create(libManager3);
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

        Material material1 = new Material(MaterialType.BOOK, "Beyaz", Category.BIOGRAPHIES, new Date(1997513),
                author1, publisher1, 1024, Location.A1, "Beyaz Dis Info", library1);
        Material material2 = new Material(MaterialType.MAGAZINE, "Sefaya Selam olsun", Category.MYSTERY,
                new Date(1997513),
                author2, publisher2, 1130, Location.A1, "Sefaya Selam olsun Info", library2);
        Material material3 = new Material(MaterialType.MAGAZINE, "Iradenin gucu", Category.LITERARY_FICTION,
                new Date(1997513),
                author2, publisher3, 1120, Location.C3, "Iradenin gucu Info", library3);
        Material material4 = new Material(MaterialType.MAGAZINE, "Olmaz boyle math", Category.THRILLER,
                new Date(1997513),
                author3, publisher2, 2410, Location.B1, "Olmaz boyle math Info", library2);
        Material material5 = new Material(MaterialType.BOOK, "Yalan dünya", Category.AUTOBIOGRAPHIES, new Date(1997513),
                author3, publisher1, 510, Location.A3, "Yalan dünya Info", library1);

        materials.create(material1);
        materials.create(material2);
        materials.create(material3);
        materials.create(material4);
        materials.create(material5);

        // author1.addBook(material1);
        // author2.addBook(material2);
        // author3.addBook(material4);
    }

    public static void createLibrary(String name, City city) {
        libraries.create(new Library(name, city));
    }

    public static Account login(String username, String password) {
        Account tempAccount = accounts.viewInfo(username);
        if (accounts.viewInfo(username) != null) {
            if (tempAccount.getPassword().equals(password))
                return tempAccount;
        }
        return null;
    }

    public static boolean register(String name, String surname, String username, String password, int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new User(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }

    public static boolean addLibraryManager(String name, String surname, String username, String password,
            int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new LibraryManager(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }

    public static boolean addLibrarian(String name, String surname, String username, String password, int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new Librarian(name, surname, username, password, libraries.findById(libraryId)));
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
        ArrayList<Library> libList = libraries.findAll();
        return libList;
    }

    public static List<Material> searchByPublisher(String publisher) {
        return publishers.findByName(publisher).getBooks();
    }

    public static List<Material> searchByAuthor(String author) {
        return authors.findByName(author).getBooks();
    }

    public static Material searchByName(String name) {
        return materials.findByName(name);
    }

    public static ArrayList<Author> authorList() {
        return authors.authorList();
    }

    public static ArrayList<Publisher> publisherList() {
        return publishers.publisherList();
    }


    public static ArrayList<String> listCategories()
    {
        ArrayList<String> categories = new ArrayList<>();
        categories.add("Horror");
        categories.add("Thriller");
        categories.add("Mystery");
        categories.add("Literary Fiction");
        categories.add("History");
        categories.add("Science-fiction");
        categories.add("Biographies");
        categories.add("Autobiographies");
        categories.add("Poetry");

        return categories;
    }

    /**
     * It takes in a category choice, and returns an ArrayList of Material objects that have that category
     *
     * @param categoryChoice The category choice is the number that the user inputs to select the category.
     * @return An ArrayList of Material objects.
     */
    public static ArrayList<Material> searchByCategory(int categoryChoice)
    {
        Category category = null;
        switch(categoryChoice)
        {
            case 0:
                category = Category.HORROR;
                break;
                
            case 1:
                category = Category.THRILLER;
                break;
                
            case 2:
                category = Category.MYSTERY;
                break;
                
            case 3:
                category = Category.LITERARY_FICTION;
                break;
                
            case 4:
                category = Category.HISTORY;
                break;
                
            case 5:
                category = Category.SCIENCE_FICTION;
                break;
                
            case 6:
                category = Category.BIOGRAPHIES;
                break;
                
            case 7:
                category = Category.AUTOBIOGRAPHIES;
                break;
                
            case 8:
                category = Category.POETRY;
                break;
                
            default:
                break;

        }
        ArrayList<Material> materialList = materials.findByCategories(category);
        return materialList;
    }

    public static ArrayList<Material> searchByRate(int rate)
    {
        return materials.findByRate(rate);
    }

    public static void addRate(Material material, int rate)
    {
        material.addRate(rate);
    }

    public static void addLibrary(String name, int cityInt)
    {
        City cityOfLib = null;

        switch(cityInt)
        {
            case 0:
                cityOfLib = City.ISTANBUL;
                break;

            case 1:
                cityOfLib = City.ANKARA;
                break;

            case 2:
                cityOfLib = City.IZMIR;
                break;

            case 3:
                cityOfLib = City.KOCAELI;
                break;

            default:
                break;
        }
        Library newLib = new Library(name, cityOfLib);
        libraries.create(newLib);
    }

    public static void removeLibrary(Library target)
    {
        libraries.remove(target);
    }

    public static void addAccount(Account newAccount)
    {
        accounts.create(newAccount);
    }

    /**
     * This function removes an account from the list of accounts
     *
     * @param newAccount The account to be removed from the list.
     */
    public static void removeAccount(Account newAccount)
    {
        accounts.remove(newAccount);
    }

    /**
     * This function returns an ArrayList of Librarian objects.
     *
     * @return The list of librarians.
     */
    public static ArrayList<Librarian> getLibrarianList()
    {
        return accounts.getLibrarianList();
    }

    /**
     * This function returns an ArrayList of User objects that are readers
     *
     * @return An ArrayList of User objects.
     */
    public static ArrayList<User> getReaderList()
    {
        return accounts.getReaderList();
    }

    /**
     * > This function returns an ArrayList of LibraryManager objects
     *
     * @return The list of library managers.
     */
    public static ArrayList<LibraryManager> getLibraryManagerList()
    {
        return accounts.getLibraryManagerList();
    }

    /**
     * If the publisher and author of the new material don't exist in the database, add them. Then add the material
     *
     * @param newMaterial The material to be added to the database.
     */
    public static void addMaterial(Material newMaterial)
    {
        if(publishers.findByName(newMaterial.getPublisher().getName())==null)
            publishers.create(newMaterial.getPublisher());

        if(authors.findByName(newMaterial.getAuthor().getName())==null)
            authors.create(newMaterial.getAuthor());

        materials.create(newMaterial);
    }

    /**
     * > Removes a material from the library
     *
     * @param name The name of the material to be removed
     */
    public static void removeMaterial(String name)
    {
        Material target = searchByName(name);
        target.getPublisher().remove(target);
        target.getAuthor().removeBook(target);

        if(target.getAuthor().getBooks().size()==0)
            authors.remove(target.getAuthor());

        if(target.getPublisher().getBooks().size()==0)
            publishers.remove(target.getPublisher());

        materials.remove(target);
    }

    /**
     * This function takes a string as an argument and searches for a material with that name. If it finds a material with
     * that name, it sets the material's isLoaned attribute to true
     *
     * @param name The name of the book to be added to the loaned list.
     */
    public static void addLoanBook(String name)
    {
        Material target = searchByName(name);
        target.setIsLoaned(true);
    }

    /**
     * This function removes a loaned book from the library
     *
     * @param name The name of the book to be removed from the loaned list.
     */
    public static void removeLoanBook(String name)
    {
        Material target = searchByName(name);
        target.setIsLoaned(false);
    }

    /**
     * This function edits the name and surname of a user.
     *
     * @param name The new name of the user
     * @param surname The new surname of the user
     * @param username The username of the user you want to edit.
     */
    public void editUser(String name, String surname, String username)
    {
        Account targetUser = accounts.viewInfo(username);
        targetUser.setName(name);
        targetUser.setSurname(surname);
    }

    /**
     * Edits the password of a user
     * @param password Updated password
     * @param targetUsername Target username
     */
    public void editUser(String password, String targetUsername)
    {
        Account targetUser = accounts.viewInfo(targetUsername);
        targetUser.setPassword(password);
    }

    /**
     * This function returns an ArrayList of all the materials in the database.
     *
     * @return An ArrayList of Material objects.
     */
    public ArrayList<Material> listAllMaterial()
    {
        return materials.listAllMaterials();
    }

    /**
     * This function returns an ArrayList of Materials that are currently on loan.
     *
     * @return An ArrayList of Material objects.
     */
    public ArrayList<Material> listLoanMaterial()
    {
        return materials.listLoanMaterials();
    }




}
