package librarymanagementsystem.model;

/**
 * Librarian is a subclass of Account
 */
public class Librarian extends Account {
    public Librarian(String name, String surname, String username, String password, Library library) {
        super(name, surname, username, password, library);
    }
}

