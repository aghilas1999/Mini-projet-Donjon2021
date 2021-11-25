package model.map.mapManagement;

import model.Position;
import model.character.Player;
import view.PlanView;

import java.util.ArrayList;
import java.util.HashMap;

public class Plan {
    public int length, width;
    public Position lastPostionWalkable = new Position(0, 0);
    public HashMap<String, Integer> plan = new HashMap<>();
    ArrayList<Wall> walls;

    public Plan(int maxWidth, int maxLenght, ArrayList<Wall> walls) {
        width = maxWidth;
        length = maxLenght;
        this.walls = walls;
        build();
    }

    /**
     * Construit un plan tel que -1:wall, null:ina, 1:parcouru, traité
     */
    private void build() {

        ArrayList<Position> oldPositions = new ArrayList<>();
        Position actual = new Position(1, 0);
        while (isFinish()) {
            // print();
            // actual.print();
            if (isWall(actual)) {
                plan.put(key(actual.getX(), actual.getY()), -1);
                actual = oldPositions.get(oldPositions.size() - 1);
                oldPositions.remove(oldPositions.size() - 1);
            }
            //si la position n'est pas un mur
            else {
                //si on n'est jamais passé par là on marque le passage comme accessible déjà vue
                if (!plan.containsKey(key(actual.getX(), actual.getY()))) {
                    plan.put(key(actual.getX(), actual.getY()), 1);
                    //  oldPositions.add(actual);
                }
                if (get(actual.getX(), actual.getY()) == 1) {
                    //chercher un autre chemin
                    if (!existAnOtherPath(actual.getX(), actual.getY())) {
                        //si plus de chemin ( ==0 ou == -1 ou ==2)
                        plan.put(key(actual.getX(), actual.getY()), 2);
//                        if (lastPostionWalkable.getY() < actual.getY())
//                            lastPostionWalkable = actual;
                        if (oldPositions.size() > 0) {
                            actual = oldPositions.get(oldPositions.size() - 1);
                            oldPositions.remove(oldPositions.size() - 1);
                        }
                    } else {
                        oldPositions.add(actual);
                        actual = getNewPath(actual.getX(), actual.getY());
                    }
                }
                if (plan.containsKey(key(actual.getX(), actual.getY())))
                    if ((get(actual.getX(), actual.getY()) == 2) && oldPositions.size() > 0) {
                        actual = oldPositions.get(oldPositions.size() - 1);
                        oldPositions.remove(oldPositions.size() - 1);
                    }
            }

        }
    }

    /**
     * Retourne vrai s'il existe un autre chemin
     *
     * @param x:x
     * @param y:y
     * @return boolean
     */
    private boolean existAnOtherPath(int x, int y) {
        return (getExistAnOtherPath(x, y).size() != 0);
    }

    /**
     * Retourne vrai si l'on a parcouru toute la map.
     *
     * @return boolean
     */
    private boolean isFinish() {
        if (null == get(1, 0))
            return true;
        return ((get(1, 0) != 2));
    }

    /**
     * Retourne un chemin accessible si disponible ou retourne la position actual
     *
     * @param x:x
     * @param y:y
     * @return Position
     */
    private Position getNewPath(int x, int y) {
        ArrayList<String> ohterPath = getExistAnOtherPath(x, y);
        if (ohterPath.contains("top"))
            return new Position(x, y + 1);
        if (ohterPath.contains("bot"))
            return new Position(x, y - 1);
        if (ohterPath.contains("right"))
            return new Position(x + 1, y);
        if (ohterPath.contains("left"))
            return new Position(x - 1, y);
        return new Position(x, y);
    }

    /**
     * Retourne un tableau de direction possible
     *
     * @param x:x
     * @param y:y
     * @return Array<String> pathsPossible
     */
    private ArrayList<String> getExistAnOtherPath(int x, int y) {
        ArrayList<String> otherPath = new ArrayList<>();
        if (plan.containsKey(key(x, (y + 1)))) {
            if (plan.get(key(x, (y + 1))) == 0)
                otherPath.add("top");
        } else otherPath.add("top");

        if (plan.containsKey(key(x, (y - 1)))) {
            if (plan.get(key(x, (y - 1))) == 0)
                otherPath.add("bot");
        } else otherPath.add("bot");

        if (plan.containsKey(key((x + 1), y))) {
            if (plan.get(key((x + 1), y)) == 0)
                otherPath.add("right");
        } else otherPath.add("right");

        if (plan.containsKey(key((x - 1), y))) {
            if (plan.get(key((x - 1), y)) == 0)
                otherPath.add("left");
        } else otherPath.add(("left"));
        return otherPath;
    }

    /**
     * Ici le choix est fait de simplifier les déplacements à des positions aux coordonnées entières.
     * Dans le cas ou l'on peu se déplacer sur des valeurs flotante il faudra addapter le code.
     *
     * @param position:position
     * @return boolean
     */
    public boolean isWall(Position position) {
        for (Wall wall : walls) {
            //wall.print();
            // System.out.println("first:"+(wall.right.equals(position))+" second:"+(wall.left.equals(position)));
            if (wall.right.equals(position) || wall.left.equals(position))
                return true;
        }
        return false;
    }

    public Integer get(int x, int y) {
        return plan.get(key(x, y));
    }

    public String key(int x, int y) {
        return x + ":" + y;
    }

    public int getLenghtPlan(){
        return getExit().getX();
    }

    /**
     * Créer une sortie
     * @return position de la sortie
     */
    public Position getExit(){
        boolean travel;
        Position exit = null;
        if ((int) Math.random() * (2) == 1)
            travel = true;
        else travel =false;

        for(int x = length; x>0; x--){
            if(travel) {
                exit = travelBotToTop(x);
            }
            else
                exit = travelTopToBot(x);
            if (exit != null)
                return exit;
        }

        return  null;
    }

    private Position travelBotToTop(int x){
        System.out.println("width:"+ width);
        for(int y=width; y>-width; y--){

            if(plan.containsKey(key(x,y)))
                if(plan.get(key(x,y))==2)
                    return new Position(x,y);
        }
        return null;
    }

    private Position travelTopToBot(int x){
        for(int y=-width; y<width+1; y++){
            if(plan.containsKey(key(x,y)))
                if(plan.get(key(x,y))==2)
                    return new Position(x,y);
        }
        return null;
    }

    /**
     * test si la position donnée, existe et accèssible =2
     * @param x
     * @param y
     * @return is Walkable
     */
    public boolean spawnable(int x, int y) {
      if(plan.containsKey(key(x,y))){

            return (plan.get(key(x,y)) == 2);
        }
        return false;
    }


    //====== Graphisme ? à voir si à décaller dans view

    /**
     * Affichage valeur des case du plan : debugueur
     */
    public void print() {
        PlanView planView = new PlanView(this);
        planView.print();
    }

    /**
     * Affiche un dessin map
     */
    public void drawing(Player player) {
        PlanView planView = new PlanView(this);
        planView.drawing(player);
    }

    //========================== methode for View ===============

    public int getWidth() {
        return  width;
    }

    public int getLenght() {
        return length;
    }


    public HashMap<String, Integer> getPlan() {
        return plan;
    }

    public void put(String key, int value){
        plan.put(key,value);
    }
}
