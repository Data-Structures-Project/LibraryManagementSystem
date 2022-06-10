package librarymanagementsystem.model;

public class Administrator extends Account{
    public Administrator(Long id, String name, String surname, String password, Library library) {
        super(id, name, surname, password, library);
    }
}
