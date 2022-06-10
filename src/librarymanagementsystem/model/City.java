package librarymanagementsystem.model;

public enum City {
    ISTANBUL, ANKARA, IZMIR, KOCAELI;

    @Override
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
