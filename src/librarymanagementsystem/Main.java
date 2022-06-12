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
import librarymanagementsystem.model.Category;
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

    public static void main(String[] args) {
        MainService.mockData();
        mainMenu();

    }

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

    static void helloBanner(String usersName){
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.printf(ANSI_BLUE + "|\t\t\t\tHello %-26s|\n", usersName);
    }

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

        }
    }

    static void librarianMenu() {
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Manage Readers                     |");
        System.out.println(ANSI_CYAN + "|         2. Manege loan books                  |");
        System.out.println(ANSI_CYAN + "|         3. Manege books                       |");
        System.out.println(ANSI_CYAN + "|         4. Manege magazines                   |");
        System.out.println(ANSI_CYAN + "|         5. Search books                       |");
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

    static void manageLoanBooks() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                Manage Loan Books              |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add loan book                      |");
        System.out.println(ANSI_CYAN + "|         2. Remmove loan book                  |");
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

    static void searchMaterials(char userType) {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                  Search Materials             |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Search by Name                     |");
        System.out.println(ANSI_CYAN + "|         2. Search by Author                   |");
        System.out.println(ANSI_CYAN + "|         3. Search by Publisher                |");
        System.out.println(ANSI_CYAN + "|         4. Search by Category                 |");
        System.out.println(ANSI_CYAN + "|         5. Search by Rate                     |");
        System.out.println(ANSI_CYAN + "|         6. Back                               |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                searchByName(userType);
                break;
            case "2":
                searchByAuthor(userType);
                break;
            case "3":
                searchByPublisher(userType);
                break;
            case "4":
                searchByCategory(userType);
                break;
            case "5":
                searchByRate(userType);
                break;
            case "6":
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

    static void rateMaterials() {
        System.out.print(ANSI_CYAN + "   Enter the name of the book want to Rate : ");
        Scanner sc = new Scanner(System.in);
        String materialName = sc.next();
        Material newMat = MainService.searchByName(materialName);
        if (newMat == null) {
            System.out.println(ANSI_RED + "   The book your searched couldn't find :(");
            searchByName('r');
        } else {
            printBook(newMat);
        }

        System.out.print(ANSI_CYAN + "|   Enter the Rate 0-5 : ");
        int materialRate = Integer.parseInt(sc.next());
        MainService.addRate(newMat, materialRate);

        System.out.println(ANSI_BLUE + "|   New average rate => " + newMat.getRateAve() );
        System.out.println(ANSI_BLUE + "=================================================");

        readerMenu();
    }

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

    static void addLoanBook() {

        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_CYAN + "   Name of the book you want to change status : ");
        String loanName = sc.next();

        if (MainService.addLoanBook(loanName)){
            System.out.print(ANSI_GREEN + "   Success! \n");
            librarianMenu();
            return;
        }
        else
            System.out.println(ANSI_RED + "|  There is no book to change status");
        librarianMenu();
    }

    static void removeLoanBook() {
        Scanner sc = new Scanner(System.in);
        System.out.print(ANSI_CYAN + "   Name of the book you want to change status : ");
        String loanName = sc.next();

        if (MainService.removeLoanBook(loanName)){
            System.out.print(ANSI_GREEN + "   Success! \n");
            librarianMenu();
            return;
        }
        else
            System.out.println(ANSI_RED + "|  There is no book to change status");
        librarianMenu();
    }

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

        librarianMenu();
    }

    static void removeBook() {
        Scanner sc = new Scanner(System.in);
        
        // TODO Kullanıcıdan kitabın ismi alınacak, kitap varsa silmeye ismi gönderilecek 

        System.out.print(ANSI_CYAN + "   Enter the number of Libray want to add : ");
        String bookName = sc.next();

        MainService.removeMaterial(bookName);
    }

    static void editBook() {

    }

    static void addMagazine() {
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_BLUE + "|   Choose a Library to add the book            |");
        ArrayList<Library> libList = MainService.listLibraries();

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
        for (int i = 0; i < authList.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + authList.get(i) + "\t\t\t\t\t\t");
        }
        System.out.println(ANSI_BLUE + "| to add new author press 'A'                   |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        String authinput = sc.next();
        int authid = Integer.parseInt(authinput);
        while (authid < 0 || authid > authList.size() - 1|| authinput == "A") {
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
        for (int i = 0; i < pubList.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + pubList.get(i) + "\t\t\t\t\t\t");
        }
        System.out.println(ANSI_BLUE + "| to add new publisher press 'A'                |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        String pubinput = sc.next();
        int pubid = Integer.parseInt(pubinput);
        while (pubid < 0 || pubid > pubList.size() - 1|| pubinput == "A") {
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

        librarianMenu();
    }

    static void removeMagazine() {
        Scanner sc = new Scanner(System.in);

        // TODO Kullanıcıdan kitabın ismi alınacak, kitap varsa silmeye ismi gönderilecek

        System.out.print(ANSI_CYAN + "   Enter the number of Libray want to add : ");
        String bookName = sc.next();

        MainService.removeMaterial(bookName);
    }

    static void editMagazine() {

    }

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
            System.out.println(ANSI_BLUE + "\n\n=================================================");
            System.out.println(ANSI_BLUE + "|\t\t\t\t" + newMat.getName() + "\t\t\t\t\t|");
            System.out.println(ANSI_BLUE + "=================================================");
            if (newMat.getIsLoaned())
                System.out.println(ANSI_RED + "|\t\t\t\tSituation : Loaned\t\t\t\t\t|");
            else
                System.out.println(ANSI_GREEN + "|\t\t\t\tSituation : Avaliable\t\t\t\t\t|");
            System.out.println(ANSI_CYAN + "|\t\t\t\tAuthor : " + newMat.getAuthor().getName() + "\t\t\t\t\t|");
            System.out.println(ANSI_CYAN + "|\t\t\t\tPage : " + newMat.getPageCount() + "\t\t\t\t\t|");
            System.out.println(ANSI_CYAN + "|\t\t\t\tPublisher : " + newMat.getPublisher().getName() + "\t\t\t\t\t|");
            System.out.println(ANSI_CYAN + "|\t\t\t\tCategory : " + newMat.getCategory() + "\t\t\t\t\t|");
            System.out.println(ANSI_CYAN + "|\t\t\t\tLocation : " + newMat.getLocation().toString() + "\t\t\t\t\t|");
            System.out.println(ANSI_BLUE + "|\t\t\t\tRate : " + newMat.getRateAve() + "\t\t\t\t\t|");
            System.out.println(ANSI_BLUE + "|\t\t\t\tType : " + newMat.getType() + "\t\t\t\t\t|");
            System.out.println(ANSI_BLUE + newMat.getInfo());
            System.out.println(ANSI_BLUE + "=================================================");
        }
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

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
        printBook(authorBooks.get(bookIndex));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

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
            searchMaterials(userType);
            return;
        }
        for (int i = 0; i < publisherBooks.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + publisherBooks.get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int bookIndex = Integer.parseInt(sc.next());
        printBook(publisherBooks.get(bookIndex));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

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
            searchMaterials(userType);
            return;
        }
        for (int i = 0; i < categoryBooks.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + categoryBooks.get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int bookIndex = Integer.parseInt(sc.next());
        printBook(categoryBooks.get(bookIndex));
        System.out.print(ANSI_GREEN + "  Press any key and Enter to go back : ");
        sc.next();
        if (userType == 'l'){
            librarianMenu();
        }
        else
            readerMenu();
    }

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
        System.out.printf(ANSI_CYAN + "|\t\t\tInfo : %-29s|\n", newMat.getInfo());
        System.out.println(ANSI_BLUE + "=================================================");
    }
}
