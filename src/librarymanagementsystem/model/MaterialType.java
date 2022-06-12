package librarymanagementsystem.model;

// Creating a new type of variable called MaterialType.
public enum MaterialType {
    BOOK, MAGAZINE;

    /**
     * The function returns a string representation of the enum type
     *
     * @return The string value of the enum.
     */
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
