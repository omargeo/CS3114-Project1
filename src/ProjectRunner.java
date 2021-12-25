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
 * The projectRunner class of this project
 * @author Omar Elgeoushy (omarelgeoushy)
 * @version  2021.09.20
 */
public class ProjectRunner {
    
    /**
     * A variable that holds the rectangle
     */
    private Rectangle1 rectangle1 = new Rectangle1();
    /**
     * the method that runs the project
     * @param args is the file 
     */
    public void run(String args) {
        String com = args;
        com = com.trim();
        String[] list = com.split("\\s+");
        if (list[0].equals("dump")) {
            rectangle1.getSkipList().dump();
        }
        else if (list[0].equals("intersections")) {
            intersections();
        }
        else if (list[0].equals("insert")) {
            String name = list[1];
            int x = Integer.parseInt(list[2]);
            int y = Integer.parseInt(list[3]);
            int w = Integer.parseInt(list[4]);
            int h = Integer.parseInt(list[5]);
            if ((x >= 0 && x <= 1024) && (y >= 0 && y <= 1024) && 
                (w > 0 && w + x <= 1024) && (h > 0 && h + y <= 1024)) {
                Rectangle rec = new Rectangle(name, x, y, w, h);
                rectangle1.getSkipList().insert(name, rec);
                System.out.format("Rectangle inserted: "
                    + "(%s, %d, %d, %d, %d)\n", name, x, y, w, h);
            }
            else {
                System.out.format("Rectangle rejected: "
                    + "(%s, %d, %d, %d, %d)\n", name, x, y, w, h);
            }
        }
        else if (list[0].equals("remove")) {
            if (isNumeric(list[1])) {
                int x = Integer.parseInt(list[1]);
                int y = Integer.parseInt(list[2]);
                int w = Integer.parseInt(list[3]);
                int h = Integer.parseInt(list[4]);
                if (!((x >= 0 && x <= 1024) && (y >= 0 && y <= 1024) &&
                    (w > 0) && (h > 0))) {
                    System.out.format("Rectangle rejected: "
                        + "(%d, %d, %d, %d)\n", x, y, w, h);
                    return;
                }
                SkipNode<String, Rectangle> sk = remove(x, y, w, h);
                if (sk == null) {
                    System.out.format("Rectangle not found: "
                        + "(%d, %d, %d, %d)\n", x, y, w, h); 
                }
                else {
                    System.out.format("Rectangle removed: %s\n", 
                        sk.toString());
                }
            }
            else {
                String name = list[1];
                SkipNode<String, Rectangle> sk = 
                    rectangle1.getSkipList().remove(name);
                if (sk == null) {
                    System.out.format("Rectangle not removed: (%s)\n"
                        , name); 
                }
                else {
                    System.out.format("Rectangle removed: %s\n", 
                        sk.toString());
                }
            }
        }
        else if (list[0].equals("regionsearch")) {
            int x = Integer.parseInt(list[1]);
            int y = Integer.parseInt(list[2]);
            int w = Integer.parseInt(list[3]);
            int h = Integer.parseInt(list[4]);
            regionsearch(x, y, w, h);
        }
        else if (list[0].equals("search")) {
            String name = list[1];
            search(name);
        }
    }
    
    /**
     * Getter method for rectangle1
     * @return rectangle1
     */
    public Rectangle1 getRectangle1() {
        return rectangle1;
    }
    
    /**
     * a private method that checks if the string is a number or not
     * @param str the String to be checked
     * @return a boolean true if its a string false otherwise
     */
    public boolean isNumeric(String str) {
        try {
            Integer.parseInt(str);
            return true;
        }
        catch (NumberFormatException e) {
            return false;  
        }
    }
    
