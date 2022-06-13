package librarymanagementsystem.dao;

import librarymanagementsystem.model.Library;
import utility.Graph;
import utility.Vertex;

import java.util.ArrayList;
import java.util.Objects;

/**
 * It implements the LibraryRepository interface and uses the Graph class to store the libraries
 */
public class LibraryRepositoryImpl implements LibraryRepository{
    // Creating a graph object to store the libraries.
    private Graph libraries;

    public LibraryRepositoryImpl(){
        libraries = new Graph<>();
    }

    @Override
    // Returning the list of all the libraries.
    public ArrayList<Library> findAll() {
        return libraries.getLibraryList();
    }

    @Override
    // Finding the library by its id.
    public Library findById(int id) {
        ArrayList<Library> libraryList = libraries.getLibraryList();
        for(int i=0;i<libraryList.size();i++)
            if(libraryList.get(i).getId()==id) return libraryList.get(i);
        return null;
    }

    @Override
    // Finding the library by its name.
    public Library findByName(String name)
    {
        ArrayList<Library> libraryList = libraries.getLibraryList();
        for(int i=0;i<libraryList.size();i++)
            if(Objects.equals(libraryList.get(i).getName(), name)) return libraryList.get(i);
        return null;
    }

    @Override
    // Adding a new library to the graph.
    public void create(Library library) {
        Vertex newVertex = new Vertex(library);
        libraries.addVertex(newVertex);

        ArrayList<Vertex> verList = this.libraries.getLibraryList();

        for (Vertex ver: verList)
            libraries.addEdge(newVertex, ver);
    }

    @Override
    // Not doing anything.
    public Library update(Library library) {
        return null;
    }

    @Override
    // Removing the library from the graph.
    public void remove(Library library) {
        libraries.removeVertex(new Vertex(library));

    }

    @Override
    // Returning the library object with the given name.
    public Library viewInfo(String name) {
        ArrayList<Library> libList = (ArrayList<Library>) libraries.getLibraryList();
        for(int i=0;i<libList.size();++i)
        {
            if(libList.get(i).getName().equals(name))
                return libList.get(i);
        }
        return null;
    }
}
