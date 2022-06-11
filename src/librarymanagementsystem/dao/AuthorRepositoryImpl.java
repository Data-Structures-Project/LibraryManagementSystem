package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;
import utility.AVLTree;

import java.util.ArrayList;

/* NOT : FIND BY AUTHOR METODU BURADA OLACAK */
/* NOT : FIND BY AUTHOR METODU BURADA OLACAK */
/* NOT : FIND BY AUTHOR METODU BURADA OLACAK */
/* NOT : FIND BY AUTHOR METODU BURADA OLACAK */
/* NOT : FIND BY AUTHOR METODU BURADA OLACAK */


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
        return (Author) authors.searchByName(name);
    }

    @Override
    public void create(Author author) {
        authors.add(author);
    }


    public Author update(Author targetAuthor, Author newAuthor) {
        Author target = authors.find(targetAuthor);
        target.setAuthor(newAuthor);
        return newAuthor;
    }

    @Override
    public void remove(Author author) {
        authors.remove(author);
    }

   @Override
    public Author viewInfo(String name) {
       Author targetAuthor = this.authors.find(new Author(name));
       return targetAuthor;
    }

    public ArrayList<Author> authorList()
    {
        return (ArrayList<Author>) authors.createList();
    }
}
