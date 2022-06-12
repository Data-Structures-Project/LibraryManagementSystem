package librarymanagementsystem.dao;

import librarymanagementsystem.model.Library;
import utility.Graph;
import utility.Vertex;

import java.util.ArrayList;

// Defining the interface for the LibraryRepository class.
public interface LibraryRepository {
    /**
     * Find all libraries.
     *
     * @return An ArrayList of Library objects.
     */
    ArrayList<Library> findAll();
    /**
     * Find a library by its id.
     *
     * @param id The id of the library you want to find.
     * @return A Library object.
     */
    Library findById(int id);
    /**
     * Find a library by name.
     *
     * @param name The name of the library.
     * @return A Library object
     */
    Library findByName(String name);
    /**
     * Create a new library.
     *
     * @param library The library to create.
     */
    void create(Library library);
    /**
     * Update the library with the given id.
     *
     * @param library The library object to be updated.
     * @return The updated library.
     */
    Library update(Library library);
    /**
     * Remove the library from the database.
     *
     * @param library The library to remove.
     */
    void remove(Library library);
    /**
     * Given a library name, return the library's information.
     *
     * @param name The name of the library.
     * @return A Library object
     */
    Library viewInfo(String name);
}
