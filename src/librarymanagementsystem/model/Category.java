package librarymanagementsystem.model;

// Creating a new type called Category.
public enum Category {
    LITERARY_FICTION, MYSTERY, THRILLER, HORROR, HISTORY, SCIENCE_FICTION, BIOGRAPHIES, AUTOBIOGRAPHIES, POETRY, CLASSICS;


    @Override
    // A method that returns a string representation of the object.
    public String toString(){
        switch (this){
            case LITERARY_FICTION:
                return "Literary Fiction";
            case MYSTERY:
                return "Mystery";
            case THRILLER:
                return "Thriller";
            case HORROR:
                return "Horror";
            case HISTORY:
                return "History";
            case SCIENCE_FICTION:
                return "Science fiction";
            case BIOGRAPHIES:
                return "Biographies";
            case AUTOBIOGRAPHIES:
                return "Autobiographies";
            case POETRY:
                return "Poetry";
            case CLASSICS:
                return "Classics";
            default:
                return "";
        }
    }
}
