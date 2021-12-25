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
 * 
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.08.26
 */
public class Rectangle {
    /**
     * A variable that holds the name of the rectangle
     */
    private String name;
    
    /**
     * A variable that holds the x coordinate of the rectangle
     */
    private int x;
    
    /**
     * A variable that holds the y coordinate of the rectangle
     */
    private int y;
    
    /**
     * A variable that holds the width coordinate of the rectangle
     */
    private int w;
    
    /**
     * A variable that holds the height coordinate of the rectangle
     */
    private int h;
    
    /**
     * Class Constructor 
     * @param name the rectangle name
     * @param x coordinate
     * @param y coordinate
     * @param w width
     * @param h height
     */
    public Rectangle(String name, int x, int y, int w, int h) {
        this.name = name;
        this.x = x;
        this.y = y;
        this.w = w;
        this.h = h;
    }
    
    /**
     * A getter method for the name
     * @return the name of the rectangle
     */
    public String getName() {
        return name;
    }
    
    /**
     * A getter method for the x coordinate 
     * @return the x coordinate of the rectangle
     */
    public int getX() {
        return x;
    }
    
    /**
     * A getter method for the y coordinate 
     * @return the y coordinate of the rectangle
     */
    public int getY() {
        return y;
    }
    
    /**
     * A getter method for the width coordinate 
     * @return the w coordinate of the rectangle
     */
    public int getWidth() {
        return w;
    }
    
    /**
     * A getter method for the height coordinate 
     * @return the h coordinate of the rectangle
     */
    public int getHeight() {
        return h;
    }
   
    /**
     * A toString method for the rectangle
     * @return a String of the Rectangle
     */
    public String toString() {
        String string = String.format("%d, %d, %d, %d", getX(), 
            getY(), getWidth(), getHeight());
        return string;
    }
}
