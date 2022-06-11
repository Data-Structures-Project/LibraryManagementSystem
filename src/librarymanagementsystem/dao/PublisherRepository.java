package librarymanagementsystem.dao;

import librarymanagementsystem.model.Publisher;
import utility.AVLTree;

import java.util.List;

public interface PublisherRepository {
    AVLTree<Publisher> findAll();
    Publisher findById(int id);
    List<Publisher> findByName(String name);
    void create(Publisher publisher);
    Publisher update(Publisher publisherTarget, Publisher publisherNew);
    void remove(Publisher publisher);
    Publisher viewInfo(String name);
}
