package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;

import java.util.List;

public interface AuthorRepository {
    List<Author> findAll();
    Author findById(Long id);
    List<Author> findByName(String surname);
    void create(Author author);
    Author update(Author author);
    void remove(Author author);
    String viewInfo(Long id);
}
