package librarymanagementsystem.test;
import librarymanagementsystem.Main;
import librarymanagementsystem.model.*;

import librarymanagementsystem.service.MainService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.Disabled;

import java.util.Date;
import java.util.List;
import java.util.NoSuchElementException;

import static org.junit.jupiter.api.Assertions.*;

public class testLibrarian {

    @BeforeEach
    void setUp(){
        MainService.mockData();
    }

    @Disabled
    @Test
    void testLogin(){
        // test with correct username and password
        Account newLogin = MainService.login("l", "1");
        assertNotNull(newLogin,"Username or password is wrong!");

        // test with incorrect username and password
        newLogin = MainService.login("l", "wrongPass");
        assertNotNull(newLogin,"Username or password is wrong!");
    }

    @Disabled
    @Test
    void testAddReader(){
        int beforeSize = MainService.listReaders().size();

        // test with unique username
        assertEquals(true, MainService.isUniqueUserName("testUser"), "Username already exists!");
        MainService.addAccount(new User("testName", "testSurname", "testUser", "1234", MainService.listLibraries().get(0)));

        assertEquals(++beforeSize, MainService.listReaders().size());

        // test with non-unique username
        assertEquals(false, MainService.isUniqueUserName("scahyir"), "Username already exists!");


        // test with invalid value for get() method
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.listLibraries().get(15));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

    @Disabled
    @Test
    void testRemoveReader(){
        int beforeSize = MainService.listReaders().size();
        // removing a reader
        MainService.removeAccount(new User("Sefa", "Cahyir", "scahyir", "1234", MainService.listLibraries().get(0)));
        assertEquals(beforeSize - 1, MainService.listReaders().size());

        // removing a reader that doesn't exist
        beforeSize = MainService.listReaders().size();
        MainService.removeAccount(new User("wrong", "wrong", "wrong", "wrong", MainService.listLibraries().get(1)));
        assertEquals(beforeSize, MainService.listReaders().size());
    }

    @Disabled
    @Test
    void testEditReader(){
        // test with valid value for get() method
        MainService.listLibraries().get(0);

        // test with invalid value for get() method
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.listLibraries().get(15));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

    @Disabled
    @Test
    void testAddMaterial(){
        int bookCount = MainService.listMaterials().size();

        // successfully added a book
        Material newMat = new Material(MaterialType.BOOK, "testBook", Category.CLASSICS, new Date(201612),
                MainService.listAuthors().get(2), MainService.listPublishers().get(0), 111, "A1", "info", MainService.listLibraries().get(0));
        MainService.addMaterial(newMat);
        assertEquals(++bookCount, MainService.listMaterials().size());

        // no such publisher exists
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.listPublishers().get(15));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

    @Disabled
    @Test
    void testRemoveMaterial(){
        int bookCount = MainService.listMaterials().size();

        // successfully removed material
        MainService.removeMaterial("Amok");
        assertEquals(--bookCount, MainService.listMaterials().size());

        // no material found with this name
        bookCount = MainService.listMaterials().size();
        MainService.removeMaterial("noSuchBook");
        assertEquals(bookCount, MainService.listMaterials().size());
    }

    @Disabled
    @Test
    void testEditMaterial(){
        // material info edited
        MainService.editMaterialInfo(MainService.listMaterials().get(0), "testInfo1");

        // there's no material with this id
        Throwable exception = assertThrows(IndexOutOfBoundsException.class, () -> MainService.editMaterialInfo(MainService.listMaterials().get(15), "testInfo2"));
        assertEquals("Index must be less than list size!", exception.getMessage());
    }

    @Disabled
    @Test
    void testSearchMaterial(){
        Material returnMat;
        // material exists
        returnMat = MainService.searchByName("Amok");
        assertNotNull(returnMat);

        // material doesn't exist
        returnMat = null;
        returnMat = MainService.searchByName("noSuchBook");
        assertNull(returnMat);
    }

    @Disabled
    @Test
    void testSearchLoanedBook(){
        // tests if the searched material is loaned or not
        Material newMat = MainService.searchByName("Amok");
        assertEquals(false,newMat.getIsLoaned());
    }

    @Disabled
    @Test
    void testAddPublisher(){
        int pubCount = MainService.listPublishers().size();
        // adding new publisher
        MainService.addPublisher("testPublisher", "testingMaterial");
        assertEquals(++pubCount, MainService.listPublishers().size());

        // publisher already exists
        pubCount = MainService.listPublishers().size();
        MainService.addPublisher("National Geographic", "National geographic, magazines");
        assertEquals(pubCount, MainService.listPublishers().size());
    }

    @Disabled
    @Test
    void testSearchPublisher(){
        List<Material> booksOfPub = null;
        // a publisher with this name exists
        booksOfPub = MainService.searchByPublisher("National Geographic");
        assertNotNull(booksOfPub);
    }

    @Disabled
    @Test
    void testAddAuthor(){
        int authorCount = MainService.listAuthors().size();
        // adding a new author
        MainService.addAuthor("testName", "testSurname", "infoAboutAuthor");
        assertEquals(++authorCount, MainService.listAuthors().size());
        // adding an already existing author
        authorCount = MainService.listAuthors().size();
        MainService.addAuthor("Franz", "Kafka", "Franz Kafka was one of the most significant and influential fiction writers of the 20th century. Dark, absurdist, and existential, his stories and novels concern the struggles of troubled individuals to survive in an impersonal, bureaucratic world.");
        assertEquals(authorCount, MainService.listAuthors().size());
    }

    @Disabled
    @Test
    void testSearchAuthor(){
        List<Material> booksOfAuthor = null;
        // an author with this name exists
        booksOfAuthor = MainService.searchByAuthor("Franz");
        assertNotNull(booksOfAuthor);
    }

    //@Disabled
    @Test
    void testSearchCategory(){
        List<Material> classicBooks = null;
        // this category exists
        classicBooks = MainService.searchByCategory(5);
        assertNotNull(classicBooks);
    }

}
