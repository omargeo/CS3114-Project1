import student.TestCase;

/**
 * This class tests the SkipNode class
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.02
 */
public class SkipNodeTest extends TestCase {

    /**
     * A variable that holds the skipNode
     */
    private SkipNode<String, Rectangle> skipNode;
    
    /**
     * A variable that holds the value which is the key
     */
    private Rectangle rec;
    
    /**
     * Setup for the testing class
     */
    public void setUp() {
        rec = new Rectangle("A", 1, 2, 3, 4);
        skipNode = new SkipNode<String, Rectangle>("A", rec, 2);
    }
    
    /**
     * a method that tests the getKey method
     */
    public void testGetKey() {
        assertEquals("A", skipNode.getKey());
    }
    
    /**
     * a method that tests the getValue method
     */
    public void testGetValue() {
        assertEquals(rec, skipNode.getValue());
    }
    
    /**
     * a method that tests the toString method
     */
    public void testToString() {
        assertEquals("(A, 1, 2, 3, 4)", skipNode.toString());
    }
    
    /** 
     * a method that tests the getForward method
     */
    public void testGetForward() {
        assertNull(skipNode.getForward()[0]);
    }
    
    /**
     * a method that tests the getLevel method
     */
    public void testGetLevel() {
        assertEquals(2, skipNode.getLevel());
    }
}
