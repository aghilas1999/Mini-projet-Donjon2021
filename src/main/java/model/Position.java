package model;

public class Position {

    private double x;
    private double y;

    public Position(double x, double y) {
        this.x = x;
        this.y = y;
    }

    public Position top(){
        return new Position(x,y+1);
    }
    public Position bot(){
        return new Position(x,y-1);
    }
    public Position left(){
        return new Position(x-1,y);
    }
    public Position right(){
        return new Position(x+1,y);
    }

    public int getX() {
        return (int) x;
    }
    public int getY() {
        return (int) y;
    }

    /**
     * voir ci necessaire?
     * @param position
     * @return
     */
    public boolean equals(Position position){
        return (x == position.getX()) && (y == position.getY());
    }


    public void print() {
        System.out.println("x="+x+" y="+y);
    }

    //Obtenir une distance
    public int getDistance(Position position2){
        return Math.abs(getX()-position2.getX())+Math.abs(position2.getY()-getY());
    }

}
