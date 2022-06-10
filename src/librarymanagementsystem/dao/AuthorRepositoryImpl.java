package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;
import utility.BinarySearchTree;

import java.util.List;

public class AuthorRepositoryImpl implements AuthorRepository{
    private BinarySearchTree<Author> authors; //BBST

    public AuthorRepositoryImpl(){
        authors = new BinarySearchTree<>();
    }

    @Override
    public List<Author> findAll() {
        return null;
    }

    @Override
    public Author findById(Long id) {
        return null;
    }

    /**
     * this.authorsa bakacak, yazarı bulacak, içindeki bütün kitapları yazdırcak
     * @param surname
     * @return
     */
    @Override
    public List<Author> findByName(String surname) {
        return null;
    }

    @Override
    public void create(Author author) {

    }

    @Override
    public Author update(Author author) {
        return null;
    }

    @Override
    public void remove(Author author) {

    }

    @Override
    public String viewInfo(Long id) {
        return null;
    }
}
