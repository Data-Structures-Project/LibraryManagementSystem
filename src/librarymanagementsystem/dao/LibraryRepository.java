package librarymanagementsystem.dao;

import librarymanagementsystem.model.Library;

import java.util.List;

public interface LibraryRepository {
    List<Library> findAll();
    Library findById(Long id);
    List<Library> findByName(String name);
    void create(Library library);
    Library update(Library library);
    void remove(Library library);
    String viewInfo(Long id);
}
