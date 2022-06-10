package librarymanagementsystem.model;

public enum MaterialType {
    BOOK, MAGAZINE;

    @Override
    public String toString(){
        switch (this){
            case BOOK:
                return "book";
            case MAGAZINE:
                return "magazine";
            default:
                return "";
        }
    }
}
