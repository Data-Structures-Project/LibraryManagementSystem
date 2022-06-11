package librarymanagementsystem.model;

public class Administrator extends Account {
    public Administrator(int id, String name, String surname, String username, String password, Library library) {
        super(id, name, surname, username, password, library);
    }
}
