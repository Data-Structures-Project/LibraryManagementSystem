package librarymanagementsystem.dao;

import librarymanagementsystem.model.Publisher;
import utility.AVLTree;

import java.util.ArrayList;
import java.util.List;

/**
 * It's a class that implements the PublisherRepository interface
 */
public class PublisherRepositoryImpl implements PublisherRepository {
    // Creating a new AVLTree object.
    private AVLTree<Publisher> publishers; //BBST OLSUN

    public PublisherRepositoryImpl(){
        publishers = new AVLTree<>();
    }

    @Override
    // Returning the publishers.
    public AVLTree<Publisher> findAll() {
        return publishers;
    }

    @Override
    // Searching the publisher by ID.
    public Publisher findById(int ID) {
        return (Publisher) publishers.serchByID(ID);
    }

    /**
     * Menüde, Publisher'a göre search etme seçildiğinde ve isim girilince. Burası çalışır
     * Gider this.publishers arar, yazarı bulur. İçerisindeki bütün kitapları printler. (İçerisindeki kitaplar modeldeki publisher classı içinde)
     * @param name
     * @return
     */
    @Override
    public Publisher findByName(String name) {
        return (Publisher) publishers.find(new Publisher(name));
    }

    @Override
    // Adding the publisher to the publisher data.
    public void create(Publisher publisher) {
        publishers.add(publisher);
    }


    // Updating the target publisher with the new publisher.
    public Publisher update(Publisher targetPublisher, Publisher newPublisher) {
        Publisher target = publishers.find(targetPublisher);
        target.setPublisher(newPublisher);
        return newPublisher;
    }

    /**
     * Removes the target publisher from the Publisher data
     * @param publisher Target Publisher
     */
    @Override
    public void remove(Publisher publisher) {
        publishers.remove(publisher);
    }

    @Override
    // A method that returns the publisher with the given name.
    public Publisher viewInfo(String name) {
        Publisher targetPublisher = this.publishers.find(new Publisher(name));
        return targetPublisher;
    }

    // Creating a list of publishers.
    public ArrayList<Publisher> publisherList()
    {
        return (ArrayList<Publisher>) publishers.createList();
    }
}
