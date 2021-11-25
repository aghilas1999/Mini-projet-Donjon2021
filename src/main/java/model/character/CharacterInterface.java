package model.character;

import model.Position;
import model.object.Object;
import model.map.Map;

import java.util.ArrayList;

public interface CharacterInterface {

    //methodes accessible de l'exterieur
    ArrayList<Object> OBJECTS = new ArrayList<>();


    void setSpawn(Map map);

    boolean isDead();

     boolean isOnExit(Position exit);
     void takeDamage(int damage);
     int getHeal();

    void resurrect();
}
