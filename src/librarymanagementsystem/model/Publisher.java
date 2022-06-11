package librarymanagementsystem.model;

import java.util.List;
import java.util.Objects;

public class Publisher implements Comparable<Publisher>{
    private Long id;
    private String name;
    private List<Material> materials;
    private String info;

    public Publisher(Long id, String name, List<Material> materials, String info) {
        this.id = id;
        this.name = name;
        this.materials = materials;
        this.info = info;
    }

    public Publisher(Long id, String name, String info) {
        this.id = id;
        this.name = name;
        this.info = info;
    }

    public Publisher(String name)
    {
        this.name = name;
    }

    public void setPublisher(Publisher targetPublisher)
    {
        this.id = targetPublisher.id;
        this.name = targetPublisher.name;
        this.materials = targetPublisher.materials;
        this.info = info;
    }

    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public List<Material> getBooks() {
        return materials;
    }

    public void setBooks(List<Material> materials) {
        this.materials = materials;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public boolean addBook(Material material){
        return materials.add(material);
    }

    public boolean remove(Material material){
        return materials.remove(material);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(getName(), publisher.getName()) && Objects.equals(getBooks(), publisher.getBooks()) && Objects.equals(getInfo(), publisher.getInfo());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getBooks(), getInfo());
    }

    @Override
    public int compareTo(Publisher publisher) {
        try{
            if(publisher == null)
                throw new NullPointerException();

            return name.compareTo(publisher.name);

        }catch (NullPointerException e){
            System.out.println("Publisher.compareTo: publisher is null");
        }

        return -1;
    }
}
