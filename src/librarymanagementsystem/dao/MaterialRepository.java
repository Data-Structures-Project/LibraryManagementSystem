package librarymanagementsystem.dao;

import librarymanagementsystem.model.Category;
import librarymanagementsystem.model.Material;
import utility.SkipList;

import java.util.List;

public interface MaterialRepository {
    SkipList<Material> getAll();

    Material findById(int id);

    Material findByName(String name);

    /* List<Material> findByAuthorId(Long id); */
    List<Material> findByCategories(Category category);

    /* List<Material> findByPublisher(Publisher publisher); */
    List<Material> findByRate(Integer rate);

    void create(Material material);

    public Material update(Material targetMaterial, Material newMaterial);

    void remove(Material material);

    Material viewInfo(String name);
}
