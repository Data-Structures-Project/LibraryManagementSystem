package librarymanagementsystem.dao;

import librarymanagementsystem.model.Library;

import java.util.ArrayList;
import java.util.List;

public class LibraryRepositoryImpl implements LibraryRepository{
    private List<Library> libraries;

    public LibraryRepositoryImpl(){
        libraries = new ArrayList<>();
    }

    @Override
    public List<Library> findAll() {
        return libraries;
    }

    @Override
    public Library findById(int id) {
        return null;
    }

    @Override
    public List<Library> findByName(String name) {
        return null;
    }

    @Override
    public void create(Library library) {
        libraries.add(library);
    }

    @Override
    public Library update(Library library) {
        return null;
    }

    @Override
    public void remove(Library library) {

    }

    @Override
    public String viewInfo(int id) {
        return null;
    }
}
