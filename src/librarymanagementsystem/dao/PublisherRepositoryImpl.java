package librarymanagementsystem.dao;

import librarymanagementsystem.model.Publisher;
import utility.AVLTree;

import java.util.ArrayList;
import java.util.List;

/* NOT : FIND BY Publisher METODU BURADA OLACAK */
/* NOT : FIND BY Publisher METODU BURADA OLACAK */
/* NOT : FIND BY Publisher METODU BURADA OLACAK */
/* NOT : FIND BY Publisher METODU BURADA OLACAK */
/* NOT : FIND BY Publisher METODU BURADA OLACAK */
public class PublisherRepositoryImpl implements PublisherRepository {
    private AVLTree<Publisher> publishers; //BBST OLSUN

    public PublisherRepositoryImpl(){
        publishers = new AVLTree<>();
    }

    @Override
    public AVLTree<Publisher> findAll() {
        return publishers;
    }

    @Override
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
    public void create(Publisher publisher) {
        publishers.add(publisher);
    }


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
    public Publisher viewInfo(String name) {
        Publisher targetPublisher = this.publishers.find(new Publisher(name));
        return targetPublisher;
    }

    public ArrayList<Publisher> publisherList()
    {
        return (ArrayList<Publisher>) publishers.createList();
    }
}
