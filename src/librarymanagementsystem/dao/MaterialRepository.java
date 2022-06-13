package librarymanagementsystem.dao;

import librarymanagementsystem.model.Category;
import librarymanagementsystem.model.Material;
import utility.SkipList;

import java.util.List;

// Defining the interface for the MaterialRepository class.
public interface MaterialRepository {
    /**
     * Get all the materials in the database.
     *
     * @return A SkipList of Materials
     */
    SkipList<Material> getAll();

    /**
     * Find a material by its id.
     *
     * @param id The id of the material you want to find.
     * @return A Material object.
     */
    Material findById(int id);

    /**
     * Find a material by its name.
     *
     * @param name The name of the material to find.
     * @return A Material object
     */
    Material findByName(String name);

    /* List<Material> findByAuthorId(int id); */
    List<Material> findByCategories(Category category);

    /* List<Material> findByPublisher(Publisher publisher); */
    List<Material> findByRate(Double rate);

    /**
     * Create a new material.
     *
     * @param material The material to use for the mesh.
     */
    void create(Material material);

    /**
     * Update the material with the given ID with the given new material.
     *
     * @param targetMaterial The material you want to update.
     * @param newMaterial The new material to be updated.
     * @return The updated material.
     */
    public Material update(Material targetMaterial, Material newMaterial);

    /**
     * Removes the given material from the list of materials
     *
     * @param material The material to remove.
     */
    void remove(Material material);

    /**
     * Given a name, return the view info for the view with that name.
     *
     * @param name The name of the material to view.
     * @return A Material object.
     */
    Material viewInfo(String name);
}
