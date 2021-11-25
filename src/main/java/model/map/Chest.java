package model.map;

import model.Position;
import model.object.Object;

import java.util.ArrayList;

public class Chest{
    ArrayList<Object> loots;
    Position position;

    public Chest(Position position){
       this.position=position;
        loots = new ArrayList<>();

    }

    public void add(Object loot) {
        loots.add( loot);
    }

    public Position getPosition() {
        return position;
    }

    public ArrayList<Object> getItem() {
        return loots;
    }


}
