package librarymanagementsystem.model;

public enum Category {
    LITERARY_FICTION, MYSTERY, THRILLER, HORROR, HISTORY, SCIENCE_FICTION, BIOGRAPHIES, AUTOBIOGRAPHIES, POETRY;

    @Override
    public String toString(){
        switch (this){
            case LITERARY_FICTION:
                return "literary fiction";
            case MYSTERY:
                return "mystery";
            case THRILLER:
                return "thriller";
            case HORROR:
                return "horror";
            case HISTORY:
                return "history";
            case SCIENCE_FICTION:
                return "science fiction";
            case BIOGRAPHIES:
                return "biographies";
            case AUTOBIOGRAPHIES:
                return "autobiographies";
            case POETRY:
                return "poetry";
            default:
                return "";
        }
    }
}
