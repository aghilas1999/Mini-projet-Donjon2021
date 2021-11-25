package model.map.mapManagement;

import model.Position;
import java.util.ArrayList;

public class Wall {


    public Position left;
    public Position right;

    public Wall(Position left, Position right) {
        this.left = left;
        this.right = right;
    }


    /**
     * Build a new wall with Map's constraints
     * - Prend en compte l'anti-backtraking
     * - Prend en compte les limites de modélisation de la map
     * @return
     */
    public Wall nextTop(int minLength, int maxLength,int maxWidthMap, int minWidth) {

        boolean btop   =   (right.top().getY()   <= maxWidthMap)  && ! right.top().equals(left) ;
        boolean bbot   = !((right.bot().getY() < minWidth) && (right.getX()<minLength)) && ! right.bot().equals(left);
        boolean bright =   (right.right().getX() <= maxLength)  && ! right.right().equals(left) ;

        ArrayList<String> directions = new ArrayList<>();
        if(bbot)
            directions.add("Bot");
        if(btop)
            directions.add("Top");
        if(bright)
            directions.add("Right");
        return new Wall(right,randomNextPos(directions));
    }

    /**
     * Build a new wall with Map's constraints
     * - Prend en compte l'anti-backtraking
     * - Prend en compte les limites de modélisation de la map
     * @return
     */
    public Wall nextBot(int minLength, int maxLength,int maxWidthMap, int minWidth) {

        // wall( l, r(x,y+1))@to do à verrifier
        boolean btop   =  !((right.top().getY()   >- minWidth)&&(right.top().getX()<minLength))   && ! right.top().equals(left);
        boolean bbot   =  (right.bot().getY()   >= -maxWidthMap) && ! right.bot().equals(left);
        boolean bright =  (right.right().getX() <=  maxLength)   && ! right.right().equals(left);

        ArrayList<String> directions = new ArrayList<>();
        if(bbot)
            directions.add("Bot");
        if(btop)
            directions.add("Top");
        if(bright)
            directions.add("Right");
        return new Wall(right,randomNextPos(directions));
    }

    //@todo corriger bug :
    /*Exception in thread "main" java.lang.IndexOutOfBoundsException: Index 0 out of bounds for length 0
	at java.base/jdk.internal.util.Preconditions.outOfBounds(Preconditions.java:64)
	at java.base/jdk.internal.util.Preconditions.outOfBoundsCheckIndex(Preconditions.java:70)
	at java.base/jdk.internal.util.Preconditions.checkIndex(Preconditions.java:248)
	at java.base/java.util.Objects.checkIndex(Objects.java:372)
	at java.base/java.util.ArrayList.get(ArrayList.java:458)
	at model.map.mapManagement.Wall.randomNextPos(Wall.java:76)*/
    /**
     * Permite to get a new position for build a new wall
     * @return nextPosition
     */
    private Position randomNextPos(ArrayList<String> directions) {
        String direction = directions.get((int) (Math.random() * directions.size()));
        return stringToPos(direction);
    }

    private Position stringToPos(String direction){
        if(direction == "Bot")
                return right.bot();
        if(direction == "Top")
                return right.top();
        if(direction == "Right")
                return right.right();
        if(direction == "Left")
            return right.left();
        System.out.println("Erreur de direction:"+direction);
        return null;
    }


    /**
     * Affiche les coordonnées du mur
     */
    public void print() {
        System.out.println("Mur{");
        left.print();
        right.print();
        System.out.println("}");
    }

    public Wall top() {
        return new Wall(right,right.top());
    }

    public Wall bot() {
        return new Wall(right,right.bot());
    }
}
