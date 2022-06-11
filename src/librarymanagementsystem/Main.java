package librarymanagementsystem;

import librarymanagementsystem.dao.LibraryRepositoryImpl;
import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.Library;
import librarymanagementsystem.service.MainService;
import java.util.Scanner;

public class Main {

    public static final String ANSI_RED = "\u001B[31m";

    public static final String ANSI_GREEN = "\u001B[32m";
    public static final String ANSI_YELLOW = "\u001B[33m";

    public static final String ANSI_BLUE = "\u001B[34m";
    public static final String ANSI_PURPLE = "\u001B[35m";
    public static final String ANSI_CYAN = "\u001B[36m";



        public static void main (String[]args){
            MainService.mockData();
            Scanner sc = new Scanner(System.in);

            System.out.println(ANSI_BLUE + "====== Welcome to Library Management System ======");
            System.out.println(ANSI_CYAN + "1. Login");
            System.out.println(ANSI_CYAN + "2. Register");
            System.out.println(ANSI_CYAN + "0. Exit");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");

        while (true){
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


    static void loginMenu () {
        System.out.println(ANSI_BLUE + "\n\n====== Login ======");
        System.out.print(ANSI_GREEN + "Username :  ");
        Scanner sc = new Scanner(System.in);
        String username = sc.next();
        System.out.print(ANSI_GREEN + "Password :  ");
        String password = sc.next();

        Account newLogin = MainService.login(username,password);
        if (newLogin == null){
            System.out.println(ANSI_RED + "Username or possword is wrong!");
            loginMenu();
        }
        else{
            switch (newLogin.getClass().getName()){
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

    static void registerMenu () {
        System.out.println(ANSI_BLUE + "\n\n====== Register ======");
        Scanner sc = new Scanner(System.in);
        System.out.println(ANSI_BLUE + "Libraries to register");
        for (int i = 0; i < MainService.listLibraries().size();i++){
            System.out.println(ANSI_CYAN + "\t" + i + ". " +MainService.listLibraries().get(i).getName());
        }
        System.out.print(ANSI_GREEN + "Choose one of the options : ");
        int libraryid = Integer.parseInt(sc.next());
        while (libraryid < 1 || libraryid > MainService.listLibraries().size()){
            System.out.println(ANSI_RED + "Invalid input!");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");
            libraryid = Integer.parseInt(sc.next());
        }

        System.out.print(ANSI_GREEN + "Name :  ");
        String name = sc.next();
        System.out.print(ANSI_GREEN + "Surname :  ");
        String surname = sc.next();
        System.out.print(ANSI_GREEN + "Username :  ");
        String username = sc.next();
        while (!MainService.isUniqueUserName(username)){
            System.out.println(ANSI_RED + "This username already taken! Please try another one.");
            System.out.print(ANSI_GREEN + "Username :  ");
            username = sc.next();
        }
        System.out.print(ANSI_GREEN + "Password :  ");
        String password = sc.next();

        MainService.register(name,surname,username,password, libraryid);

        loginMenu();


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
    static void readerMenu (String usersName) {
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

    }





    static void manageLibraries ( char userType){
        System.out.println(ANSI_BLUE + "\n\n====== Manage Libraries ======");
        if (userType == 'a'){
            System.out.println(ANSI_CYAN + "1. Add library");
            System.out.println(ANSI_CYAN + "2. Remove library");
            System.out.println(ANSI_CYAN + "3. Edit library");
            System.out.println(ANSI_CYAN + "0. Exit");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");
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

            System.out.println(ANSI_CYAN + "1. Edit library");
            System.out.println(ANSI_CYAN + "0. Exit");
            System.out.print(ANSI_GREEN + "Choose one of the options : ");
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
    static void manageLibraryManagers () {
        System.out.println(ANSI_BLUE + "\n\n====== Manage Library Managers ======");
        System.out.println(ANSI_CYAN + "1. Add library manager");
        System.out.println(ANSI_CYAN + "2. Remmove library manager");
        System.out.println(ANSI_CYAN + "3. Edit library manager");
        System.out.print(ANSI_GREEN + "Choose one of the options : ");

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
    static void manageLibrarians () {
        System.out.println(ANSI_BLUE + "\n\n====== Manage Librarians ======");
        System.out.println(ANSI_CYAN + "1. Add librarian");
        System.out.println(ANSI_CYAN + "2. Remmove librarian");
        System.out.println(ANSI_CYAN + "3. Edit librarian");
        System.out.print(ANSI_GREEN + "Choose one of the options : ");

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
    static void manageReaders () {
        System.out.println(ANSI_BLUE + "\n\n====== Manage Readers ======");
        System.out.println(ANSI_CYAN + "1. Add reader");
        System.out.println(ANSI_CYAN + "2. Remmove reader");
        System.out.println(ANSI_CYAN + "3. Edit reader");
        System.out.print(ANSI_GREEN + "Choose one of the options : ");

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




    static void manageLoanBooks () {
        System.out.println(ANSI_BLUE + "\n\n====== Manage Loan Books ======");
        System.out.println(ANSI_CYAN + "1. Add loan book");
        System.out.println(ANSI_CYAN + "2. Remmove loan book");
        System.out.println(ANSI_CYAN + "3. Edit loan book");
        System.out.print(ANSI_GREEN + "Choose one of the options : ");

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
    static void manageBooks () {
        System.out.println(ANSI_BLUE + "\n\n====== Manage Books ======");
        System.out.println(ANSI_CYAN + "1. Add book");
        System.out.println(ANSI_CYAN + "2. Remmove book");
        System.out.println(ANSI_CYAN + "3. Edit book");
        System.out.print(ANSI_GREEN + "Choose one of the options : ");

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
    static void manageMagazines () {
        System.out.println(ANSI_BLUE + "\n\n====== Manage Magazines ======");
        System.out.println(ANSI_CYAN + "1. Add magazine");
        System.out.println(ANSI_CYAN + "2. Remmove magazine");
        System.out.println(ANSI_CYAN + "3. Edit magazine");
        System.out.print(ANSI_GREEN + "Choose one of the options : ");

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
    static void searchBooks () {
        System.out.println(ANSI_BLUE + "\n\n====== Search Books ======");
        System.out.println(ANSI_CYAN + "1. Search by Name");
        System.out.println(ANSI_CYAN + "2. Search by Author");
        System.out.println(ANSI_CYAN + "3. Search by Publisher");
        System.out.println(ANSI_CYAN + "4. Search by Category");
        System.out.println(ANSI_CYAN + "5. Search by Rate");
        System.out.println(ANSI_CYAN + "0. Exit");
        System.out.print(ANSI_GREEN + "Choose one of the options : ");

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
    static void rateBook() {

    }


    static void addLibrary(){

    }
    static void removeLibrary(){

    }
    static void editLibrary(){

    }


    static void addManager(){

    }
    static void removeManager(){

    }
    static void editManager(){

    }



    static void addLibrarian(){

    }
    static void removeLibrarian(){

    }
    static void editLibrarian(){

    }


    static void addReader(){

    }
    static void removeReader(){

    }
    static void editReader(){

    }



    static void addLoanBook(){

    }
    static void removeLoanBook(){

    }
    static void editLoanBook(){

    }


    static void addBook(){

    }
    static void removeBook(){

    }
    static void editBook(){

    }


    static void addMagazine(){

    }
    static void removeMagazine(){

    }
    static void editMagazine(){

    }



    static void searchByName(){

    }
    static void searchByAuthor(){

    }
    static void searchByPublisher(){

    }
    static void searchByCategory(){

    }
    static void searchByRate(){

    }



}
