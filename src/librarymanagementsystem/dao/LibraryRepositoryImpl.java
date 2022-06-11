package librarymanagementsystem.dao;

import librarymanagementsystem.model.Library;
import utility.Graph;
import utility.Vertex;

import java.util.ArrayList;
import java.util.Objects;

public class LibraryRepositoryImpl implements LibraryRepository{
    private Graph libraries;

    public LibraryRepositoryImpl(){
        libraries = new Graph<>();
    }

    @Override
    public Graph findAll() {
        return libraries;
    }

    @Override
    public Library findById(int id) {
        ArrayList<Library> libraryList = libraries.getVertexList();
        for(int i=0;i<libraryList.size();i++)
            if(libraryList.get(i).getId()==id) return libraryList.get(i);
        return null;
    }

    @Override
    public Library findByName(String name)
    {
        ArrayList<Library> libraryList = libraries.getVertexList();
        for(int i=0;i<libraryList.size();i++)
            if(Objects.equals(libraryList.get(i).getName(), name)) return libraryList.get(i);
        return null;
    }

    @Override
    public void create(Library library) {
        libraries.addVertex(new Vertex(library));
    }

    @Override
    public Library update(Library library) {
        return null;
    }

    @Override
    public void remove(Library library) {
        libraries.removeVertex(new Vertex(library));

    }

    @Override
    public Library viewInfo(String name) {
        ArrayList<Library> libList = libraries.getVertexList();
        for(int i=0;i<libList.size();++i)
        {
            if(libList.get(i).getName().equals(name))
                return libList.get(i);
        }
        return null;
    }
}
