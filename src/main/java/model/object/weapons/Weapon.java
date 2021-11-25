package model.object.weapons;

import model.character.CharacterInterface;
import model.character.Player;
import model.object.Object;
import view.ItemView;

public class Weapon extends Object implements WeaponInterface {

    int range=3;
    int damage;
    int durability;
    //Niveau a atteindre minimum pour trouver l'item
    int stageNecessary=0;


    /**
     * Pour un meilleur game play nous conseillons d'éviter d'avoir moins de 3 de portée.
     * Dans cette optique nous estimons que moins de 3 est une erreure et l'interdisons.
     * @param distance
     */
    public void range(int distance){
        if(range<3)
            range = 3;
        else
            range = distance;
    }
    public void damage (int damage){this.damage=damage;}
    public void stageNecessary(int stageNecessary){this.stageNecessary=stageNecessary;}
    public int getStageNecessary(){return stageNecessary; }
    public int getDamage(){return damage;}

    @Override
    public int getRange() {
        return range;
    }

    @Override
    public boolean use(CharacterInterface entity) {
        return false;
    }

    @Override
    public void description() {
        ItemView itemView = new ItemView(this);
        itemView.printWeapon(this);
    }

    @Override
    public void equip(Player player) {
        player.equipWeapon(this);
    }

}
