package librarymanagementsystem.dao;

import librarymanagementsystem.model.Library;
import utility.Graph;

public interface LibraryRepository {
    Graph findAll();
    Library findById(int id);
    Library findByName(String name);
    void create(Library library);
    Library update(Library library);
    void remove(Library library);
    Library viewInfo(String name);
}
