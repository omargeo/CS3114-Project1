import java.io.File;
import java.io.FileNotFoundException;
import java.util.Scanner;

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

/**
 * The project runner class
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.20
 */
public class Rectangle1 {
    /**
     * A variable that holds the file
     */
    private static Scanner file;
    
    /**
     * A variable that holds the skipList
     */
    private static SkipList<String, Rectangle> skipList = 
        new SkipList<String, Rectangle>();
    
    /**
     * constructor of the class
     */
    public Rectangle1() {
        skipList = new SkipList<String, Rectangle>();
    }
    
    /**
     * The main method of the project
     * @param args the input file 
     */
    public static void main(String[] args) {
        ProjectRunner projectRunner = new ProjectRunner();
        try {
            file = new Scanner(new File(args[0]));
            while (file.hasNextLine()) {
                String com = file.nextLine();
                projectRunner.run(com);
            }
        }
        catch (FileNotFoundException e) {
            e.printStackTrace();
        }
    }
    
    /**
     * Getter method of the skipList
     * @return SkipList
     */
    public SkipList<String, Rectangle> getSkipList() {
        return skipList;
    }
}