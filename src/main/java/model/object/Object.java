package model.object;

import model.character.Player;

abstract public class Object {

    String name;
    Boolean stackable = false;
    public Object(){

    }



    public String getName() {
        return name;
    }

    public void setName(String name){
        this.name = name;
    }

    public abstract void description();

    public abstract void equip(Player player);


    public boolean isStackable(){
        return stackable;
    }
}
