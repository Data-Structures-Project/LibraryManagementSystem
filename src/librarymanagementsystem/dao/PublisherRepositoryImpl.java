package librarymanagementsystem.dao;

import librarymanagementsystem.model.Publisher;
import utility.BinarySearchTree;

import java.util.List;

public class PublisherRepositoryImpl implements PublisherRepository {
    private BinarySearchTree<Publisher> publishers; //BBST OLSUN

    public PublisherRepositoryImpl(){
        publishers = new BinarySearchTree<>();
    }

    @Override
    public List<Publisher> findAll() {
        return null;
    }

    @Override
    public Publisher findById(Long id) {
        return null;
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

    }

    @Override
    public Publisher update(Publisher publisher) {
        return null;
    }

    @Override
    public void remove(Publisher publisher) {

    }

    @Override
    public String viewInfo(Long id) {
        return null;
    }
}
