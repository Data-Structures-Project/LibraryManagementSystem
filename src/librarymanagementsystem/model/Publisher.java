package librarymanagementsystem.model;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

public class Publisher implements Comparable<Publisher>{
    // A unique identifier for each publisher.
    private int id;
    // A static variable that is used to generate a unique id for each publisher.
    private static int count;
    // A variable that stores the name of the publisher.
    private String name;
    // Creating a new ArrayList of Material objects.
    private List<Material> materials = new ArrayList<>();
    // A variable that stores the information about the publisher.
    private String info;

    public Publisher(String name, String info) {
        this.id = count++;
        this.name = name;
        this.info = info;

    }

    public Publisher(String name)
    {
        this.name = name;
    }

    /**
     * This function sets the values of the current Publisher object to the values of the target Publisher object
     *
     * @param targetPublisher The publisher object that you want to copy.
     */
    public void setPublisher(Publisher targetPublisher)
    {
        this.id = targetPublisher.id;
        this.name = targetPublisher.name;
        this.materials = targetPublisher.materials;
        this.info = info;
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
     * This function adds a material to the materials list
     *
     * @param material The material to add to the book.
     * @return A boolean value.
     */
    public boolean addBook(Material material){
        return materials.add(material);
    }

    /**
     * This function removes a material from the list of materials
     *
     * @param material The material to remove from the list.
     * @return A boolean value.
     */
    public boolean remove(Material material){
        return materials.remove(material);
    }

    @Override
    // This function is checking if the object is equal to the object passed in.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Publisher)) return false;
        Publisher publisher = (Publisher) o;
        return Objects.equals(getName(), publisher.getName()) && Objects.equals(getBooks(), publisher.getBooks()) && Objects.equals(getInfo(), publisher.getInfo());
    }

    @Override
    // This function is returning the hash code of the object.
    public int hashCode() {
        return Objects.hash(getName(), getBooks(), getInfo());
    }

    @Override
    // This function is comparing the name of the publisher to the name of the publisher passed in.
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

    /**
     * The toString() function returns the name of the object.
     *
     * @return The name of the object.
     */
    public String toString()
    {return name;}
}
