package librarymanagementsystem.dao;

import librarymanagementsystem.model.Category;
import librarymanagementsystem.model.Material;
import librarymanagementsystem.model.Publisher;
import utility.SkipList;

import java.util.ArrayList;
import java.util.List;
import java.util.PriorityQueue;

public class MaterialRepositoryImpl implements MaterialRepository {
    private SkipList<Material> materials;

    public MaterialRepositoryImpl(){
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
     * @param id Target ID
     * @return Returns the found Material. If there is no match, returns NULL
     */
    @Override
    public Material findById(int id) {
        return materials.traverseById(id);
    }

    /**
     * Searches the Material (Skip List Data) by name
     * @param name Target name
     * @return Returns the found Material. If there is no match, returs null
     */
    @Override
    public Material findByName(String name) {
        return materials.search(new Material(name));
    }

   /* Silindi @Override
    public List<Material> findByAuthorId(Long id) {
        return null;
    }*/

    /**
     * TODO Bütün skip listi traverse et, bi tane ARRAYLİSTE SOK. arraylisti sort et, arraylisti printle
     * @param category
     * @return
     */
    @Override
    public List<Material> findByCategories(Category category) {
        ArrayList<Material> material;
        return null;
    }

    /*@Override
    public List<Material> findByPublisher(Publisher publisher) {
        return null;
    }*/

    /**
     * * TODO Bütün skip listi traverse et, bi tane ARRAYLİSTE SOK. arraylisti sort et, arraylisti printle
     * @param rate
     * @return
     */
    @Override
    public List<Material> findByRate(Integer rate) {
        return null;
    }

    @Override
    public void create(Material material) {

    }

    @Override
    public Material update(Material material) {
        return null;
    }

    @Override
    public void remove(Material material) {

    }

    /**
     * Parameteryi string yaptık, id idi
     * @param name
     * @return
     */
    @Override
    public String viewInfo(String name) {
        return null;
    }
}
