package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;
import utility.AVLTree;

import java.util.ArrayList;

/**
 * It's a repository class that implements the AuthorRepository interface
 */
public class AuthorRepositoryImpl implements AuthorRepository{
    /**
     * AVL Balanced Tree that store the Authors
     */
    private AVLTree<Author> authors;

    /**
     * Constructor. Creates the AVL Tree
     */
    public AuthorRepositoryImpl(){
        authors = new AVLTree<>();
    }

    /**
     * Returns the Author data.
     * @return Returns the AVL Tree data Author
     */
    @Override
    public AVLTree<Author> findAll() {
        return authors;
    }

    @Override
    // It's a method that returns the author object with the given ID.
    public Author findById(int ID) {
        return (Author) authors.serchByID(ID);
    }

    /**
     * this.authorsa bakacak, yazarı bulacak, içindeki bütün kitapları yazdırcak
     * @param
     * @return
     */
    @Override
    public Author findByName(String name) {
        return (Author) authors.find(new Author(name));
    }

    @Override
    // It adds the author to the AVL Tree.
    public void create(Author author) {
        authors.add(author);
    }


    /**
     * Find the author in the database that matches the target author, and update it with the new author.
     *
     * @param targetAuthor The author you want to update.
     * @param newAuthor The new author object that will be used to update the targetAuthor.
     * @return The newAuthor is being returned.
     */
    public Author update(Author targetAuthor, Author newAuthor) {
        Author target = authors.find(targetAuthor);
        target.setAuthor(newAuthor);
        return newAuthor;
    }

    @Override
    // It removes the author from the AVL Tree.
    public void remove(Author author) {
        authors.remove(author);
    }

   @Override
    // Returning the author object with the given name.
    public Author viewInfo(String name) {
       Author targetAuthor = this.authors.find(new Author(name));
       return targetAuthor;
    }

    /**
     * Return a list of all authors.
     *
     * @return An ArrayList of Author objects.
     */
    public ArrayList<Author> authorList()
    {
        return (ArrayList<Author>) authors.createList();
    }
}
