package model.character.inventoryPack;

import controller.KeyBoardController;
import model.object.Object;
import model.object.Rocket;
import model.object.armors.Armor;
import model.object.weapons.Weapon;
import model.object.weapons.whiteWeapons.Pickaxe;
import view.InventoryView;

import java.util.ArrayList;

public class Inventory {

    Bag bag;
    Equipement equipement;

    public Inventory(Bag bag){
        this.bag = bag;
        this.equipement = new Equipement(new Pickaxe());
    }


    public void print(KeyBoardController keyBoardController){
        InventoryView view = new InventoryView(getBagArrayList(), keyBoardController, equipement);
        view.print();
    }


    public Weapon getWeapon() {
        return equipement.getWeapon();
    }

    public void equipWeapon(Weapon weapon){
        equipement.setWeapon(weapon, bag);
    }

    public void equipArmor(Armor armor) {
        equipement.setArmor(armor, bag);
    }

    //======================= Bag =================================

    public ArrayList<Object> getBagArrayList(){
        return bag.getArrayList();
    }

    public Bag getBag() {
        return bag;
    }

    public void dropBag(Object object){
        bag.drop(object);
    }

    public void addAll(ArrayList<Object> bagArrayList) {
        bag.addAll(bagArrayList);
    }

    public void setArmor(Armor armor) {
        equipement.setArmor(armor);
    }

    public int nbRocket() {
        Rocket rocket;
        for (Object object : bag.getArrayList()) {
            if (object.getName() == "Rocket") {
                rocket = (Rocket) object;
                return rocket.getNumber();
            }
        }
        return 0;
    }
}
