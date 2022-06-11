package librarymanagementsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author implements Comparable<Author>{
    private int id;
    private static int count;
    private String name;
    private String surname;
    private String info;
    private List<Material> materials;

    public Author(String name, String surname, String info) {
        this.id = count++;
        this.name = name;
        this.surname = surname;
        this.info = info;
        materials = null;
    }

    public Author(String name)
    {
        this.name = name;
    }

    public void setAuthor(Author newAuthor)
    {
        this.id = newAuthor.id;
        this.name = newAuthor.name;
        this.surname = newAuthor.surname;
        this.info = newAuthor.info;
        this.materials = newAuthor.materials;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getInfo() {
        return info;
    }

    public void setInfo(String info) {
        this.info = info;
    }

    public List<Material> getBooks() {
        return materials;
    }

    public void setBooks(List<Material> materials) {
        this.materials = materials;
    }

    public boolean addBook(Material material){
        return materials.add(material);
    }

    public Material removeBook(Material material){
        return removeBook(material);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Author))
            return false;

        Author author = (Author) o;
        return Objects.equals(getName(), author.getName()) && Objects.equals(getSurname(), author.getSurname());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getName(), getSurname());
    }

    @Override
    public int compareTo(Author author) {
        return this.name.compareTo(author.getName());
    }
}
