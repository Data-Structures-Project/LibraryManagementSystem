package librarymanagementsystem.model;

import java.util.LinkedList;
import java.util.List;
import java.util.PriorityQueue;

public class Personnel extends Account{
    public Personnel(Long id, String name, String surname, String password, Library library) {
        super(id, name, surname, password, library);
    }
}
