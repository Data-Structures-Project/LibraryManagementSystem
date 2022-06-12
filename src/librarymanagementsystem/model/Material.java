package librarymanagementsystem.model;

import java.util.Date;
import java.util.Objects;
import java.util.Stack;

/**
 * Material is a class that represents a material in the library
 */
public class Material implements Comparable<Material> {
    // Used to assign an id to each material.
    private int id;
    // Used to assign an id to each material.
    private static int count;
    // Used to store the type of the material.
    private MaterialType type;
    // A variable that stores the name of the material.
    private String name;
    // Used to store the category of the material.
    private Category category;
    // Used to store the publication date of the material.
    private Date publicationDate;
    // Used to store the author of the material.
    private Author author;
    // Used to store the publisher of the material.
    private Publisher publisher;
    // Used to store the page count of the material.
    private int pageCount;
    // Used to store the location of the material in the library.
    private String location;
    // Used to store the rates of the material.
    private Stack<Integer> rates;
    // Used to store the average rate of the material.
    private Double rateAve = 0.0;
    // Used to store the information about the material.
    private String info;

    // Used to store the library that the material is in.
    private Library lib;

    // Used to store the information about the material's loan status.
    private boolean isLoaned;

    public Material(MaterialType type, String name, Category category, Date publicationDate, Author author,
            Publisher publisher, int pageCount, String location, String info, Library lib) {
        this.id = count++;
        this.type = type;
        this.name = name;
        this.category = category;
        this.publicationDate = publicationDate;
        this.author = author;
        this.publisher = publisher;
        this.pageCount = pageCount;
        this.location = location;
        this.info = info;
        this.isLoaned = false;
        this.lib = lib;
        rates = new Stack<>();
        author.addBook(this);
        publisher.addBook(this);
    }

