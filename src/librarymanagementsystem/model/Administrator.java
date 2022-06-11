package librarymanagementsystem.model;

public class Administrator extends Account {
    public Administrator(String name, String surname, String username, String password, Library library) {
        super(name, surname, username, password, library);
    }
}
