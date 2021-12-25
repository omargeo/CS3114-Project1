import student.TestCase;

/**
 * This class test the functionality of the Rectangle class
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.02
 */
public class RectangleTest extends TestCase {
    /**
     * A variable that holds the rectangle
     */
    private Rectangle rec;
    
    /**
     * Setup for the class which runs before every test case
     */
    public void setUp() {
        rec = new Rectangle("a", 2, 3, 4, 5);
    }
    
    /**
     * This method tests the getName 
     */
    public void testGetName() {
        assertEquals("a", rec.getName());
    }
    
    /**
     * This method tests the getX 
     */
    public void testGetX() {
        assertEquals(2, rec.getX());
    }
    
    /**
     * This method tests the getY 
     */
    public void testGetY() {
        assertEquals(3, rec.getY());
    }
    
    /**
     * This method tests the getWidth 
     */
    public void testGetWidth() {
        assertEquals(4, rec.getWidth());
    }
    
    /**
     * This method tests the getHeight 
     */
    public void testGetHeight() {
        assertEquals(5, rec.getHeight());
    }
    
    /**
     * This method tests the toString 
     */
    public void testToString() {
        assertEquals("2, 3, 4, 5", rec.toString());
    }
} 
