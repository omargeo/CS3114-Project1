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
//import java.util.ArrayList;
//import java.util.List;
import student.TestCase;

/**
 * This class tests the behavior of projectRunner class
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.20
 */
public class ProjectRunnerTest extends TestCase {

    /**
     * A variable that holds the rectangle
     */
    private ProjectRunner projectRunner;
    
    /**
     * setUp method for the class
     */
    public void setUp() {
        projectRunner = new ProjectRunner();
    }
    
    /**
     * tests isNumeric
     */
    public void testIsNumeric() {
        assertFalse(projectRunner.isNumeric("a"));
        assertTrue(projectRunner.isNumeric("1"));
    }
    
    /** 
     * tests the regionSearch
     */
    public void testRegionSearch() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("insert rec1 12 18 5 5");
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("regionsearch 10 10 10 10");
        assertEquals("Rectangles intersecting region "
            + "(10, 10, 10, 10):\n(rec1, 12, 18, 5, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("insert rec2 18 18 5 5");
        projectRunner.run("regionsearch 10 10 10 10");
        assertEquals("Rectangle inserted: (rec2, 18, 18, 5, 5)\n"
            + "Rectangles intersecting region (10, 10, 10, 10):"
            + "\n(rec1, 12, 18, 5, 5)\n"
            + "(rec2, 18, 18, 5, 5)\n", outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("insert rec3 5 5 10 10");
        projectRunner.run("regionsearch 10 10 10 10");
        assertEquals("Rectangle inserted: (rec3, 5, 5, 10, 10)\n"
            + "Rectangles intersecting region (10, 10, 10, 10):\n"
            + "(rec1, 12, 18, 5, 5)\n"
            + "(rec2, 18, 18, 5, 5)\n(rec3, 5, 5, 10, 10)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("insert rec4 18 5 10 10");
        projectRunner.run("regionsearch 10 10 10 10");
        assertEquals("Rectangle inserted: (rec4, 18, 5, 10, 10)\n"
            + "Rectangles intersecting region (10, 10, 10, 10):\n"
            + "(rec1, 12, 18, 5, 5)\n"
            + "(rec2, 18, 18, 5, 5)\n(rec3, 5, 5, 10, 10)"
            + "\n(rec4, 18, 5, 10, 10)\n", outContent.toString());
    }
   
    /**
     * tests the regionSearch
     */
    public void testRegionSearch1() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.regionsearch(-10, 10, 10, 10);
        assertEquals("Rectangle rejected: (-10, 10, 10, 10)\n",
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.regionsearch(10, -10, 10, 10);
        assertEquals("Rectangle rejected: (10, -10, 10, 10)\n",
            outContent.toString());
    
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.regionsearch(10, 10, -10, 10);
        assertEquals("Rectangle rejected: (10, 10, -10, 10)\n",
            outContent.toString());
    
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.regionsearch(10, 10, 10, -10);
        assertEquals("Rectangle rejected: (10, 10, 10, -10)\n",
            outContent.toString());
    }
    
    /**
     * test method for search
     */
    public void testSearch() {
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.search("rec");
        assertEquals("Rectangle not found: rec\n", outContent.toString());
        
        projectRunner.run("insert rec1 18 18 5 5");
        
        projectRunner.run("insert rec2 18 18 5 5");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        //projectRunner.search("rec2");
        projectRunner.run("search rec2");
        assertEquals("Rectangles found:\n(rec2, 18, 18, 5, 5)\n", 
            outContent.toString());
        
        projectRunner.run("insert rec2 10 7 3 4");
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("search rec2");
        assertEquals("Rectangles found:\n(rec2, 10, 7, 3, 4)"
            + "\n(rec2, 18, 18, 5, 5)\n", 
            outContent.toString());
    }
   
    /**
     * test method for remove
     */
    public void testRemove() {
        projectRunner.run("insert rec1 12 18 5 4");
        projectRunner.run("insert rec2 12 18 4 5");
        projectRunner.run("insert rec3 12 17 5 5");
        projectRunner.run("insert rec4 11 18 5 5");
        projectRunner.run("insert rec5 12 18 5 5");
        
        
        projectRunner.run("dump");
        
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove 12 18 5 5");
        assertEquals("Rectangle removed: (rec5, 12, 18, 5, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove 11 18 5 5");
        assertEquals("Rectangle removed: (rec4, 11, 18, 5, 5)\n",
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove 12 17 5 5");
        assertEquals("Rectangle removed: (rec3, 12, 17, 5, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove 12 18 4 5");
        assertEquals("Rectangle removed: (rec2, 12, 18, 4, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove 12 18 5 4");
        assertEquals("Rectangle removed: (rec1, 12, 18, 5, 4)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove 12 18 5 4");
        assertEquals("Rectangle not found: (12, 18, 5, 4)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("dump");
        assertEquals(String.format("SkipList dump:"
            + "\nNode has depth %d, Value (null)\n"
            + "SkipList size is: 0\n", projectRunner.getRectangle1().
            getSkipList().getLevel()), 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        assertNull(projectRunner.remove(-1, 0, 1, 1));
    }
    
    /**
     * tests insert method
     */
    public void testInsert() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("dump");
        assertEquals("SkipList dump:\nNode has depth 1, "
            + "Value (null)\nSkipList size is: 0\n", outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run(" \n insert rec1  0  0  5  5");
        assertEquals("Rectangle inserted: (rec1, 0, 0, 5, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run(" \n insert rec1  -1  0  5  5");
        assertEquals("Rectangle rejected: (rec1, -1, 0, 5, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run(" \n insert rec1  0  -1  5  5");
        assertEquals("Rectangle rejected: (rec1, 0, -1, 5, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run(" \n insert rec1  0  0  0  5");
        assertEquals("Rectangle rejected: (rec1, 0, 0, 0, 5)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run(" \n insert rec1  0  0  5  0");
        assertEquals("Rectangle rejected: (rec1, 0, 0, 5, 0)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove rec1");
        assertEquals("Rectangle removed: (rec1, 0, 0, 5, 5)\n", 
            outContent.toString());
    }
    
    /**
     * tests remove method
     */
    public void testRemove1() {
        projectRunner.run("insert rec1 1 0 2 4");
        projectRunner.run("insert rec2 1 0 2 4");
        projectRunner.run("insert rec3 2 5 123 6");
        
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove rec3");
        assertEquals("Rectangle removed: (rec3, 2, 5, 123, 6)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("remove rec1");
        assertEquals("Rectangle removed: (rec1, 1, 0, 2, 4)\n", 
            outContent.toString());
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("dump");
    }
    
    /**
     * Tests the intersection method
     */
    public void testIntersection() {
        ByteArrayOutputStream outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        
        projectRunner.run("insert rec1 0 0 5 4");
        projectRunner.run("insert rec2 2 3 6 3");
        projectRunner.run("insert rec3 8 3 5 2");
        projectRunner.run("insert rec4 10 4 5 2");
        
        outContent = new ByteArrayOutputStream();
        System.setOut(new PrintStream(outContent));
        projectRunner.run("intersections");
        assertEquals("Intersection pairs:\n"
            + "(rec1, 0, 0, 5, 4) | (rec2, 2, 3, 6, 3)\n"
            + "(rec2, 2, 3, 6, 3) | (rec1, 0, 0, 5, 4)\n"
            + "(rec3, 8, 3, 5, 2) | (rec4, 10, 4, 5, 2)\n"
            + "(rec4, 10, 4, 5, 2) | (rec3, 8, 3, 5, 2)\n", 
            outContent.toString());
    }
}
