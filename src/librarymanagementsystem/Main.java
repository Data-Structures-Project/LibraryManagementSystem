package librarymanagementsystem;

public class Main {


    public static void main(String[] args) {
        public static final String ANSI_RED = "\u001B[31m";

        public static final String ANSI_GREEN = "\u001B[32m";
        public static final String ANSI_YELLOW = "\u001B[33m";

        public static final String ANSI_BLUE = "\u001B[34m";
        public static final String ANSI_PURPLE = "\u001B[35m";
        public static final String ANSI_CYAN = "\u001B[36m";

        public static void main (String[]args){
            Scanner sc = new Scanner(System.in);

            System.out.println(ANSI_BLUE + "====== Welcome to Library Management System ======");
            System.out.println(ANSI_CYAN + "1. Login");
            System.out.println(ANSI_CYAN + "2. Register");
            System.out.println(ANSI_CYAN + "0. Exit");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");


            String input = sc.next();

            switch (input) {
                case "1":
                    loginMenu();
                    break;
                case "2":
                    registerMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    break;

            }


        }


        static void loginMenu () {
            System.out.print(ANSI_GREEN + "Username :  ");
            Scanner sc = new Scanner(System.in);
            String userName = sc.next();
            System.out.print(ANSI_GREEN + "Password :  ");
            String passwd = sc.next();

            //switch login func
            //search user func returns user type, user name


        }

        static void registerMenu () {

        }

        static void administratorMenu (String usersName){
            System.out.println(ANSI_BLUE + "\n\nHello " + usersName);
            System.out.println(ANSI_CYAN + "1. Manage Libraries");
            System.out.println(ANSI_CYAN + "2. Manege Library Managers");
            System.out.println(ANSI_CYAN + "0. Exit");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");

            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch (input) {
                case "1":
                    manageLibraries('a');
                    break;
                case "2":
                    manageLibraryManagers();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    break;

            }

        }
        static void libraryManagerMenu (String usersName){
            System.out.println(ANSI_BLUE + "\n\nHello " + usersName);
            System.out.println(ANSI_CYAN + "1. Edit Libraries");
            System.out.println(ANSI_CYAN + "2. Manege Librarians");
            System.out.println(ANSI_CYAN + "0. Exit");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");

            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch (input) {
                case "1":
                    manageLibraries('l');
                    break;
                case "2":
                    manageLibrarians();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    break;

            }
        }
        static void librarianMenu (String usersName){
            System.out.println(ANSI_BLUE + "\n\nHello " + usersName);
            System.out.println(ANSI_CYAN + "1. Manage Readers");
            System.out.println(ANSI_CYAN + "2. Manege loan books");
            System.out.println(ANSI_CYAN + "3. Manege books");
            System.out.println(ANSI_CYAN + "4. Manege magazines");
            System.out.println(ANSI_CYAN + "5. Search books");
            System.out.println(ANSI_CYAN + "0. Exit");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");

            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch (input) {
                case "1":
                    manageReaders();
                    break;
                case "2":
                    manageLoanBooks();
                    break;
                case "3":
                    manageBooks();
                    break;
                case "4":
                    manageMagazines();
                    break;
                case "5":
                    searchBooks();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    break;

            }
        }
        static void readerMenu () {
            System.out.println(ANSI_BLUE + "\n\nHello " + usersName);
            System.out.println(ANSI_CYAN + "1. Search books");
            System.out.println(ANSI_CYAN + "2. Rate a book");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");

            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch (input) {
                case "1":
                    searchBooks();
                    break;
                case "2":
                    rateBook();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    break;

            }


            static void manageLibraries ( char userType){

            }
            static void manageLibraryManagers () {

            }
            static void manageLibrarians () {

            }
            static void manageReaders () {

            }
            static void manageLoanBooks () {

            }
            static void manageBooks () {

            }
            static void manageMagazines () {

            }
            static void searchBooks () {

            }
            static void rateBooks () {

            }
        }
    }
}
