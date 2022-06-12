package librarymanagementsystem.model;

import java.util.Objects;

/**
 * Account is an abstract class that represents an account in a library
 */
public abstract class Account implements Comparable<Account>{
    // A private variable that is used to store the id of the account.
    private int id;
    // Used to keep track of the number of accounts that have been created.
    private static int count;
    // A private variable that is used to store the username of the account.
    private String username;
    // A private variable that is used to store the name of the account.
    private String name;
    // A private variable that is used to store the surname of the account.
    private String surname;
    // A private variable that is used to store the password of the account.
    private String password;
    // A reference to the library that the account is associated with.
    private Library library;

    public Account(String name, String surname, String username, String password, Library library) {
        this.id = count++;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.library = library;
    }

    /**
     * This function sets the current account to the account passed in as a parameter.
     *
     * @param newAccount The account that will be used to set the current account's parameters.
     */
    public void setAccount(Account newAccount)
    {
        this.id = newAccount.id;
        this.name = newAccount.name;
        this.surname = newAccount.surname;
        this.username = newAccount.username;
        this.password = newAccount.password;
        this.library = newAccount.library;
    }

    /**
     * This function returns the username of the user
     *
     * @return The username
     */
    public String getUsername()
    {
        return username;
    }

    /**
     * This function sets the username of the user
     *
     * @param username The username of the user you want to get the information of.
     */
    public void setUsername(String username)
    {
        this.username = username;
    }
    /**
     * This function returns the id of the object.
     *
     * @return The id of the object.
     */
    public int getId() {
        return id;
    }

    /**
     * This function sets the id of the object to the value of the parameter id.
     *
     * @param id The id of the user.
     */
    public void setId(int id) {
        this.id = id;
    }

    /**
     * This function returns the name of the person.
     *
     * @return The name of the person.
     */
    public String getName() {
        return name;
    }

    /**
     * This function sets the name of the object to the value of the parameter.
     *
     * @param name The name of the parameter.
     */
    public void setName(String name) {
        this.name = name;
    }

    /**
     * > This function returns the surname of the person
     *
     * @return The surname of the person.
     */
    public String getSurname() {
        return surname;
    }

    /**
     * This function sets the surname of the person to the value of the parameter.
     *
     * @param surname The surname of the person.
     */
    public void setSurname(String surname) {
        this.surname = surname;
    }

    /**
     * This function returns the password of the user
     *
     * @return The password
     */
    public String getPassword() {
        return password;
    }

    /**
     * This function sets the password of the user.
     *
     * @param password The password to use for the connection.
     */
    public void setPassword(String password) {
        this.password = password;
    }

    /**
     * This function returns the library object.
     *
     * @return The library object.
     */
    public Library getLibrary() {
        return library;
    }

    /**
     * This function sets the library of the current object to the library passed in as a parameter.
     *
     * @param library The library to which the book belongs.
     */
    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    // Comparing the current object to the object passed in as a parameter.
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getName(), account.getName()) && Objects.equals(getSurname(), account.getSurname()) && Objects.equals(getPassword(), account.getPassword()) && Objects.equals(getLibrary(), account.getLibrary());
    }

    @Override
    // A function that is used to generate a hash code for the object.
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPassword(), getLibrary());
    }

    // Comparing the current object to the object passed in as a parameter.
    @Override
    public int compareTo(Account account) {
        try {
            if(account == null)
                throw new NullPointerException();

            if(id == account.id){
                return 0;
            }

            else if(id < account.id)
                return -1;

            else
                return 1;
        } catch (NullPointerException e){
            System.out.println("Account.compareTo: account is null");
        }

        return -1;
    }
}
