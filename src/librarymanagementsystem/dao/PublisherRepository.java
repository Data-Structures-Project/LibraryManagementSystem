package librarymanagementsystem.dao;

import librarymanagementsystem.model.Publisher;

import java.util.List;

public interface PublisherRepository {
    List<Publisher> findAll();
    Publisher findById(Long id);
    List<Publisher> findByName(String name);
    void create(Publisher publisher);
    Publisher update(Publisher publisher);
    void remove(Publisher publisher);
    String viewInfo(Long id);
}
