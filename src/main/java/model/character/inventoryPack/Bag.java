package model.character.inventoryPack;

import model.object.Object;
import model.object.Rocket;
import model.object.weapons.guns.LazerGun;

import java.util.ArrayList;

public class Bag {
    //Sac pour le joueur et loot pour les mobs

    ArrayList<Object> bag;

    // Constructor
    public Bag(){
        bag = new ArrayList<>();
        Object object = new LazerGun();
        bag.add(object);
    }

    public ArrayList<Object> getArrayList() {
        return bag;
    }

    // Une methode pour ajouter tout les object.
    public void addAll(ArrayList<Object> objects) {
        for(Object object : objects)
            add(object);
    }

    public void add(Object object){
        if(object.isStackable() && containOne(object.getName())) {
            if (object.getName() == "Rocket")
                optimizeRocket((Rocket) object);
        }
        else {
                bag.add(object);
            }
    }

    private boolean containOne(String name) {
        for (Object object : bag)
            if (object.getName() == name)
                return true;
        return false;
    }

    private void optimizeRocket( Rocket rocket) {
        for(Object object : bag) {
            if (object.getName() == "Rocket")
                stackRocket((Rocket) object, rocket);
        }
    }

    private void stackRocket(Rocket item, Rocket rocket) {
        item.stack(rocket);
    }

    public void drop(Object object) {
        if(object.getName() == "Rocket") {
            System.out.println("Is it true that getting stuck here is a much better option than bear the rocket load");
        }
        else
        bag.remove(object);
    }

    public void remove(Object object) {
        bag.remove(object);
    }
}
