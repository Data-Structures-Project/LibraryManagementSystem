package librarymanagementsystem.model;

import java.util.Objects;

public abstract class Account implements Comparable<Account>{
    private int id;
    private String username;
    private String name;
    private String surname;
    private String password;
    private Library library;

    public Account(int id, String name, String surname, String username, String password, Library library) {
        this.id = id;
        this.name = name;
        this.surname = surname;
        this.username = username;
        this.password = password;
        this.library = library;
    }

    public void setAccount(Account newAccount)
    {
        this.id = newAccount.id;
        this.name = newAccount.name;
        this.surname = newAccount.surname;
        this.username = newAccount.username;
        this.password = newAccount.password;
        this.library = newAccount.library;
    }

    public String getUsername()
    {
        return username;
    }

    public void setUsername(String username)
    {
        this.username = username;
    }
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getSurname() {
        return surname;
    }

    public void setSurname(String surname) {
        this.surname = surname;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public Library getLibrary() {
        return library;
    }

    public void setLibrary(Library library) {
        this.library = library;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Account)) return false;
        Account account = (Account) o;
        return Objects.equals(getId(), account.getId()) && Objects.equals(getName(), account.getName()) && Objects.equals(getSurname(), account.getSurname()) && Objects.equals(getPassword(), account.getPassword()) && Objects.equals(getLibrary(), account.getLibrary());
    }

    @Override
    public int hashCode() {
        return Objects.hash(getId(), getName(), getSurname(), getPassword(), getLibrary());
    }

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
