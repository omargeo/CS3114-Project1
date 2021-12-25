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
import java.lang.reflect.Array;
import java.util.*;

/**
 * This class controls the behavior of SkipList
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.20
 * @param <K> Key
 * @param <V> Element
 */
public class SkipList<K extends Comparable<K>, V> {
    
    /**
     * A variable that holds the head
     */
    private SkipNode<K, V> head;
    
    /**
     * A variable that holds how many Objects are there
     */
    private int size;
    
    /**
     * A variable that holds how many levels are there
     */
    private int level;
    
    /**
     * A variable that holds the Random
     */
    static private Random ran = new Random();
    
    /**
     * Constructor of the class
     */
    public SkipList() {
        head = new SkipNode<K, V>(null, null, 1);
        size = 0;
        level = -1;
    }
   
    /**
     * A method that inserts the Object 
     * @param name genetic type which will store the name
     * @param values genetic type which will store the rectangle
     */
    public void insert(K name, V values) {
        //int newLevel = randomLevel(); // New node's level
        int newLevel = randomLevel();
        if (newLevel > level) { // If new node is deeper
            adjustHead(newLevel); // adjust the header
        }
        // Track end of level
        //SkipNode<K, V>[] update = new SkipNode[level + 1];
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipNode.class, head.getLevel() + 1);
        SkipNode<K, V> x = head; // Start at header node
        for (int i = level; i >= 0; i--) { // Find insert position
            while ((x.getForward()[i] != null) && 
                (x.getForward()[i].getKey().compareTo(name) < 0)) {
                x = x.getForward()[i];
            }
            update[i] = x; // Track end at level i
        }
        x = new SkipNode<K, V>(name, values, newLevel);
        x.setLevel(newLevel);
        for (int i = 0; i <= newLevel; i++) { // Splice into list
            x.getForward()[i] = update[i].getForward()[i]; // Who x points to
            update[i].getForward()[i] = x; // Who points to x
        }
        size++; // Increment dictionary size
    }
    
    /**
     * This method allows the head adjustment for the insertion process
     * @param newLevel is the new level to be adjusted to
     */
    private void adjustHead(int newLevel) {
        SkipNode<K, V> temp = head;
        head = new SkipNode<K, V>(null, null, newLevel);
        for (int i = 0; i <= level; i++) {
            head.getForward()[i] = temp.getForward()[i];
        }
        level = newLevel;
    }
   
    /**
     * this generates the new random level
     * @return a new randomly created level
     */
    int randomLevel() {
        int lev;
        for (lev = 0; Math.abs(
            ran.nextInt()) % 2 == 0; lev++) { // ran is random generator
            //; // Do nothing
        }
        return lev;
    }

    /**
     * A method that removes an Object based on the name
     * @param name the name of the rectangle
     * @return SkipNode that was removed
     */
    public SkipNode<K, V> remove(K name) {
        SkipNode<K, V> x = head; // Start at header node
        SkipNode[] update = (SkipNode[])Array.newInstance(
            SkipNode.class, head.getLevel() + 1);
        for (int i = level; i >= 0; i--) {
            x = head;
            while ((x.getForward()[i] != null) && 
                !(x.getForward()[i].getKey().equals(name))) {
                x = x.getForward()[i];
            }
            update[i] = x;
        }
        x = x.getForward()[0];
        if ((x != null)) { // && (x.getKey().equals(name))
            for (int i = 0; i < x.getForward().length; i++) {
                update[i].getForward()[i] = x.getForward()[i];
            }
            size--;
            return x;
        }
        return null;
        
        
    }

    /**
     * A method that finds a specific node
     * @param name to be found 
     * @return SkipNode if found but null if it is not available
     */
    public SkipNode<K, V> find(K name) {
        SkipNode<K, V> x = head; // Dummy header node
        for (int i = level; i >= 0; i--) { // For each level...
            while ((x.getForward()[i] != null) && 
                (x.getForward()[i].getKey().
                    compareTo(name) < 0)) { // go forward
                x = x.getForward()[i]; // Go one last step
            }
        }
        x = x.getForward()[0]; // Move to actual record, if it exists
        if ((x != null) && (x.getKey().compareTo(name) == 0)) {
            //return x.getValue(); // Got it
            return x;
        }
        else {
            return null; // Its not there
        }
    }
    
    /**
     * Shows all the nodes in the skipList
     */
    public void dump() {
        System.out.println("SkipList dump:");
        SkipNode<K, V> x = head;
        System.out.println("Node has depth " + 
            x.getLevel() + ", Value (null)"); //+ x.getValue().toString())
        while ((x.getForward()[0] != null)) { // go forward
            System.out.println("Node has depth " + 
                x.getForward()[0].getLevel() + 
                  ", Value " + x.getForward()[0].toString());
            x = x.getForward()[0];
        }
        System.out.print("SkipList size is: " + getSize() + "\n");
    }
    
    /**
     * A getter method for the head
     * @return the head
     */
    public SkipNode<K, V> getHead() {
        return head;
    }
    /**
     * A getter method for the size
     * @return the size
     */
    public int getSize() {
        return size;
    }

    /**
     * A getter method for the level
     * @return the level
     */
    public int getLevel() {
        return level;
    }
}