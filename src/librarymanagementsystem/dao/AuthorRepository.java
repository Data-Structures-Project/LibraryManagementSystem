package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;
import utility.AVLTree;

public interface AuthorRepository {
    AVLTree<Author> findAll();
    Author findById(int id);
    Author findByName(String name);
    void create(Author author);
    Author update(Author targetAuthor, Author newAuthor);
    void remove(Author author);
    Author viewInfo(String name);
}
