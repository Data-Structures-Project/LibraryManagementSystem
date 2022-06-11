package librarymanagementsystem.dao;

import librarymanagementsystem.model.Author;
import librarymanagementsystem.model.Publisher;
import utility.AVLTree;
import utility.BinarySearchTree;

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
    public List<Publisher> findByName(String name) {
        return null;
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
    public String viewInfo(String name) {
        Publisher targetPublisher = this.publishers.find(new Publisher(name));
        return targetPublisher.toString();
    }
}
