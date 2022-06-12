package librarymanagementsystem.model;

// Creating an enum type called City.
public enum City {
    ISTANBUL, ANKARA, IZMIR, KOCAELI;

    @Override
    // Overriding the toString method.
    public String toString(){
        switch (this){
            case ISTANBUL:
                return "İstanbul";
            case ANKARA:
                return "Ankara";
            case IZMIR:
                return "İzmir";
            case KOCAELI:
                return "Kocaeli";
            default:
                return "";
        }
    }
}
