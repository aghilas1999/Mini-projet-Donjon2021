package model.object.armors;

import model.character.Player;
import model.object.Object;

public class Armor extends Object {

    int resistance = 0;
    int stageNecessary = 0;
    public Armor(){

    }

    @Override
    public void description() {

    }

    @Override
    public void equip(Player player) {
        player.equipAmor(this);
    }
    public int getResistance(){
        return resistance;
    }


    public int getStageNecessary() {
        return stageNecessary;
    }
}
