package librarymanagementsystem.model;

public class Personnel extends Account {
    public Personnel(int id, String name, String surname, String username, String password, Library library) {
        super(id, name, surname, username, password, library);
    }
}
