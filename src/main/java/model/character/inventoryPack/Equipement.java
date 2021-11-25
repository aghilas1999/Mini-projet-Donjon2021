package model.character.inventoryPack;


import model.object.armors.Armor;
import model.object.weapons.Weapon;


public class Equipement {

    //Equipement équipé
    Armor armor;
    Weapon weapon;

    public Equipement(Weapon weapon) {
        this.armor = null;
        this.weapon = weapon;
    }

    public Weapon getWeapon() {
        return weapon;
    }

    public void setWeapon(Weapon newWeapon, Bag bag) {
        bag.add(weapon);
        weapon = newWeapon;
        bag.remove(newWeapon);
    }

    public String getNameAmor() {
        return armor.getName();
    }

    public int getResistance() {
        return armor.getResistance();
    }

    public String getNameWeapon() {
        return weapon.getName();
    }

    public int getRange() {
        return weapon.getRange();
    }

    public int getDamage() {
        return weapon.getDamage();
    }

    public void setArmor(Armor armor) {
        this.armor = armor;
    }

    public void setArmor(Armor newArmor, Bag bag) {
        bag.add(armor);
        armor = newArmor;
        bag.remove(newArmor);
    }
}
