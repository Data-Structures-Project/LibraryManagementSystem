package librarymanagementsystem.test;
import librarymanagementsystem.model.Account;
import librarymanagementsystem.model.City;

import static org.junit.jupiter.api.Assertions.assertThrows;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNotNull;
import static org.junit.jupiter.api.Assertions.assertNull;

import librarymanagementsystem.service.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;


public class testAdministrator {

    @BeforeEach
    void setUp(){
        MainService.mockData();
    }

    @Disabled
    @Test
    void testLogin(){
        // test with correct username and password
        Account newLogin = MainService.login("Administrator", "1234");
        assertNotNull(newLogin,"Username or password is wrong!");

        // test with incorrect username and password
        newLogin = MainService.login("Administrator", "wrongPass");
        assertNull(newLogin);
    }

    @Disabled
    @Test
    void testRemoveLibrary(){
        // test with valid value for get() method
        MainService.removeLibrary(MainService.listLibraries().get(1));

        // test with invalid value for get() method
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.removeLibrary(MainService.listLibraries().get(15)));
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

}
