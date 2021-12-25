/** 
 On my honor:

 - I have not used source code obtained from another student,
 or any other unauthorized source, either modified or
 unmodified.

 - All source code and documentation used in my program is
 either my original work, or was derived by me from the
 source code published in the textbook for this course.

 - I have not discussed coding details about this project with
 anyone other than the instructor, ACM/UPE tutors, programming 
 partner (if allowed in this class), or the TAs assigned to 
 this course. I understand that I may discuss the concepts
 of this program with other students, and that another student
 may help me debug my program so long as neither of us writes
 anything during the discussion or modifies any computer file
 during the discussion. I have violated neither the spirit nor
 letter of this restriction.
*/

import java.io.ByteArrayOutputStream;
import java.io.PrintStream;
import student.TestCase;

/**
 * This class tests the SkipList behavior
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.19
 */
public class SkipListTest extends TestCase {
    /**
     * A variable that holds the skiplist
     */
    private SkipList<String, Integer> sl = new SkipList<String, Integer>();
    
    /**
     * A method that tests the dump method
     */
    public void testDump() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        //PrintStream originalOut = System.out;
        System.setOut(new PrintStream(outContent));
        sl.dump();
        assertEquals("SkipList dump:\nNode has depth 1, "
            + "Value (null)\nSkipList size is: 0\n", outContent.toString());
    }

    /** 
     * A method that tests the insert method
     */
    public void testInsert() {
        sl.insert("A", 2);
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sl.dump();
        assertEquals(String.format("SkipList dump:\nNode has depth %d, "
            + "Value (null)\nNode has depth %d, "
            + "Value (A, 2)\nSkipList size is: 1\n", 
            sl.getHead().getLevel(), 
            sl.getHead().getForward()[0].getLevel()), outContent.toString());
        assertEquals("(A, 2)", sl.remove("A").toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sl.dump();
        assertEquals(String.format("SkipList dump:\nNode has depth %d, "
            + "Value (null)\nSkipList size is: 0\n", 
            sl.getHead().getLevel()), outContent.toString());
        
        assertNull(sl.remove("z"));
    }
  
    /**
     * A method that tests the getLevel method
     */
    public void testGetLevel() {
        sl.insert("A", 2);
        assertNotNull(sl.getLevel());
        
        sl.insert("C", 4);
        sl.insert("D", 1);
        sl.insert("B", 3);
        sl.insert("C", 1);
        sl.dump();
        
        sl.remove("C");
        assertNotNull(sl.getLevel());
    }
    
    /**
     * tests the find method
     */
    public void testFind() {
        sl.insert("A", 3);
        sl.insert("C", 3);
        sl.insert("C", 1);
         
        sl.remove("C");
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        sl.remove("C");
        sl.remove("A");
        sl.dump();
        assertEquals(String.format("SkipList dump:\nNode has depth %d, "
            + "Value (null)\nSkipList size is: 0\n", 
            sl.getHead().getLevel()), outContent.toString());
        //assertNull(sl.find("D"));
    }
}
