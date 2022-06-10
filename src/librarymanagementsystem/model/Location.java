package librarymanagementsystem.model;

public enum Location {
    A1, A2, A3, A4, A5, A6,
    B1, B2, B3, B4, B5, B6,
    C1, C2, C3, C4, C5, C6;


    @Override
    public String toString(){
        switch (this){
            case A1:
                return "in A1";
            case A2:
                return "in A2";
            case A3:
                return "in A3";
            case A4:
                return "in A4";
            case A5:
                return "in A5";
            case A6:
                return "in A6";
            case B1:
                return "in B1";
            case B2:
                return "in B2";
            case B3:
                return "in B3";
            case B4:
                return "in B4";
            case B5:
                return "in B5";
            case B6:
                return "in B6";
            case C1:
                return "in C1";
            case C2:
                return "in C2";
            case C3:
                return "in C3";
            case C4:
                return "in C4";
            case C5:
                return "in C5";
            case C6:
                return "in C6";
            default:
                return "";
        }
    }
}
