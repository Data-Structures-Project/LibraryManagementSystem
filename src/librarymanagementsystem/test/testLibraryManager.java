package librarymanagementsystem.test;
import librarymanagementsystem.model.Account;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import librarymanagementsystem.model.Librarian;
import librarymanagementsystem.service.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

public class testLibraryManager {

    @BeforeEach
    void setUp(){
        MainService.mockData();
    }

    @Disabled
    @Test
    void testLogin(){
        // test with correct username and password
        Account newLogin = MainService.login("ikaratekin", "1234");
        assertNotNull(newLogin,"Username or password is wrong!");

        // test with incorrect username and password
        newLogin = MainService.login("ikaratekin", "wrongPass");
        assertNull(newLogin);
    }

    @Disabled
    @Test
    void addLibrarian(){
        // test with valid Library number
        MainService.addAccount(new Librarian("onur", "bilgin", "newUserNamee", "1234", MainService.listLibraries().get(0)));

        // test with invalid Library number
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.listLibraries().get(15));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

    @Disabled
    @Test
    void removeLibrarian(){
        // test with valid Library number
        MainService.removeAccount(MainService.listLibrarians().get(0));

        // test with invalid Library number
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.removeAccount(MainService.listLibrarians().get(15)));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

    @Disabled
    @Test
    void testEditLibrary(){
        // test with valid value for get() method
        MainService.listLibraries().get(0);

        // test with invalid value for get() method
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.listLibraries().get(15));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

    @Disabled
    @Test
    void testEditLibrarian(){
        // test with valid value for get() method
        MainService.listLibrarians().get(0);

        // test with invalid value for get() method
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.listLibrarians().get(15));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

}