    /**
     * A method that checks for rectangles within a specific region
     * @param x coordinate 
     * @param y coordinate
     * @param w coordinate
     * @param h coordinate
     */
    public void regionsearch(int x, int y, int w, int h) {
        if (!((w > 0) && (h > 0) && 
            (x + w > 0 && x < 1024) && 
            (y + h > 0 && y < 1024))) {
            System.out.print(String.format(
                "Rectangle rejected: "
                + "(%d, %d, %d, %d)\n", x, y, w, h));
            return;
        }
        System.out.format("Rectangles intersecting region "
            + "(%d, %d, %d, %d):\n", x, y, w, h);
        SkipNode<String, Rectangle> skipNode = 
            rectangle1.getSkipList().getHead();
        while ((skipNode.getForward()[0] != null)) { // go forward
            if (recInt(skipNode.getForward()[0], x, y, w, h)) {
                System.out.println(skipNode.getForward()[0].toString());
            }
            skipNode = skipNode.getForward()[0];
        }
    }
  
    /**
     * A method that finds all the pairs of rectangles 
     * within the database that intersect
     */
    public void intersections() {
        System.out.println("Intersection pairs:");
        SkipNode<String, Rectangle> skipNode = 
            rectangle1.getSkipList().getHead();
        
        while ((skipNode.getForward()[0] != null)) {
            SkipNode<String, Rectangle> skipNode1 = 
                rectangle1.getSkipList().getHead();
            while ((skipNode1.getForward()[0] != null)) { // go forward
                int xCor = skipNode1.getForward()[0].getValue().getX();
                int yCor = skipNode1.getForward()[0].getValue().getY();
                int width = skipNode1.getForward()[0].getValue().getWidth();
                int height = skipNode1.getForward()[0].getValue().getHeight();
                
                if (skipNode1.getForward()[0] != skipNode.getForward()[0] 
                    && recInt(skipNode.getForward()[0], 
                    xCor, yCor, width, height)) {
                    System.out.format("%s | %s\n", 
                        skipNode.getForward()[0].toString(), 
                        skipNode1.getForward()[0].toString()); 
                }
                skipNode1 = skipNode1.getForward()[0];
            }
            skipNode = skipNode.getForward()[0];
        }
    }
    

    /**
     * Checks if the rectangles intersect
     * @param rec rectangle
     * @param x coordinate
     * @param y coordinate
     * @param w coordinate
     * @param h coordinate
     * @return SkipNode
     */
    private boolean 
        recInt(SkipNode<String, Rectangle> rec, int x, int y, int w, int h) {
        int xVar = rec.getValue().getX();
        int yVar = rec.getValue().getY();
        int wVar = rec.getValue().getWidth();
        int hVar = rec.getValue().getHeight();
        
        return x < xVar + wVar && y < yVar + hVar 
            && x + w > xVar && y + h > yVar;
    }
    
    /**
     * A method that removes an Object based on the coordinates
     * @param x coordinate 
     * @param y coordinate
     * @param w coordinate
     * @param h coordinate of the rectangle
     * @return the removed node of type SkipNode<String, Rectangle>
     */
    public SkipNode<String, Rectangle> remove(
        int x, int y, int w, int h) {
        SkipNode<String, Rectangle> skipNode = 
            rectangle1.getSkipList().getHead();
        //for (int i = 0; i < skipList.getSize(); i++) {
        while ((skipNode.getForward()[0] != null)) {
            if (skipNode.getForward()[0].getValue().getX() == x && 
                skipNode.getForward()[0].getValue().getY() == y &&
                skipNode.getForward()[0].getValue().getWidth() == w &&
                skipNode.getForward()[0].getValue().getHeight() == h) {
                return rectangle1.getSkipList().
                    remove(skipNode.getForward()[0].getKey());
            }
            skipNode = skipNode.getForward()[0];
        }
        return null;
    }
    
    /**
     * Finds all the rectangles with that name
     * @param name is the key that we are looking for
     */
    public void search(String name) {
        SkipNode<String, Rectangle> x = 
            rectangle1.getSkipList().getHead(); // Dummy header node
        if (rectangle1.getSkipList().find(name) == null) {
            System.out.format("Rectangle not found: %s\n", name);
        }
        else {
            System.out.print("Rectangles found:\n");
            while ((x.getForward()[0] != null)) { // go forward
                if ((x.getForward()[0].getKey().compareTo(name) == 0)) {
                    System.out.println(x.getForward()[0].toString());
                }
                x = x.getForward()[0];
            }
        }
    }
}
