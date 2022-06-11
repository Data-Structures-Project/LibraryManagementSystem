package librarymanagementsystem.model;

import java.util.Objects;
import java.util.Random;

public class Library implements Comparable<Library>{
    private int id;
    private static int count;
    private String name;
    private City city;
    private double [] coordinates;
    //private List<Material> materials;

    public Library(String name, City city){
        this.id = count++;
        this.name = name;
        this.city = city;

        Random rand = new Random();
        coordinates = new double[2];
        coordinates[0] = rand.nextDouble(200);
        coordinates[1] = rand.nextDouble(200);
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Library)) return false;
        Library library = (Library) o;
        return Objects.equals(id, library.id) && Objects.equals(name, library.name) && city == library.city;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, name, city);
    }

    @Override
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

    public String getName(){
        return name;
    }

    public int getId(){return id;}
}
