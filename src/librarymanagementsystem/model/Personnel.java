package librarymanagementsystem.model;

public class Personnel extends Account{
    public Personnel(int id, String name, String username, String surname, String password, Library library) {
        super(id, name, surname, username, password, library);
    }
}
