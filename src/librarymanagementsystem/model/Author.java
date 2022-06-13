package librarymanagementsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Author implements Comparable<Author>{
    // A unique identifier for the author.
    private int id;
    // A static variable that is used to generate unique id for each author.
    private static int count;
    // A variable that stores the name of the author.
    private String name;
    // A variable that stores the surname of the author.
    private String surname;
    // A variable that stores the information about the author.
    private String info;
    // Creating a new ArrayList of Material objects.
    private List<Material> materials = new ArrayList<Material>();

    public Author(String name, String surname, String info) {
        this.id = count++;
        this.name = name;
        this.surname = surname;
        this.info = info;
    }

    public Author(String name)
    {
        this.name = name;
    }

    /**
     * This function sets the current author to the author passed in as a parameter.
     *
     * @param newAuthor The author object that will be used to set the current author's parameters.
     */
    public void setAuthor(Author newAuthor)
    {
        this.id = newAuthor.id;
        this.name = newAuthor.name;
        this.surname = newAuthor.surname;
        this.info = newAuthor.info;
        this.materials = newAuthor.materials;
    }

    /**
     * This function returns the id of the object
     *
     * @return The id of the object.
     */
    public int getId() {
        return id;
    }

    /**
     * This function sets the id of the object to the value of the parameter id.
     *
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function sets the name of the object to the value of the parameter.
     *
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * > This function returns the surname of the person
     *
     * @return The surname of the person.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * This function sets the surname of the person to the value of the parameter.
     *
     * @param surname The surname of the person.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * > This function returns the value of the info variable
     *
     * @return The info variable is being returned.
     */
    public String getInfo() {
        return info;
    }

    /**
     * This function sets the info variable to the value of the info parameter.
     *
     * @param info The message to be displayed.
     */
    public void setInfo(String info) {
        this.info = info;
    }

    /**
     * This function returns a list of materials.
     *
     * @return A list of materials.
     */
    public List<Material> getBooks() {
        return materials;
    }

    /**
     * This function sets the materials of the class to the materials passed in.
     *
     * @param materials The list of materials to be displayed in the list.
     */
    public void setBooks(List<Material> materials) {
        this.materials = materials;
    }

    /**
     * This function adds a material to the materials list
     *
     * @param material The material to add to the book.
     * @return A boolean value.
     */
    public boolean addBook(Material material){
        return materials.add(material);
    }

    /**
     * > This function removes a book from the library
     *
     * @param material The material to be removed from the library.
     * @return The method is returning the material that is being removed.
     */
    public boolean removeBook(Material material){
        return materials.remove(material);
    }

    @Override
    // This function is comparing the current object to the object passed in as a parameter.
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Author))
            return false;

        Author author = (Author) o;
        return Objects.equals(getName(), author.getName()) && Objects.equals(getSurname(), author.getSurname());
    }

    @Override
    // This function is returning the hash code of the object.
    public int hashCode() {
        return Objects.hash(getName(), getSurname());
    }

    @Override
    // This function is comparing the current object to the object passed in as a parameter.
    public int compareTo(Author author) {
        return this.name.compareTo(author.getName());
    }

    public String toString()
    {return String.format(name+' '+surname);}
}
