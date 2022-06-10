package librarymanagementsystem.model;

public enum Situation {
    STORAGE, LIBRARY, USER, NONE;

    @Override
    public String toString(){
        switch (this){
            case STORAGE:
                return "in storage";
            case LIBRARY:
                return "in library";
            case USER:
                return "in user";
            case NONE:
                return "is not available";
            default:
                return "";
        }
    }
}
