package librarymanagementsystem.model;

/**
 * A LibraryManager is an Account that has a Library.
 */
public class LibraryManager extends Account {
    public LibraryManager(String name, String surname, String username, String password, Library library) {
        super(name, surname, username, password, library);
    }
}
