package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;
import utility.AVLTree;

import java.util.List;

public interface AuthorRepository {
    AVLTree<Author> findAll();
    Author findById(int id);
    Author findByName(String name);
    void create(Author author);
    Author update(Author targetAuthor, Author newAuthor);
    void remove(Author author);
    String viewInfo(String name);
}
