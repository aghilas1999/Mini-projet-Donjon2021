package model.object.weapons.guns;

import model.character.CharacterInterface;
import model.object.weapons.Weapon;


public class Gun extends Weapon {
    int capacityMunition;
    int munition;

    public void reload(){
        //@TODO prendre en compte les munitions qui sont dans le sac
        if(munition<capacityMunition)
            munition =capacityMunition;
        else System.out.println("l'arme est déjà chargée");
    }
    public boolean shoot(CharacterInterface entity ){
        if( canShoot()) {
            munition = munition - 1;
            entity.takeDamage(getDamage());
            return true;
        }
        return false;

    }
    public boolean canShoot(){
        return munition!=0;
    }

    public int getMunition(){
        return munition;
    }
}


