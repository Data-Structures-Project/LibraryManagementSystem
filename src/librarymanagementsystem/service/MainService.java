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
    // Creating a new instance of the AccountRepositoryImpl class.
    static AccountRepositoryImpl accounts = new AccountRepositoryImpl();
    // Creating a new instance of the AuthorRepositoryImpl class.
    static AuthorRepositoryImpl authors = new AuthorRepositoryImpl();
    // Creating a new instance of the LibraryRepositoryImpl class.
    static LibraryRepositoryImpl libraries = new LibraryRepositoryImpl();
    // Creating a new instance of the MaterialRepositoryImpl class.
    static MaterialRepositoryImpl materials = new MaterialRepositoryImpl();
    // Creating a new instance of the PublisherRepositoryImpl class.
    static PublisherRepositoryImpl publishers = new PublisherRepositoryImpl();

    /**
     * It creates 3 libraries, 9 accounts, 3 publishers, 3 authors and 5 materials
     */
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
                author1, publisher1, 1024, "A1", "Beyaz Dis Info", library1);
        Material material2 = new Material(MaterialType.MAGAZINE, "Sefaya Selam olsun", Category.MYSTERY,
                new Date(1997513),
                author2, publisher2, 1130,"A2", "Sefaya Selam olsun Info", library2);
        Material material3 = new Material(MaterialType.MAGAZINE, "Iradenin gucu", Category.LITERARY_FICTION,
                new Date(1997513),
                author2, publisher3, 1120, "C3", "Iradenin gucu Info", library3);
        Material material4 = new Material(MaterialType.MAGAZINE, "Olmaz boyle math", Category.THRILLER,
                new Date(1997513),
                author3, publisher2, 2410,"B1", "Olmaz boyle math Info", library2);
        Material material5 = new Material(MaterialType.BOOK, "Yalan dünya", Category.AUTOBIOGRAPHIES, new Date(1997513),
                author3, publisher1, 510, "A3", "Yalan dünya Info", library1);

        materials.create(material1);
        materials.create(material2);
        materials.create(material3);
        materials.create(material4);
        materials.create(material5);

        // author1.addBook(material1);
        // author2.addBook(material2);
        // author3.addBook(material4);
    }

    /**
     * Create a new library with the given name and city, and add it to the list of libraries.
     *
     * @param name The name of the library
     * @param city The city that the library is in.
     */
    public static void createLibrary(String name, City city) {
        libraries.create(new Library(name, city));
    }

    /**
     * If the username exists in the database, and the password matches the password in the database, return the account
     *
     * @param username The username of the account you want to login to.
     * @param password The password of the account
     * @return The account object that matches the username and password.
     */
    public static Account login(String username, String password) {
        Account tempAccount = accounts.viewInfo(username);
        if (accounts.viewInfo(username) != null) {
            if (tempAccount.getPassword().equals(password))
                return tempAccount;
        }
        return null;
    }

    /**
     * If the username is not already in the database, create a new user with the given parameters and return true,
     * otherwise return false
     *
     * @param name The name of the user
     * @param surname String
     * @param username The username of the user
     * @param password the password of the user
     * @param libraryId The id of the library the user is registering to.
     * @return A boolean value.
     */
    public static boolean register(String name, String surname, String username, String password, int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new User(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }

    /**
     * If the username is not taken, create a new LibraryManager account with the given parameters
     *
     * @param name The name of the library manager
     * @param surname String
     * @param username The username of the account to be created.
     * @param password String
     * @param libraryId The id of the library that the manager will be assigned to.
     * @return A boolean value.
     */
    /**
     * If the username is not taken, create a new LibraryManager account with the given parameters
     *
     * @param name The name of the library manager
     * @param surname String
     * @param username The username of the account to be created.
     * @param password String
     * @param libraryId The id of the library that the manager will be assigned to.
     * @return A boolean value.
     */
    public static boolean addLibraryManager(String name, String surname, String username, String password,
            int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new LibraryManager(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }


    /**
     * > If the account does not exist, create a new librarian account with the given parameters
     *
     * @param name The name of the librarian
     * @param surname String
     * @param username the username of the librarian
     * @param password String
     * @param libraryId the id of the library the librarian is assigned to
     * @return A boolean value.
     */
    public static boolean addLibrarian(String name, String surname, String username, String password, int libraryId) {
        if (accounts.viewInfo(username) == null) {
            accounts.create(new Librarian(name, surname, username, password, libraries.findById(libraryId)));
            return true;
        } else
            return false;
    }

    /**
     * If the username is not in the database, then it is unique
     *
     * @param username the username of the account
     * @return A boolean value.
     */
    public static boolean isUniqueUserName(String username) {
        if (accounts.viewInfo(username) == null)
            return true;
        return false;
    }

    /**
     * > This function returns a list of all the libraries in the database
     *
     * @return An ArrayList of Library objects.
     */
    public static ArrayList<Library> listLibraries() {
        ArrayList<Library> libList = libraries.findAll();
        return libList;
    }

    /**
     * Find the publisher with the given name, and return the list of books that publisher has published.
     *
     * @param publisher The name of the publisher to search for.
     * @return A list of books published by the publisher.
     */
    public static List<Material> searchByPublisher(String publisher) {
        return publishers.findByName(publisher).getBooks();
    }

    /**
     * Given an author's name, return a list of all the books that author has written.
     *
     * @param author The name of the author to search for.
     * @return A list of books written by the author.
     */
    public static List<Material> searchByAuthor(String author) {
        return authors.findByName(author).getBooks();
    }

    /**
     * Search for a material by name.
     *
     * @param name The name of the material.
     * @return A material object
     */
    public static Material searchByName(String name) {
        return materials.findByName(name);
    }

    /**
     * Return the list of authors.
     *
     * @return An ArrayList of Author objects.
     */
    public static ArrayList<Author> listAuthors() {
        return authors.authorList();
    }

    /**
     * Return the list of publishers.
     *
     * @return An ArrayList of Publisher objects.
     */
    public static ArrayList<Publisher> publisherList() {
        return publishers.publisherList();
    }


    /**
     * It returns an ArrayList of Strings that contains the names of all the categories
     *
     * @return An ArrayList of Strings
     */
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

    /**
     * Search for materials by rate.
     *
     * @param rate The rate of the material
     * @return An ArrayList of Materials
     */
    public static ArrayList<Material> searchByRate(int rate)
    {
        return materials.findByRate(rate);
    }

    /**
     * It adds a rate to a material
     *
     * @param material The material you want to add a rate to.
     * @param rate The rate at which the material will be mined.
     */
    public static void addRate(Material material, int rate)
    {
        material.addRate(rate);
    }

    /**
     * It takes a name and a city as parameters, creates a new library object with the given name and city, and adds it to
     * the library list
     *
     * @param name The name of the library.
     * @param cityInt 0 for Istanbul, 1 for Ankara, 2 for Izmir, 3 for Kocaeli
     */
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

    /**
     * Remove the specified library from the list of libraries.
     *
     * @param target The library to remove.
     */
    public static void removeLibrary(Library target)
    {
        libraries.remove(target);
    }

    /**
     * This function creates a new account in the database.
     *
     * @param newAccount The account to be added to the database.
     */
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
     * This function edits the name and surname of a account.
     *
     * @param name The new name of the user
     * @param surname The new surname of the user
     * @param username The username of the user you want to edit.
     */
    public void editAccount(String name, String surname, String username)
    {
        Account targetUser = accounts.viewInfo(username);
        targetUser.setName(name);
        targetUser.setSurname(surname);
    }

    /**
     * Edits the password of a account
     * @param password Updated password
     * @param targetUsername Target username
     */
    public void editAccount(String password, String targetUsername)
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


    /**
     * If the author doesn't exist, create it.
     *
     * @param name The name of the author
     * @param surname The author's surname.
     * @param info a short description of the author
     * @return Author
     */
    public static Author addAuthor(String name, String surname, String info)
    {
        Author newAuthor = new Author(name, surname, info);
        if(authors.findByName(newAuthor.getName())==null)
            authors.create(newAuthor);
        return newAuthor;
    }


    /**
     * If the publisher doesn't exist, create it.
     *
     * @param name The name of the publisher
     * @param info The information about the publisher.
     * @return The newPublisher object is being returned.
     */
    public Publisher addPublisher(String name, String info)
    {
        Publisher newPublisher = new Publisher(name, info);
        if(publishers.findByName(newPublisher.getName())==null)
            publishers.create(newPublisher);
        return newPublisher;
    }
    /**
     * This function adds a material to the library.
     *
     * @param type The type of material.
     * @param name The name of the material
     * @param category The category of the material.
     * @param publicationDate Date object
     * @param author Author object
     * @param publisher Publisher object
     * @param pageCount The number of pages in the material.
     * @param location The location of the material in the library.
     * @param info a string that contains any additional information about the material
     * @param lib The library that the material is in.
     * @return A new Material object.
     */
    public Material addMaterial(MaterialType type, String name, Category category, Date publicationDate, Author author,
                                Publisher publisher, int pageCount, String location, String info, Library lib)
    {
        Material newMaterial = new Material(type, name, category, publicationDate, author,
            publisher, pageCount, location, info, lib);

        addMaterial(newMaterial);
        return newMaterial;
    }








}
