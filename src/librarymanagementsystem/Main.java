package librarymanagementsystem;

import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.Author;
import librarymanagementsystem.model.Librarian;
import librarymanagementsystem.model.Library;
import librarymanagementsystem.model.LibraryManager;
import librarymanagementsystem.model.Material;
import librarymanagementsystem.model.MaterialType;
import librarymanagementsystem.model.Publisher;
import librarymanagementsystem.model.User;
import librarymanagementsystem.service.MainService;

import java.sql.Date;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RED = "\u001B[31m";
    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";
    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    /**
     * The main function is the entry point of the program. It calls the mockData function to populate the database with
     * some data, and then calls the mainMenu function to display the main menu
     */
    public static void main(String[] args) {
        MainService.mockData();
        mainMenu();

    }

    /**
     * This function is the main menu of the program, it will display the main menu and ask the user to choose one of the
     * options
     */
    static void mainMenu(){
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "==================================================");
        System.out.println(ANSI_BLUE + "|      Welcome to Library Management System      |");
        System.out.println(ANSI_BLUE + "==================================================");
        System.out.println(ANSI_CYAN + "|         1. Login                               |");
        System.out.println(ANSI_CYAN + "|         2. Register                            |");
        System.out.println(ANSI_CYAN + "|         0. Exit                                |");
        System.out.println(ANSI_BLUE + "==================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        while (true) {
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
                    System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                    break;

            }
        }
    }

    /**
     * This function is used to login to the system
     */
    static void loginMenu() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                     Login                     |");
        System.out.println(ANSI_BLUE + "=================================================");

        System.out.print(ANSI_GREEN + "         Username :  ");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        System.out.print(ANSI_GREEN + "         Password :  ");
        String password = sc.next();

        Account newLogin = MainService.login(username, password);
        if (newLogin == null) {
            System.out.println(ANSI_RED + "Username or possword is wrong!");
            loginMenu();
        } else {
            helloBanner(newLogin.getName());
            switch (newLogin.getClass().getName()) {
                case "librarymanagementsystem.model.Administrator":
                    administratorMenu();
                    break;
                case "librarymanagementsystem.model.LibraryManager":
                    libraryManagerMenu();
                    break;
                case "librarymanagementsystem.model.Librarian":
                    librarianMenu();
                    break;
                case "librarymanagementsystem.model.User":
                    readerMenu();
                    break;
            }
        }
    }

    /**
     * It registers a new user.
     */
    static void registerMenu() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Register                   |");
        System.out.println(ANSI_BLUE + "=================================================");

        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "|   Libraries to register                       |");
        for (int i = 0; i < MainService.listLibraries().size(); i++) {
            System.out.println(
                    ANSI_CYAN + "|\t\t" + i + ". " + MainService.listLibraries().get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > MainService.listLibraries().size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }

        System.out.print(ANSI_GREEN + "         Name :  ");
        String name = sc.next();
        System.out.print(ANSI_GREEN + "         Surname :  ");
        String surname = sc.next();
        System.out.print(ANSI_GREEN + "         Username :  ");
        String username = sc.next();
        while (!MainService.isUniqueUserName(username)) {
            System.out.println(ANSI_RED + "   This username already taken! Please try another one.");
            System.out.print(ANSI_GREEN + "         Username :  ");
            username = sc.next();
        }
        System.out.print(ANSI_GREEN + "         Password :  ");
        String password = sc.next();

        MainService.register(name, surname, username, password, libraryid);

        loginMenu();

    }

    /**
     * This function prints a banner to the console with the user's name in the middle
     *
     * @param usersName The name of the user.
     */
    static void helloBanner(String usersName){
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.printf(ANSI_BLUE + "|\t\t\t\tHello %-26s|\n", usersName);
    }

    /**
     * This function is used to display the menu for the administrator
     */
    static void administratorMenu() {
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Manage Libraries                   |");
        System.out.println(ANSI_CYAN + "|         2. Manage Library Managers            |");
        System.out.println(ANSI_CYAN + "|         3. Logout                             |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
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
            case "3":
                mainMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }

    }

    /**
     * This function is used to display the menu for the library manager
     */
    static void libraryManagerMenu() {
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Edit Libraries                     |");
        System.out.println(ANSI_CYAN + "|         2. Manege Librarians                  |");
        System.out.println(ANSI_CYAN + "|         3. Logout                             |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                manageLibraries('l');
                break;
            case "2":
                manageLibrarians();
                break;
            case "3":
                mainMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

    /**
     * It's a menu for the librarian
     */
        }
    }

    /**
     * This function is used to display the menu for the librarian
     */
    static void librarianMenu() {
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Manage Readers                     |");
        System.out.println(ANSI_CYAN + "|         2. Manege loan books                  |");
        System.out.println(ANSI_CYAN + "|         3. Manege books                       |");
        System.out.println(ANSI_CYAN + "|         4. Manege magazines                   |");
        System.out.println(ANSI_CYAN + "|         5. Search materials                   |");
        System.out.println(ANSI_CYAN + "|         6. Logout                             |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
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
                searchMaterials('l');
                break;
            case "6":
                mainMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }
    }

    /**
     * This function is used to display the menu for the reader
     */
    static void readerMenu() {
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Search materials                   |");
        System.out.println(ANSI_CYAN + "|         2. Rate a materials                   |");
        System.out.println(ANSI_CYAN + "|         3. Logout                             |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                searchMaterials('r');
                break;
            case "2":
                rateMaterials();
                break;
            case "3":
                mainMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }

    }

    /**
     * Menu that allows the user to add, remove, or edit a library
     *
     * @param userType This is the type of user that is currently logged in.
     */
    static void manageLibraries(char userType) {

        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                 Manage Libraries              |");
        System.out.println(ANSI_BLUE + "=================================================");
        if (userType == 'a') {
            System.out.println(ANSI_CYAN + "|         1. Add library                        |");
            System.out.println(ANSI_CYAN + "|         2. Remove library                     |");
            System.out.println(ANSI_CYAN + "|         3. Edit library                       |");
            System.out.println(ANSI_CYAN + "|         4. Back                               |");
            System.out.println(ANSI_CYAN + "|         0. Exit                               |");
            System.out.println(ANSI_BLUE + "=================================================");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch (input) {
                case "1":
                    addLibrary();
                    break;
                case "2":
                    removeLibrary();
                    break;
                case "3":
                    editLibrary();
                    break;
                case "4":
                    System.out.println("\n\n\n");
                    administratorMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                    break;

            }
        }

        else {

            System.out.println(ANSI_CYAN + "|         1. Edit library                       |");
            System.out.println(ANSI_CYAN + "|         2. Back                               |");
            System.out.println(ANSI_CYAN + "|         0. Exit                               |");
            System.out.println(ANSI_BLUE + "=================================================");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch (input) {
                case "1":
                    editLibrary();
                    break;
                case "2":
                    System.out.println("\n\n\n");
                    libraryManagerMenu();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
                    System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                    break;

            }
        }

    }

    /**
     *  Menu that allows the user to add, remove, or edit a library manager
     */
    static void manageLibraryManagers() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|             Manage Library Managers           |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add library manager                |");
        System.out.println(ANSI_CYAN + "|         2. Remmove library manager            |");
        System.out.println(ANSI_CYAN + "|         3. Edit library manager               |");
        System.out.println(ANSI_CYAN + "|         4. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");
        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                addManager();
                break;
            case "2":
                removeManager();
                break;
            case "3":
                editManager();
                break;
            case "4":
                administratorMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }
    }

    /**
     * Menu that allows the user to add, remove, or edit a librarian
     */
    static void manageLibrarians() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|               Manage Librarians               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add librarians                     |");
        System.out.println(ANSI_CYAN + "|         2. Remmove librarians                 |");
        System.out.println(ANSI_CYAN + "|         3. Edit librarians                    |");
        System.out.println(ANSI_CYAN + "|         4. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                addLibrarian();
                break;
            case "2":
                removeLibrarian();
                break;
            case "3":
                editLibrarian();
                break;
            case "4":
                libraryManagerMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }
    }

    /**
     * Menu that allows the user to add, remove, or edit a reader
     */
    static void manageReaders() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                 Manage Readers                |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add readers                        |");
        System.out.println(ANSI_CYAN + "|         2. Remmove readers                    |");
        System.out.println(ANSI_CYAN + "|         3. Edit readers                       |");
        System.out.println(ANSI_CYAN + "|         4. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                addReader();
                break;
            case "2":
                removeReader();
                break;
            case "3":
                editReader();
                break;
            case "4":
                librarianMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }
    }

    /**
     * Menu that allows the user to add or remove loan book
     */
    static void manageLoanBooks() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                Manage Loan Books              |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add loan book                      |");
        System.out.println(ANSI_CYAN + "|         2. Remove loan book                   |");
        System.out.println(ANSI_CYAN + "|         3. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                addLoanBook();
                break;
            case "2":
                removeLoanBook();
                break;
            case "3":
                librarianMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }
    }

    /**
     * Menu that allows the user to add, remove, or edit a book
     */
    static void manageBooks() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                  Manage Books                 |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add book                           |");
        System.out.println(ANSI_CYAN + "|         2. Remmove book                       |");
        System.out.println(ANSI_CYAN + "|         3. Edit book                          |");
        System.out.println(ANSI_CYAN + "|         4. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                addBook();
                break;
            case "2":
                removeBook();
                break;
            case "3":
                editBook();
                break;
            case "4":
                librarianMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }
    }

    /**
     * Menu that allows the user to add, remove, or edit a magazine
     */
    static void manageMagazines() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                Manage Magazines               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add magazine                       |");
        System.out.println(ANSI_CYAN + "|         2. Remmove magazine                   |");
        System.out.println(ANSI_CYAN + "|         3. Edit magazine                      |");
        System.out.println(ANSI_CYAN + "|         4. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                addMagazine();
                break;
            case "2":
                removeMagazine();
                break;
            case "3":
                editMagazine();
                break;
            case "4":
                librarianMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!" + ANSI_GREEN + "\nPlease choose again : ");
                break;

        }
    }

    /**
     * It's a function that prints a menu for searching materials and then calls the appropriate function based on the
     * user's input
     *
     * @param userType  This is the type of user that is currently logged in.
     */
    static void searchMaterials(char userType) {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                  Search Materials             |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. List all Books and Magazines       |");
        System.out.println(ANSI_CYAN + "|         2. Search by Name                     |");
        System.out.println(ANSI_CYAN + "|         3. Search by Author                   |");
        System.out.println(ANSI_CYAN + "|         4. Search by Publisher                |");
        System.out.println(ANSI_CYAN + "|         5. Search by Category                 |");
        System.out.println(ANSI_CYAN + "|         6. Search by Rate                     |");
        System.out.println(ANSI_CYAN + "|         7. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                listAll(userType);
                break;
            case "2":
                searchByName(userType);
                break;
            case "3":
                searchByAuthor(userType);
                break;
            case "4":
                searchByPublisher(userType);
                break;
            case "5":
                searchByCategory(userType);
                break;
            case "6":
                searchByRate(userType);
                break;
            case "7":
                if (userType == 'l'){
                    librarianMenu();
                }
                else
                    readerMenu();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                System.out.print(ANSI_RED + "Invalid input!");
                searchMaterials(userType);
                break;

        }
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

    /**
     * The function takes the name of the book from the user and searches for it in the library. If the book is found, it
     * prints the book's information and asks the user to rate it. Then, it adds the rate to the book and prints the new
     * average rate
     */
    static void rateMaterials() {
        System.out.print(ANSI_CYAN + "   Enter the name of the book want to Rate : ");
        Scanner sc = new Scanner(System.in);
        String materialName = sc.nextLine();
        Material newMat = MainService.searchByName(materialName);
        if (newMat == null) {
            System.out.println(ANSI_RED + "   The book your searched couldn't find :(");
            searchByName('r');
            return;
        } else {
            printBook(newMat);
        }

        System.out.print(ANSI_CYAN + "|   Enter the Rate 0-5 : ");
        int materialRate = Integer.parseInt(sc.next());
        MainService.addRate(newMat, materialRate);
        System.out.printf(ANSI_BLUE + "|   New average rate => %-24.2f|\n", newMat.getRateAve());
        System.out.println(ANSI_BLUE + "=================================================\n\n\n");

        readerMenu();
    }

    /**
     * Menu that adds a library to the database
     */
    static void addLibrary() {
        Scanner sc = new Scanner(System.in);

        System.out.print(ANSI_CYAN + "   Enter the name of the Library : ");
        String libraryName = sc.next();

        System.out.print(ANSI_CYAN + "   Enter the code of the City : ");
        int libraryCity = Integer.parseInt(sc.next());

        MainService.addLibrary(libraryName, libraryCity);
        System.out.print(ANSI_GREEN + "   Success! \n");
        administratorMenu();
    }

    /**
     * Menu that is used to remove a library from the database
     */
    static void removeLibrary() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to remove                  |");
        ArrayList<Library> libList = MainService.listLibraries();
        if(libList.size()==0){
            System.out.println(ANSI_RED + "|   There is no Library to remove                |");
            System.out.println(ANSI_BLUE + "=================================================");
            administratorMenu();
            return;
        }
        for (int i = 0; i < libList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, libList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > libList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }
        MainService.removeLibrary(libList.get(libraryid));


        System.out.print(ANSI_GREEN + "   Success! \n");
        administratorMenu();
    }

    /**
     * Menu that is used to edit the name of a library
     */
    static void editLibrary() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to edit name               |");
        ArrayList<Library> libList = MainService.listLibraries();
        if(libList.size()==0){
            System.out.println(ANSI_RED + "|   There is no Library to remove                |");
            System.out.println(ANSI_BLUE + "=================================================");
            administratorMenu();
            return;
        }
        for (int i = 0; i < libList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, libList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > libList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }
        System.out.print(ANSI_CYAN + "   Enter new name : ");
        String newName = sc.next();
        MainService.editLibraryName(libList.get(libraryid), newName);
        System.out.print(ANSI_GREEN + "   Success! \n");
        administratorMenu();

    }

    /**
     * Menu that adds a new Library Manager to the system
     */
    static void addManager() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Library> libraryList = MainService.listLibraries();
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to add manager             |");
        ArrayList<Library> libList = MainService.listLibraries();
        if(libList.size()==0){
            System.out.println(ANSI_RED + "|   There is no Library to remove                |");
            System.out.println(ANSI_BLUE + "=================================================");
            administratorMenu();
            return;
        }
        for (int i = 0; i < libList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, libList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > libList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }


        System.out.print(ANSI_GREEN + "         Name :  ");
        String name = sc.next();
        System.out.print(ANSI_GREEN + "         Surname :  ");
        String surname = sc.next();
        System.out.print(ANSI_GREEN + "         Username :  ");
        String username = sc.next();
        while (!MainService.isUniqueUserName(username)) {
            System.out.println(ANSI_RED + "   This username already taken! Please try another one.");
            System.out.print(ANSI_GREEN + "         Username :  ");
            username = sc.next();
        }
        System.out.print(ANSI_GREEN + "         Password :  ");
        String password = sc.next();


        MainService.addAccount(new LibraryManager(name, surname, username, password, libraryList.get(1)));
        System.out.print(ANSI_GREEN + "   Success! \n");
        administratorMenu();
    }

    /**
     * Menu that is used to remove a Library Manager from the system
     */
    static void removeManager() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library Manager to remove          |");
        ArrayList<LibraryManager> lMList = MainService.listLibraryManagers();
        if(lMList.size()==0){
            System.out.println(ANSI_RED + "|   There is no Library Manager to remove                |");
            System.out.println(ANSI_BLUE + "=================================================");
            administratorMenu();
            return;
        }
        for (int i = 0; i < lMList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, lMList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int lMid = Integer.parseInt(sc.next());
        while (lMid < 0 || lMid > lMList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            lMid = Integer.parseInt(sc.next());
        }
        MainService.removeAccount(lMList.get(lMid));


        System.out.print(ANSI_GREEN + "   Success! \n");
        administratorMenu();
    }

    /**
     * Menu that allows the administrator to change the password of a library manager
     */
    static void editManager() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|  Choose a Library Manager to change password |");
        ArrayList<LibraryManager> lMList = MainService.listLibraryManagers();
        if(lMList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Library Manager to change password|");
            System.out.println(ANSI_BLUE + "=================================================");
            administratorMenu();
            return;
        }
        for (int i = 0; i < lMList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, lMList.get(i).getUsername());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int lMid = Integer.parseInt(sc.next());
        while (lMid < 0 || lMid > lMList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            lMid = Integer.parseInt(sc.next());
        }

        System.out.print(ANSI_CYAN + "   Enter new password : ");
        String newPass = sc.next();
        MainService.editAccount(newPass, lMList.get(lMid).getUsername());
        System.out.print(ANSI_GREEN + "   Success! \n");
        administratorMenu();


    }

    /**
     * Menu that is used to add a new librarian to the system
     */
    static void addLibrarian() {
        Scanner sc = new Scanner(System.in);

        ArrayList<Library> libraryList = MainService.listLibraries();
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to add librarian           |");
        ArrayList<Library> libList = MainService.listLibraries();
        if(libList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Library to add Librarian          |");
            System.out.println(ANSI_BLUE + "=================================================");
            libraryManagerMenu();
            return;
        }
        for (int i = 0; i < libList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, libList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > libList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }


        System.out.print(ANSI_GREEN + "         Name :  ");
        String name = sc.next();
        System.out.print(ANSI_GREEN + "         Surname :  ");
        String surname = sc.next();
        System.out.print(ANSI_GREEN + "         Username :  ");
        String username = sc.next();
        while (!MainService.isUniqueUserName(username)) {
            System.out.println(ANSI_RED + "   This username already taken! Please try another one.");
            System.out.print(ANSI_GREEN + "         Username :  ");
            username = sc.next();
        }
        System.out.print(ANSI_GREEN + "         Password :  ");
        String password = sc.next();


        MainService.addAccount(new Librarian(name, surname, username, password, libraryList.get(1)));
        System.out.print(ANSI_GREEN + "   Success! \n");
        libraryManagerMenu();
    }

    /**
     * Menu that removes a librarian from the database
     */
    static void removeLibrarian() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Librarian to remove                |");
        ArrayList<Librarian> lList = MainService.listLibrarians();
        if(lList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Librarian to remove              |");
            System.out.println(ANSI_BLUE + "=================================================");
            libraryManagerMenu();
            return;
        }
        for (int i = 0; i < lList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, lList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int lid = Integer.parseInt(sc.next());
        while (lid < 0 || lid > lList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            lid = Integer.parseInt(sc.next());
        }
        MainService.removeAccount(lList.get(lid));


        System.out.print(ANSI_GREEN + "   Success! \n");
        libraryManagerMenu();
    }

    /**
     * Menu that allows the library manager to change the password of a librarian
     */
    static void editLibrarian() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Librarian to change password       |");
        ArrayList<Librarian> lList = MainService.listLibrarians();
        if(lList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Librarian to change password     |");
            System.out.println(ANSI_BLUE + "=================================================");
            libraryManagerMenu();
            return;
        }
        for (int i = 0; i < lList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, lList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int lid = Integer.parseInt(sc.next());
        while (lid < 0 || lid > lList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            lid = Integer.parseInt(sc.next());
        }

        System.out.print(ANSI_CYAN + "   Enter new password : ");
        String newPass = sc.next();
        MainService.editAccount(newPass, lList.get(lid).getUsername());
        System.out.print(ANSI_GREEN + "   Success! \n");
        libraryManagerMenu();
    }

    /**
     * Menu that is used to add a new reader to the library
     */
    static void addReader() {
        Scanner sc = new Scanner(System.in);

        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to add reader              |");
        ArrayList<Library> libList = MainService.listLibraries();
        if(libList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Library to add Reader            |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < libList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, libList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > libList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }


        System.out.print(ANSI_GREEN + "         Name :  ");
        String name = sc.next();
        System.out.print(ANSI_GREEN + "         Surname :  ");
        String surname = sc.next();
        System.out.print(ANSI_GREEN + "         Username :  ");
        String username = sc.next();
        while (!MainService.isUniqueUserName(username)) {
            System.out.println(ANSI_RED + "   This username already taken! Please try another one.");
            System.out.print(ANSI_GREEN + "         Username :  ");
            username = sc.next();
        }
        System.out.print(ANSI_GREEN + "         Password :  ");
        String password = sc.next();


        MainService.addAccount(new User(name, surname, username, password, libList.get(1)));
        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that is used to remove a reader from the system
     */
    static void removeReader() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Reader to remove                   |");
        ArrayList<User> rList = MainService.listReaders();
        if(rList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Reader to remove                 |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < rList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, rList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int rid = Integer.parseInt(sc.next());
        while (rid < 0 || rid > rList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            rid = Integer.parseInt(sc.next());
        }
        MainService.removeAccount(rList.get(rid));


        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that is used to change the password of a reader
     */
    static void editReader() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Reader to change password          |");
        ArrayList<User> rList = MainService.listReaders();
        if(rList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Librarian to change password     |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < rList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, rList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int rid = Integer.parseInt(sc.next());
        while (rid < 0 || rid > rList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            rid = Integer.parseInt(sc.next());
        }

        System.out.print(ANSI_CYAN + "   Enter new password : ");
        String newPass = sc.next();
        MainService.editAccount(newPass, rList.get(rid).getUsername());
        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that is used to change the status of the book to "loan" in the database
     */
    static void addLoanBook() {

        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_CYAN + "   Name of the book you want to change status : ");
        String loanName = sc.nextLine();

        if (MainService.addLoanBook(loanName)){
            System.out.print(ANSI_GREEN + "   Success! \n");
            librarianMenu();
            return;
        }
        else
            System.out.println(ANSI_RED + "|  There is no book to change status");
        librarianMenu();
    }

    /**
     * Menu that is used to change the status of a book from "loaned" to "available"
     */
    static void removeLoanBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_CYAN + "   Name of the book you want to change status : ");
        String loanName = sc.nextLine();

        if (MainService.removeLoanBook(loanName)){
            System.out.print(ANSI_GREEN + "   Success! \n");
            librarianMenu();
            return;
        }
        else
            System.out.println(ANSI_RED + "|  There is no book to change status");
        librarianMenu();
    }

    /**
     * Menu that takes the input from the user and adds a book to the library
     */
    static void addBook() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to add the book            |");
        ArrayList<Library> libList = MainService.listLibraries();
        if(libList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Library to add Book              |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < libList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, libList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > libList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }




        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Category                           |");
        ArrayList<String> catList = MainService.listCategories();
        if(catList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Category to add Book             |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < catList.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + catList.get(i) + "\t\t\t\t\t\t");
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int catid = Integer.parseInt(sc.next());
        while (catid < 0 || catid > catList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            catid = Integer.parseInt(sc.next());
        }

        Author bookAuth;
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose an Author                            |");
        ArrayList<Author> authList = MainService.listAuthors();
        if(authList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Author to add Book               |");
        }
        else {
            for (int i = 0; i < authList.size(); i++) {
                System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + authList.get(i) + "\t\t\t\t\t\t");
            }
        }
        System.out.println(ANSI_BLUE + "| to add new author press 'A'                   |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        String authinput = sc.next();
        int authid = Integer.parseInt(authinput);
        while (authid < 0 || authid > authList.size() - 1|| authinput != "A") {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            authinput = sc.next();
            authid = Integer.parseInt(authinput);
        }
        if (authinput == "A"){
            System.out.print(ANSI_CYAN + "| Author's Name : ");
            String authorName = sc.next();
            System.out.print(ANSI_CYAN + "| Author's Surname : ");
            String authorSurname = sc.next();
            System.out.println(ANSI_CYAN + "| Author's Info : ");
            String authorInfo = sc.next();
            bookAuth = MainService.addAuthor(authorName, authorSurname, authorInfo);
        }
        else {
            bookAuth = authList.get(authid);
        }

        Publisher bookPub;
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose an Publisher                         |");
        ArrayList<Publisher> pubList = MainService.listPublishers();
        if(pubList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Author to add Book               |");
        }
        else {
            for (int i = 0; i < authList.size(); i++) {
                System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + pubList.get(i) + "\t\t\t\t\t\t");
            }
        }
        System.out.println(ANSI_BLUE + "| to add new publisher press 'A'                |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        String pubinput = sc.next();
        int pubid = Integer.parseInt(pubinput);
        while (pubid < 0 || pubid > pubList.size() - 1|| pubinput != "A") {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            pubinput = sc.next();
            pubid = Integer.parseInt(pubinput);
        }
        if (pubinput == "A"){
            System.out.print(ANSI_CYAN + "| Publisher's Name : ");
            String pubName = sc.next();
            System.out.println(ANSI_CYAN + "| Publisher's Info : ");
            String pubInfo = sc.next();
            bookPub = MainService.addPublisher(pubName, pubInfo);
        }
        else {
            bookPub = pubList.get(pubid);
        }

        System.out.println(ANSI_CYAN + "| Book's name : ");
        String bookName = sc.next();

        System.out.println(ANSI_CYAN + "| Book's page count : ");
        int bookPage = Integer.parseInt(sc.next());

        System.out.println(ANSI_CYAN + "| Book's publishing date : ");
        Date bookDate = new Date(Long.parseLong(sc.next()));

        System.out.println(ANSI_CYAN + "| Book's location in library : ");
        String bookLoc = sc.next();

        System.out.println(ANSI_CYAN + "| Book's info : ");
        String bookInfo = sc.next();


        MainService.addMaterial(MaterialType.BOOK, bookName, catid, bookDate, bookAuth, bookPub, bookPage, bookLoc, bookInfo, libList.get(libraryid));
        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that is used to remove a book from the library
     */
    static void removeBook() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Book to remove                     |");
        ArrayList<Material> mList = MainService.listMaterials();
        if(mList.size()==0){
            System.out.println(ANSI_RED + "|  There is no book to remove                   |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, mList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int mid = Integer.parseInt(sc.next());
        while (mid < 0 || mid > mList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            mid = Integer.parseInt(sc.next());
        }
        MainService.removeMaterial(mList.get(mid).getName());

        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();


    }

    /**
     * Menu that is used to edit the information of a book
     */
    static void editBook() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Book to change info                |");
        ArrayList<Material> mList = MainService.listMaterials();
        if(mList.size()==0){
            System.out.println(ANSI_RED + "|  There is no book to change info              |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, mList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int mid = Integer.parseInt(sc.next());
        while (mid < 0 || mid > mList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            mid = Integer.parseInt(sc.next());
        }

        System.out.print(ANSI_CYAN + "   Enter new info : ");
        String newinfo = sc.next();
        MainService.editMaterialInfo(mList.get(mid),newinfo);
        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that takes the user's input and adds a new magazine to the database
     */
    static void addMagazine() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to add the Magazine        |");
        ArrayList<Library> libList = MainService.listLibraries();
        if(libList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Library to add Magazine          |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < libList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, libList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 0 || libraryid > libList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }




        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Category                           |");
        ArrayList<String> catList = MainService.listCategories();
        if(catList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Category to add Magazine         |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < catList.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + catList.get(i) + "\t\t\t\t\t\t");
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int catid = Integer.parseInt(sc.next());
        while (catid < 0 || catid > catList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            catid = Integer.parseInt(sc.next());
        }

        Author bookAuth;
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose an Author                            |");
        ArrayList<Author> authList = MainService.listAuthors();
        if(authList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Author to add Magazine           |");
        }
        else {
            for (int i = 0; i < authList.size(); i++) {
                System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + authList.get(i) + "\t\t\t\t\t\t");
            }
        }
        System.out.println(ANSI_BLUE + "| to add new author press 'A'                   |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        String authinput = sc.next();
        int authid = Integer.parseInt(authinput);
        while (authid < 0 || authid > authList.size() - 1|| authinput != "A") {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            authinput = sc.next();
            authid = Integer.parseInt(authinput);
        }
        if (authinput == "A"){
            System.out.print(ANSI_CYAN + "| Author's Name : ");
            String authorName = sc.next();
            System.out.print(ANSI_CYAN + "| Author's Surname : ");
            String authorSurname = sc.next();
            System.out.println(ANSI_CYAN + "| Author's Info : ");
            String authorInfo = sc.next();
            bookAuth = MainService.addAuthor(authorName, authorSurname, authorInfo);
        }
        else {
            bookAuth = authList.get(authid);
        }

        Publisher bookPub;
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose an Publisher                         |");
        ArrayList<Publisher> pubList = MainService.listPublishers();
        if(pubList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Author to add Magazine           |");
        }
        else {
            for (int i = 0; i < authList.size(); i++) {
                System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + pubList.get(i) + "\t\t\t\t\t\t");
            }
        }
        System.out.println(ANSI_BLUE + "| to add new publisher press 'A'                |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        String pubinput = sc.next();
        int pubid = Integer.parseInt(pubinput);
        while (pubid < 0 || pubid > pubList.size() - 1|| pubinput != "A") {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            pubinput = sc.next();
            pubid = Integer.parseInt(pubinput);
        }
        if (pubinput == "A"){
            System.out.print(ANSI_CYAN + "| Publisher's Name : ");
            String pubName = sc.next();
            System.out.println(ANSI_CYAN + "| Publisher's Info : ");
            String pubInfo = sc.next();
            bookPub = MainService.addPublisher(pubName, pubInfo);
        }
        else {
            bookPub = pubList.get(pubid);
        }

        System.out.println(ANSI_CYAN + "| Book's name : ");
        String bookName = sc.next();

        System.out.println(ANSI_CYAN + "| Book's page count : ");
        int bookPage = Integer.parseInt(sc.next());

        System.out.println(ANSI_CYAN + "| Book's publishing date : ");
        Date bookDate = new Date(Long.parseLong(sc.next()));

        System.out.println(ANSI_CYAN + "| Book's location in library : ");
        String bookLoc = sc.next();

        System.out.println(ANSI_CYAN + "| Book's info : ");
        String bookInfo = sc.next();


        MainService.addMaterial(MaterialType.MAGAZINE, bookName, catid, bookDate, bookAuth, bookPub, bookPage, bookLoc, bookInfo, libList.get(libraryid));
        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that is used to remove a magazine from the library
     */
    static void removeMagazine() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Magazine to remove                 |");
        ArrayList<Material> mList = MainService.listMaterials();
        if(mList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Magazine to remove               |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, mList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int mid = Integer.parseInt(sc.next());
        while (mid < 0 || mid > mList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            mid = Integer.parseInt(sc.next());
        }
        MainService.removeMaterial(mList.get(mid).getName());

        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that is used to edit the information of a magazine
     */
    static void editMagazine() {
        Scanner sc = new Scanner(System.in);


        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Magazine to edit info              |");
        ArrayList<Material> mList = MainService.listMaterials();
        if(mList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Magazine to edit info            |");
            System.out.println(ANSI_BLUE + "=================================================");
            librarianMenu();
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, mList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int mid = Integer.parseInt(sc.next());
        while (mid < 0 || mid > mList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            mid = Integer.parseInt(sc.next());
        }

        System.out.print(ANSI_CYAN + "   Enter new info : ");
        String newinfo = sc.next();
        MainService.editMaterialInfo(mList.get(mid),newinfo);
        System.out.print(ANSI_GREEN + "   Success! \n");
        librarianMenu();
    }

    /**
     * Menu that prints all the materials in the library
     *
     * @param userType This is the type of user that is currently logged in.
     */
    static void listAll(char userType) {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   All Materials                               |");
        ArrayList<Material> mList = MainService.listMaterials();
        if(mList.size()==0){
            System.out.println(ANSI_RED + "|  There is no Material                         |");
            System.out.println(ANSI_BLUE + "=================================================");
            if (userType == 'l'){
                librarianMenu();
            }
            else
                readerMenu();
            return;
        }
        for (int i = 0; i < mList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, mList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int mid = Integer.parseInt(sc.next());
        while (mid < 0 || mid > mList.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            mid = Integer.parseInt(sc.next());
        }



        printBook(mList.get(mid));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();

    }

    /**
     * Menu that takes a character as an argument and prints the book with the name that the user entered
     *
     * @param userType This is the type of user that is currently logged in.
     */
    static void searchByName(char userType) {
        System.out.print(ANSI_CYAN + "   Enter the name of the book : ");
        Scanner sc = new Scanner(System.in);
        String materialName = sc.next();
        Material newMat = MainService.searchByName(materialName);
        if (newMat == null) {
            System.out.println(ANSI_RED + "   The book your searched couldn't find :(");
            searchMaterials(userType);
            return;
        } else {
            printBook(newMat);
        }
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

    /**
     * Menu that prints a list of authors, then asks the user to choose one of them, then prints a list of books written by that
     * author, then asks the user to choose one of them, then prints the details of that book
     *
     * @param userType This is the type of user that is currently logged in.
     */
    static void searchByAuthor(char userType) {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Authors                    |");
        System.out.println(ANSI_BLUE + "=================================================");

        Scanner sc = new Scanner(System.in);
        ArrayList<Author> authorList = MainService.listAuthors();
        for (int i = 0; i < authorList.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, authorList.get(i).getName());
        }
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int authorIndex = Integer.parseInt(sc.next());
        List<Material> authorBooks = MainService.searchByAuthor(authorList.get(authorIndex).getName());
        if (authorBooks == null){
            System.out.println(ANSI_RED + "|  There is no materıal for author              |");
            searchMaterials(userType);
            return;
        }
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Books                      |");
        System.out.println(ANSI_BLUE + "=================================================");
        for (int i = 0; i < authorBooks.size(); i++) {
            System.out.printf(ANSI_CYAN + "|\t\t%d. %-37s|\n",i, authorBooks.get(i).getName());
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int bookIndex = Integer.parseInt(sc.next());
        while (bookIndex < 0 || bookIndex > authorBooks.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            bookIndex = Integer.parseInt(sc.next());
        }
        printBook(authorBooks.get(bookIndex));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

    /**
     * Menu that prints a list of publishers, then asks the user to choose one of them, then prints a list of books published by
     * that publisher, then asks the user to choose one of them, then prints the details of that book
     *
     * @param userType This is the type of user that is currently logged in.
     */
    static void searchByPublisher(char userType) {

        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Publisher                    |");
        System.out.println(ANSI_BLUE + "=================================================");

        Scanner sc = new Scanner(System.in);
        ArrayList<Publisher> publisherList = MainService.listPublishers();
        for (int i = 0; i < publisherList.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + publisherList.get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int publisherIndex = Integer.parseInt(sc.next());
        List<Material> publisherBooks = MainService.searchByPublisher(publisherList.get(publisherIndex).getName());
        if (publisherBooks == null){
            System.out.println(ANSI_RED + "|  There is no materıal for pubisher            |");
            searchMaterials(userType);
            return;
        }
        for (int i = 0; i < publisherBooks.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + publisherBooks.get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int bookIndex = Integer.parseInt(sc.next());
        while (bookIndex < 0 || bookIndex > publisherBooks.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            bookIndex = Integer.parseInt(sc.next());
        }
        printBook(publisherBooks.get(bookIndex));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

    /**
     * Menu that prints the list of categories, asks the user to choose one of them, then prints the list of materials in that
     * category, and asks the user to choose one of them, then prints the details of that material
     *
     * @param userType This is the type of user that is currently logged in.
     */
    static void searchByCategory(char userType) {

        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Category                    |");
        System.out.println(ANSI_BLUE + "=================================================");

        Scanner sc = new Scanner(System.in);
        System.out.printf("%s", MainService.listCategories());

        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int categoryIndex = Integer.parseInt(sc.next());
        List<Material> categoryBooks = MainService.searchByCategory(categoryIndex);
        if (categoryBooks == null){
            System.out.println(ANSI_RED + "|  There is no materıal for category            |");
            searchMaterials(userType);
            return;
        }
        for (int i = 0; i < categoryBooks.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + categoryBooks.get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int bookIndex = Integer.parseInt(sc.next());
        while (bookIndex < 0 || bookIndex > categoryBooks.size() - 1) {
            System.out.println(ANSI_RED + "      Invalid input!");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            bookIndex = Integer.parseInt(sc.next());
        }
        printBook(categoryBooks.get(bookIndex));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

    /**
     * Menu that is used to search for a book by its rate
     *
     * @param userType This is the type of user that is currently logged in.
     */
    static void searchByRate(char userType) {
        System.out.print(ANSI_CYAN + "   Enter the rate of Book : ");
        Scanner sc = new Scanner(System.in);
        int materialRate = Integer.parseInt(sc.next());
        List<Material> newMaterials = MainService.searchByRate(materialRate);
        if (newMaterials == null) {
            System.out.println(ANSI_RED + "   The book your searched couldn't find :(");
            searchMaterials(userType);
        } else {
            for (int i = 0; i < newMaterials.size(); i++) {
                System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + newMaterials.get(i).getName() + "\t\t"
                        + newMaterials.get(i).getRateAve() + "\t\t\t\t\t\t");
            }
            System.out.println(ANSI_BLUE + "=================================================");

        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int bookIndex = Integer.parseInt(sc.next());
        if(newMaterials.size()==0)
            System.out.print(ANSI_RED + "  No material found for your search criteria ");
        else
            printBook(newMaterials.get(bookIndex));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }




    /**
     * This function is used to print the all information of a book
     *
     * @param newMat The material object that you want to print.
     */
    static void printBook(Material newMat){
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.printf(ANSI_BLUE + "|\t\t\t\t%-32s|\n", newMat.getName());
        System.out.println(ANSI_BLUE + "=================================================");
        if (newMat.getIsLoaned())
            System.out.println(ANSI_RED + "|\t\t\tSituation : Loaned              |");
        else
            System.out.println(ANSI_GREEN + "|\t\t\tSituation : Avaliable               |");
        System.out.printf(ANSI_CYAN + "|\t\t\tAuthor : %-27s|\n", newMat.getAuthor().getName());
        System.out.printf(ANSI_CYAN + "|\t\t\tPage : %-29d|\n", newMat.getPageCount());
        System.out.printf(ANSI_CYAN + "|\t\t\tPublisher : %-24s|\n", newMat.getPublisher().getName());
        System.out.printf(ANSI_CYAN + "|\t\t\tCategory : %-25s|\n", newMat.getCategory());
        System.out.printf(ANSI_CYAN + "|\t\t\tLodation : %-25s|\n", newMat.getLocation());
        System.out.printf(ANSI_CYAN + "|\t\t\tRate : %-29s|\n", newMat.getRateAve());
        System.out.printf(ANSI_CYAN + "|\t\t\tType : %-29s|\n", newMat.getType());

        String[] paragraph = paragraphString(newMat.getInfo());
        int i = 0;
        while (paragraph[i]!=null) {
            System.out.printf(ANSI_CYAN + "|%-47s|\n", paragraph[i]);
            i++;
        }
        System.out.println(ANSI_BLUE + "=================================================");
    }



    static String[] paragraphString(String line) {

        String[] words = line.split(" ");
        String[] paragraph = new String[10];
        int pindex = 0;
        for (int i = 0; i < words.length; i++) {
            if(paragraph[pindex]==null){
                paragraph[pindex] = new String();
            }
            if (paragraph[pindex].length() + words[i].length() > 46) {
                while (paragraph[pindex].length() < 46)
                    paragraph[pindex] = paragraph[pindex] + " ";
                pindex++;

                paragraph[pindex] =  words[i];
            } else
                paragraph[pindex] = paragraph[pindex] + " " + words[i];
        }
        return paragraph;
    }
}
