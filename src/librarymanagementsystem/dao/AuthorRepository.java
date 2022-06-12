package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;
import utility.AVLTree;

// A declaration of a class.
public interface AuthorRepository {
    /**
     * Find all authors in the database.
     *
     * @return An AVLTree of type Author.
     */
    AVLTree<Author> findAll();
    /**
     * Find an author by id.
     *
     * @param id The id of the author you want to find.
     * @return An Author object.
     */
    Author findById(int id);
    /**
     * Find an author by name.
     *
     * @param name The name of the method.
     * @return A single Author object.
     */
    Author findByName(String name);
    /**
     * Creates a new author.
     *
     * @param author The author object to be created.
     */
    void create(Author author);
    /**
     * Update the target author with the new author's data.
     *
     * @param targetAuthor The author you want to update.
     * @param newAuthor The new author object that will be used to update the targetAuthor.
     * @return The updated author.
     */
    Author update(Author targetAuthor, Author newAuthor);
    /**
     * Removes the given author from the database.
     *
     * @param author The author to remove from the database.
     */
    void remove(Author author);
    /**
     * Given a name, return the author object that has that name.
     *
     * @param name The name of the author to view.
     * @return A view of the author's information.
     */
    Author viewInfo(String name);
}
