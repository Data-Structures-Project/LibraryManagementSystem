package librarymanagementsystem.model;

import java.util.LinkedList;
import java.util.List;

public class User extends Account{
    private List<Material> materials;

    public User(int id, String name, String username, String surname, String password, Library library) {
        super(id, name, surname, username, password, library);
        materials = new LinkedList<>();
    }

    public boolean addMaterial(Material material){
        return materials.add(material);
    }

    public boolean removeMaterial(Material material){
        return materials.remove(material);
    }

    public boolean searchMaterial(Material material){
        return materials.contains(material);
    }

}