    public Material(String name) {
        this.name = name;
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
     * Returns the type of material this is.
     *
     * @return The type of material.
     */
    public MaterialType getType() {
        return type;
    }

    /**
     * > This function sets the type of the material
     *
     * @param type The type of material.
     */
    public void setType(MaterialType type) {
        this.type = type;
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
     * This function returns the category of the product.
     *
     * @return The category object.
     */
    public Category getCategory() {
        return category;
    }

    /**
     * This function sets the category of the product to the category passed in as a parameter.
     *
     * @param category The category that the product belongs to.
     */
    public void setCategory(Category category) {
        this.category = category;
    }

    /**
     * This function returns the publication date of the book.
     *
     * @return The publication date of the book.
     */
    public Date getPublicationDate() {
        return publicationDate;
    }

    /**
     * This function sets the publication date of the book
     *
     * @param publicationDate The date the book was published.
     */
    public void setPublicationDate(Date publicationDate) {
        this.publicationDate = publicationDate;
    }

    /**
     * This function returns the author of the book.
     *
     * @return The author object.
     */
    public Author getAuthor() {
        return author;
    }

    /**
     * This function sets the author of the book.
     *
     * @param author The author of the book.
     */
    public void setAuthor(Author author) {
        this.author = author;
    }

    /**
     * This function returns the publisher of the book.
     *
     * @return The publisher object.
     */
    public Publisher getPublisher() {
        return publisher;
    }

    /**
     * This function sets the publisher of the book.
     *
     * @param publisher The publisher object that will be used to publish the message.
     */
    public void setPublisher(Publisher publisher) {
        this.publisher = publisher;
    }

    /**
     * This function returns the number of pages in the book
     *
     * @return The number of pages in the book.
     */
    public int getPageCount() {
        return pageCount;
    }

    /**
     * This function sets the page count of the book.
     *
     * @param pageCount The total number of pages in the document.
     */
    public void setPageCount(int pageCount) {
        this.pageCount = pageCount;
    }

    /**
     * This function returns the location of the current object.
     *
     * @return The location of the event.
     */
    public String getLocation() {
        return location;
    }

    /**
     * This function sets the location of the object.
     *
     * @param location The location of the file.
     */
    public void setLocation(String location) {
        this.location = location;
    }

    /**
     * This function returns a Stack of Integers.
     *
     * @return The rates stack.
     */
    public Stack<Integer> getRates() {
        return rates;
    }

    /**
     * This function sets the rates of the object to the rates passed in.
     *
     * @param rates A stack of integers representing the rates of the different levels of the game.
     */
    public void setRates(Stack<Integer> rates) {
        this.rates = rates;
    }

    /**
     * The function adds a new rate to the list of rates and updates the average rate
     *
     * @param rate the rate to be added
     * @return A boolean value.
     */
    public boolean addRate(Integer rate) {
        rates.add(rate);
        rateAve = ((rateAve*(rates.size()-1))+ rate)/rates.size();
        return true;
    }

    /**
     * Remove the rate from the list of rates.
     *
     * @param rate The rate to remove from the list.
     * @return A boolean value.
     */
    public boolean removeRate(Integer rate) {
        return rates.remove(rate);
    }

    /**
     * This function sets the value of the isLoaned variable to the value of the loan variable
     *
     * @param loan The loan status of the book.
     */
    public void setIsLoaned(boolean loan)
    {
        this.isLoaned = loan;
    }
    /**
     * This function returns the value of the isLoaned variable
     *
     * @return The isLoaned variable is being returned.
     */
    public boolean getIsLoaned()
    {
        return this.isLoaned;
    }

    /**
     * This function sets the library of the current object to the library passed in as a parameter.
     *
     * @param lib The library to use.
     */
    public void setLib(Library lib)
    {
        this.lib = lib;
    }
    /**
     * This function returns the value of the variable lib.
     *
     * @return The library object.
     */
    public Library getLib()
    {
        return this.lib;
    }
    /**
     * This function returns the average rating of the movie
     *
     * @return The average rate of the movie.
     */
    public Double getRateAve() {
        return rateAve;
    }

    /**
     * > This function returns the value of the variable `info`
     *
     * @return The info variable is being returned.
     */
    public String getInfo() {
        return this.info;
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
     * Copies the newMaterial's data to the this material
     * @param newMaterial The material object that has new data
     */
    public void setMaterial(Material newMaterial)
    {
        this.author = newMaterial.author;
        this.category = newMaterial.category;
        this.id = newMaterial.id;
        this.pageCount = newMaterial.pageCount;
        this.info = newMaterial.info;;
        this.name = newMaterial.name;
        this.type = newMaterial.type;
        this.location = newMaterial.location;
        this.publicationDate = newMaterial.publicationDate;
        this.rates = newMaterial.rates;
        this.isLoaned = newMaterial.isLoaned;
        this.lib = newMaterial.lib;
    }

    /**
     * TODO BUNU İSME GÖRE COMPARE ET
     * 
     * @param material the object to be compared.
     * @return
     */
    @Override
    public int compareTo(Material material) {
        try {
            if (material == null)
                throw new NullPointerException();

            return this.name.compareTo(material.getName());
        } catch (NullPointerException e) {
            System.out.println("Material.compareTo: material is null");
        }

        return -1;
    }

    public int compareTo(Integer ID) {
        if (id < ID)
            return -1;

        else if (id > ID)
            return 1;

        else
            return 0;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o)
            return true;
        if (!(o instanceof Material))
            return false;
        Material material = (Material) o;
        return getPageCount() == material.getPageCount() && Objects.equals(getId(), material.getId())
                && getType() == material.getType() && Objects.equals(getName(), material.getName())
                && getCategory() == material.getCategory()
                && Objects.equals(getPublicationDate(), material.getPublicationDate())
                && Objects.equals(getAuthor(), material.getAuthor())
                && getLocation() == material.getLocation() && Objects.equals(getRates(), material.getRates());
    }

    @Override
    public String toString() {
        return "Material{" +
                "id=" + id +
                ", type=" + type +
                ", name='" + name + '\'' +
                ", category=" + category +
                ", publicationDate=" + publicationDate +
                ", author=" + author +
                ", publisher=" + publisher +
                ", pageCount=" + pageCount +
                ", location=" + location +
                ", library=" + lib +
                ", situation=" + isLoaned +
                ", rates=" + rates +
                '}';
    }
}
