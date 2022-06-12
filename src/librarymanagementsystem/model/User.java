package librarymanagementsystem.model;

import java.util.LinkedList;
import java.util.List;

public class User extends Account{
    // Creating a list of materials.
    private List<Material> materials;

    public User(String name, String surname, String username, String password, Library library) {
        super(name, surname, username, password, library);
        materials = new LinkedList<>();
    }

    /**
     * This function adds a material to the materials list
     *
     * @param material The material to add to the list.
     * @return A boolean value.
     */
    public boolean addMaterial(Material material){
        return materials.add(material);
    }

    /**
     * This function removes a material from the list of materials
     *
     * @param material The material to remove from the list.
     * @return A boolean value.
     */
    public boolean removeMaterial(Material material){
        return materials.remove(material);
    }

    /**
     * This function returns true if the material is in the materials list
     *
     * @param material The material to search for.
     * @return A boolean value.
     */
    public boolean searchMaterial(Material material){
        return materials.contains(material);
    }

}
