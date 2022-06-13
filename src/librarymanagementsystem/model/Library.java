package librarymanagementsystem.model;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Objects;
import java.util.Random;

/**
 * A Library is a place where you can borrow books
 */
public class Library implements Comparable<Library>{
    // A private variable that is used to store the id of the library.
    private int id;
    // A static variable that is used to keep track of the number of libraries that have been created.
    private static int count;
    // Declaring a variable called name that is of type String.
    private String name;
    // A variable that is used to store the city that the library is in.
    private City city;
    // Creating an array of doubles called coordinates.

    public Library(String name, City city){
        this.id = count++;
        this.name = name;
        this.city = city;

        Random rand = new Random();
    }

    @Override
    // Checking if the object is equal to the library.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library)) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id) && Objects.equals(name, library.name) && city == library.city;
    }

    @Override
    // A method that is used to generate a hash code for the library.
    public int hashCode() {
        return Objects.hash(id, name, city);
    }

    @Override
    // Comparing the library to another library.
    public int compareTo(Library library) {
        try {
            if(library == null){
                throw new NullPointerException();
            }

            else if(id == library.id){
                return 0;
            }

            else if(id < library.id){
                return -1;
            }

            else{
                return 1;
            }
        } catch (NullPointerException e){
            System.out.println("Library.compareTo: library is null");
        }
        return -1;
    }

    /**
     * The function getName() returns the name of the object
     *
     * @return The name of the person.
     */
    public String getName(){
        return name;
    }

    /**
     * This function sets the name of the object to the value of the parameter.
     *
     * @param name The name of the parameter.
     */
    public void setName(String name)
    {
        this.name = name;
    }
    /**
     * This function returns the id of the object.
     *
     * @return The id variable is being returned.
     */
    public int getId(){return id;}


}
