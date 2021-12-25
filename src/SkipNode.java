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
 * This class is associated with controlling the SkipNode
 * 
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.02
 * @param <K> the key
 * @param <V> the value
 */
public class SkipNode<K extends Comparable<K>, V> {
    /**
     * A variable that holds the key
     */
    private K key;
    
    /**
     * A variable that holds the value
     */
    private V value;
    
    /**
     * A variable that holds the level
     */
    private int level;
    
    /**
     * A variable that holds the forward
     */
    private SkipNode<K, V>[] forward;
    
    /**
     * The constructor of the class
     * @param key to be inserted
     * @param elem to be inserted
     * @param level to be used
     */
    @SuppressWarnings("unchecked")
    public SkipNode(K key, V elem, int level) {
        //rec = new KVPair<K, E>(key, elem);
        this.key = key;
        this.value = elem;
        this.level = level;
        forward = new SkipNode[level + 1];
        for (int i = 0; i < level; i++) {
            forward[i] = null;
        }
    }

    /**
     * A getter method for the key
     * @return the key
     */
    public K getKey() {
        return key;
    }

    /**
     * A getter method for the value
     * @return the value
     */
    public V getValue() {
        return value;
    }
   
    /**
     * A method that switches the value to string
     * @return String 
     */
    public String toString() {
        String string = "(" + getKey() + ", " + getValue() + ")";
        return string;
    }
    
    /**
     * A method that returns the forward variable
     * @return SkipNode<K, V>[]
     */
    public SkipNode<K, V>[] getForward() {
        return forward;
    }

    /**
     * A method that returns the level
     * @return the level
     */
    public int getLevel() {
        return level;
    }

    /**
     * A setter method for the level variable
     * @param level the level to set
     */
    public void setLevel(int level) {
        this.level = level;
    }
}