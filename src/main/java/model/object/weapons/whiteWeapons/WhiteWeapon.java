package model.object.weapons.whiteWeapons;

import model.character.CharacterInterface;
import model.object.weapons.Weapon;
import model.object.weapons.WeaponInterface;


public class WhiteWeapon extends Weapon implements WeaponInterface {

    @Override
    public boolean use(CharacterInterface entity){
        System.out.println("arme blanche");
        return true;
    }

}
