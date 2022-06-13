package librarymanagementsystem.test;
import librarymanagementsystem.model.Account;

import librarymanagementsystem.model.Material;
import librarymanagementsystem.model.Category;
import librarymanagementsystem.service.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;


public class testReader {

    @BeforeEach
    void setUp(){
        MainService.mockData();
    }

    @Disabled
    @Test
    void testLogin(){
        // test with correct username and password
        Account newLogin = MainService.login("emre9180", "1234");
        assertNotNull(newLogin,"Username or password is wrong!");

        // test with incorrect username and password
        newLogin = MainService.login("emre9180", "wrongPass");
        assertNull(newLogin);
    }

    @Disabled
    @Test // tbi
    void testRateBook(){
        Material newMat = MainService.searchByName("Beyaz");
        MainService.addRate(newMat, 5);
    }

    @Disabled
    @Test
    void testSearchLoanBook(){
        // tests if the searched material is loaned or not
        Material newMat = MainService.searchByName("Amok");
        assertEquals(false,newMat.getIsLoaned());
    }

    @Disabled
    @Test
    void testSearchBook(){
        // tests if the given book exists in the library
        Material newMat = MainService.searchByName("Beyaz");
        assertNull(newMat);
    }

    @Disabled
    @Test
    void testSearchAuthor(){
        // tests if the given publisher exists or not
        List<Material> booksByAuthor = MainService.searchByAuthor("Stefan");
        assertNotNull(booksByAuthor, "No such author exists!");
    }

    @Disabled
    @Test
    void testSearchPublisher(){
        // tests if the given publisher exists or not
        assertNotNull(MainService.searchByPublisher("Yapikredi"), "No such publisher exists!");
    }

    @Disabled
    @Test
    void testSearchByCategory(){
        // tests if the given category has any books or not
        List<Material> booksByCategory = MainService.searchByCategory(Category.BIOGRAPHIES.ordinal());
        assertNotNull(booksByCategory, "No such books exists in this category!");
        if(booksByCategory.size() == 0)
            throw new NoSuchElementException("No elements in the list!");
    }

}
