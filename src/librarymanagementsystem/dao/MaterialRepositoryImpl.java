package librarymanagementsystem.dao;

import librarymanagementsystem.model.Material;
import utility.SkipList;
import java.util.ArrayList;
import java.util.List;

/**
 * It's a repository class that stores the materials in a skip list
 */
public class MaterialRepositoryImpl implements MaterialRepository {

    /**
     * Skip List that store the materials
     */
    private SkipList<Material> materials;

    /**
     * Constructor. Creates the skip list
     */
    public MaterialRepositoryImpl() {
        materials = new SkipList<>();
    }

    /**
     * Returns the Material data.
     * @return Returns the data Material
     */
    @Override
    public SkipList<Material> getAll() {
        return materials;
    }

    /**
     * Searches the Material (Skip List data) by given ID.
     * 
     * @param id Target ID
     * @return Returns the found Material. If there is no match, returns NULL
     */
    @Override
    public Material findById(int id) {
        return materials.traverseById(id);
    }

    /**
     * Searches the Material (Skip List Data) by name
     * 
     * @param name Target name
     * @return Returns the found Material. If there is no match, returs null
     */
    @Override
    public Material findByName(String name) {
        return materials.search(new Material(name));
    }



    /**
     * TODO Bütün skip listi traverse et, bi tane ARRAYLİSTE SOK. arraylisti sort
     * et, arraylisti printle
     * 
     * @param category
     * @return
     */
    @Override
    public ArrayList<Material> findByCategories(librarymanagementsystem.model.Category category) {
        ArrayList<Material> materialsByCategory = new ArrayList<>();
        return this.materials.traverseByCategory(category, materialsByCategory);
    }


    /**
     * * TODO Bütün skip listi traverse et, bi tane ARRAYLİSTE SOK. arraylisti sort
     * et, arraylisti printle
     * 
     * @param rate
     * @return
     */
    @Override
    public ArrayList<Material> findByRate(Integer rate) {
        ArrayList<Material> materialsByRate = new ArrayList<>();

        return this.materials.traverseByRate(rate, materialsByRate);
    }

    /**
     * Insert the target material to the Material List
     * @param material Target material
     */
    @Override
    public void create(Material material) {
        materials.insert(material);
    }

    /**
     * Finds the target material, change it with new material
     * @param targetMaterial Target material to change
     * @param newMaterial New material to change with target material
     * @return Return the new material
     */
    @Override
    public Material update(Material targetMaterial, Material newMaterial) {
        Material targetMat = materials.search(targetMaterial);
        targetMat.setMaterial(newMaterial);
        return newMaterial;
    }

    /**
     * Removes a material from the Material List.
     * @param material Target material
     */
    @Override
    public void remove(Material material) {
    materials.delete(material);
    }



    /**
     * Returns the information of material that has the target name.
     * @param name Target name
     * @return Return the information
     */
    @Override
    public Material viewInfo(String name) {
        Material targetMaterial = materials.search(new Material(name));
        return targetMaterial;
    }

    /**
     * Traverse the tree and add all the materials to the list.
     *
     * @return An ArrayList of all the materials in the library.
     */
    public ArrayList<Material> listAllMaterials()
    {
        ArrayList<Material> allMaterials = new ArrayList<>();
        return this.materials.traverseAll(allMaterials);
    }

    /**
     * Return an ArrayList of all the materials that are currently on loan.
     *
     * @return An ArrayList of all the materials that are on loan.
     */
    public ArrayList<Material> listLoanMaterials()
    {
        ArrayList<Material> allLoanMaterials = new ArrayList<>();
        return this.materials.traverseByLoan(allLoanMaterials);
    }


}
