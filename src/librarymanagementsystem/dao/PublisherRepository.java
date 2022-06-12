package librarymanagementsystem.dao;

import librarymanagementsystem.model.Publisher;
import utility.AVLTree;

import java.util.List;

// A declaration of a class.
public interface PublisherRepository {
    /**
     * Find all publishers in the database.
     *
     * @return An AVLTree of all the publishers in the database.
     */
    AVLTree<Publisher> findAll();
    /**
     * Find a publisher by id.
     *
     * @param id The id of the publisher you want to find.
     * @return Publisher
     */
    Publisher findById(int id);
    /**
     * Find a Publisher by name.
     *
     * @param name The name of the Publisher you want to find.
     * @return Publisher
     */
    Publisher findByName(String name);
    /**
     * Create a new publisher.
     *
     * @param publisher The publisher object to be created.
     */
    void create(Publisher publisher);
    /**
     * Update the publisherTarget with the values of publisherNew.
     *
     * @param publisherTarget The publisher you want to update.
     * @param publisherNew The new publisher object that will be used to update the publisherTarget.
     * @return The updated publisher.
     */
    Publisher update(Publisher publisherTarget, Publisher publisherNew);
    /**
     * Removes the specified publisher from the list of publishers.
     *
     * @param publisher The publisher to remove.
     */
    void remove(Publisher publisher);
    /**
     * "Given a name, return a Publisher that emits the view info for that name."
     *
     * The above function is a good example of a function that returns a Publisher. It's a function that takes a String and
     * returns a Publisher
     *
     * @param name The name of the publisher.
     * @return A Publisher object.
     */
    Publisher viewInfo(String name);
}
