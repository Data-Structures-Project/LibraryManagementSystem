package librarymanagementsystem;

import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.Author;
import librarymanagementsystem.model.Material;
import librarymanagementsystem.model.Publisher;
import librarymanagementsystem.service.MainService;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;
import java.util.Locale.Category;

public class Main {

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";

    public static void main(String[] args) {
        MainService.mockData();
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
            switch (newLogin.getClass().getName()) {
                case "librarymanagementsystem.model.Administrator":
                    administratorMenu(newLogin.getName());
                    break;
                case "librarymanagementsystem.model.LibraryManager":
                    libraryManagerMenu(newLogin.getName());
                    break;
                case "librarymanagementsystem.model.Librarian":
                    librarianMenu(newLogin.getName());
                    break;
                case "librarymanagementsystem.model.User":
                    readerMenu(newLogin.getName());
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

    static void administratorMenu(String usersName) {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|\t\t\t\tHello " + usersName + "\t\t\t\t\t|");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Manage Libraries                   |");
        System.out.println(ANSI_CYAN + "|         2. Manege Library Managers            |");
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
            case "0":
                System.exit(0);
                break;
            default:
                break;

        }

    }

    static void libraryManagerMenu(String usersName) {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|\t\t\t\tHello " + usersName + "\t\t\t\t\t|");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Edit Libraries                     |");
        System.out.println(ANSI_CYAN + "|         2. Manege Librarians                  |");
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
            case "0":
                System.exit(0);
                break;
            default:
                break;

        }
    }

    static void librarianMenu(String usersName) {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|\t\t\t\tHello " + usersName + "\t\t\t\t\t|");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Manage Readers                     |");
        System.out.println(ANSI_CYAN + "|         2. Manege loan books                  |");
        System.out.println(ANSI_CYAN + "|         3. Manege books                       |");
        System.out.println(ANSI_CYAN + "|         4. Manege magazines                   |");
        System.out.println(ANSI_CYAN + "|         5. Search books                       |");
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
                searchMaterials();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                break;

        }
    }

    static void readerMenu(String usersName) {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|\t\t\t\tHello " + usersName + "\t\t\t\t\t|");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Search materials                   |");
        System.out.println(ANSI_CYAN + "|         2. Rate a materials                   |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                searchMaterials();
                break;
            case "2":
                rateMaterials();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                break;

        }

    }

    static void manageLibraries(char userType) {

        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                 Manage Libraries              |");
        System.out.println(ANSI_BLUE + "=================================================");
        if (userType == 'a') {
            System.out.println(ANSI_CYAN + "|         1. Add library                        |");
            System.out.println(ANSI_CYAN + "|         2. Remmove library                    |");
            System.out.println(ANSI_CYAN + "|         3. Edit library                       |");
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
                case "0":
                    System.exit(0);
                    break;
                default:
                    break;

            }
        }

        else {

            System.out.println(ANSI_CYAN + "|         3. Edit library                       |");
            System.out.println(ANSI_CYAN + "|         0. Exit                               |");
            System.out.println(ANSI_BLUE + "=================================================");
            System.out.print(ANSI_GREEN + "   Choose one of the options : ");
            Scanner sc = new Scanner(System.in);
            String input = sc.next();

            switch (input) {
                case "1":
                    editLibrary();
                    break;
                case "0":
                    System.exit(0);
                    break;
                default:
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
            case "0":
                System.exit(0);
                break;
            default:
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
            case "0":
                System.exit(0);
                break;
            default:
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
            case "0":
                System.exit(0);
                break;
            default:
                break;

        }
    }

    static void manageLoanBooks() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                Manage Loan Books              |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Add loan book                      |");
        System.out.println(ANSI_CYAN + "|         2. Remmove loan book                  |");
        System.out.println(ANSI_CYAN + "|         3. Edit loan book                     |");
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
                editLoanBook();
                break;
            case "0":
                System.exit(0);
                break;
            default:
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
            case "0":
                System.exit(0);
                break;
            default:
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
            case "0":
                System.exit(0);
                break;
            default:
                break;

        }
    }

    static void searchMaterials() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                  Search Materials             |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.println(ANSI_CYAN + "|         1. Search by Name                     |");
        System.out.println(ANSI_CYAN + "|         2. Search by Author                   |");
        System.out.println(ANSI_CYAN + "|         3. Search by Publisher                |");
        System.out.println(ANSI_CYAN + "|         4. Search by Category                 |");
        System.out.println(ANSI_CYAN + "|         5. Search by Rate                     |");
        System.out.println(ANSI_CYAN + "|         0. Exit                               |");
        System.out.println(ANSI_BLUE + "=================================================");
        System.out.print(ANSI_GREEN + "   Choose one of the options : ");

        Scanner sc = new Scanner(System.in);
        String input = sc.next();

        switch (input) {
            case "1":
                searchByName();
                break;
            case "2":
                searchByAuthor();
                break;
            case "3":
                searchByPublisher();
                break;
            case "4":
                searchByCategory();
                break;
            case "5":
                searchByRate();
                break;
            case "0":
                System.exit(0);
                break;
            default:
                break;

        }
    }

    static void rateMaterials() {
        System.out.print(ANSI_CYAN + "   Enter the name of the book want to Rate : ");
        Scanner sc = new Scanner(System.in);
        String materialName = sc.next();
        Material newMat = MainService.searchByName(materialName);
        if (newMat == null) {
            System.out.println(ANSI_RED + "   The book your searched couldn't find :(");
            searchByName();
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

        System.out.print(ANSI_CYAN + "   Enter the Rate : ");
        int materialRate = Integer.parseInt(sc.next());
        MainService.addRate(newMat, materialRate);

        System.out.println( newMat.getRateAve());

    }

    static void addLibrary() {

    }

    static void removeLibrary() {

    }

    static void editLibrary() {

    }

    static void addManager() {

    }

    static void removeManager() {

    }

    static void editManager() {

    }

    static void addLibrarian() {

    }

    static void removeLibrarian() {

    }

    static void editLibrarian() {

    }

    static void addReader() {

    }

    static void removeReader() {

    }

    static void editReader() {

    }

    static void addLoanBook() {

    }

    static void removeLoanBook() {

    }

    static void editLoanBook() {

    }

    static void addBook() {

    }

    static void removeBook() {

    }

    static void editBook() {

    }

    static void addMagazine() {

    }

    static void removeMagazine() {

    }

    static void editMagazine() {

    }

    static void searchByName() {
        System.out.print(ANSI_CYAN + "   Enter the name of the book : ");
        Scanner sc = new Scanner(System.in);
        String materialName = sc.next();
        Material newMat = MainService.searchByName(materialName);
        if (newMat == null) {
            System.out.println(ANSI_RED + "   The book your searched couldn't find :(");
            searchByName();
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
    }

    static void searchByAuthor() {
        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Authors                    |");
        System.out.println(ANSI_BLUE + "=================================================");

        Scanner sc = new Scanner(System.in);
        ArrayList<Author> authorList = MainService.authorList();
        for (int i = 0; i < authorList.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + authorList.get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int authorIndex = Integer.parseInt(sc.next());
        List<Material> authorBooks = MainService.searchByAuthor(authorList.get(authorIndex).getName());
        if (authorBooks == null)
            searchByAuthor();
        for (int i = 0; i < authorBooks.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + authorBooks.get(i).getName() + "\t\t\t\t\t\t");
        }
    }

    static void searchByPublisher() {

        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Publisher                    |");
        System.out.println(ANSI_BLUE + "=================================================");

        Scanner sc = new Scanner(System.in);
        ArrayList<Publisher> publisherList = MainService.publisherList();
        for (int i = 0; i < publisherList.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + publisherList.get(i).getName() + "\t\t\t\t\t\t");
        }
        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int publisherIndex = Integer.parseInt(sc.next());
        List<Material> publisherBooks = MainService.searchByPublisher(publisherList.get(publisherIndex).getName());
        if (publisherBooks == null)
            searchByPublisher();
        for (int i = 0; i < publisherBooks.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + publisherBooks.get(i).getName() + "\t\t\t\t\t\t");
        }
    }

    static void searchByCategory() {

        System.out.println(ANSI_BLUE + "\n\n=================================================");
        System.out.println(ANSI_BLUE + "|                    Category                    |");
        System.out.println(ANSI_BLUE + "=================================================");

        Scanner sc = new Scanner(System.in);
        System.out.printf("%s", MainService.getCategoryList());

        System.out.print(ANSI_GREEN + "  Choose one of the options : ");
        int categoryIndex = Integer.parseInt(sc.next());
        List<Material> categoryBooks = MainService.searchByCategory(categoryIndex);
        if (categoryBooks == null)
            searchByCategory();
        for (int i = 0; i < categoryBooks.size(); i++) {
            System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + categoryBooks.get(i).getName() + "\t\t\t\t\t\t");
        }
    }

    static void searchByRate() {
        System.out.print(ANSI_CYAN + "   Enter the rate of Book : ");
        Scanner sc = new Scanner(System.in);
        int materialRate = Integer.parseInt(sc.next());
        List<Material> newMaterials = MainService.searchByRate(materialRate);
        if (newMaterials == null) {
            System.out.println(ANSI_RED + "   The book your searched couldn't find :(");
            searchByRate();
        } else {
            for (int i = 0; i < newMaterials.size(); i++) {
                System.out.println(ANSI_CYAN + "|\t\t" + i + ". " + newMaterials.get(i).getName() + "\t\t"
                        + newMaterials.get(i).getRateAve() + "\t\t\t\t\t\t");
            }
            System.out.println(ANSI_BLUE + "=================================================");

        }
    }

}
