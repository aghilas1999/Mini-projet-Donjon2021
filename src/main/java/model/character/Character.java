package model.character;

import model.Position;
import controller.actions.Actions;
import model.character.inventoryPack.Bag;
import model.character.inventoryPack.Inventory;
import model.object.Object;
import model.object.armors.Armor;
import model.object.weapons.Weapon;
import model.map.Chest;
import model.map.Map;

import java.util.ArrayList;

abstract public class Character implements CharacterInterface {

    public Inventory inventory;
//    public Bag bag;
//    public Equipement equipement = new Equipement(null, null);

    public Health health = new Health();
    //for effect

    public Actions actions;

    public Position position;
    public Position oldPosition;

    public Position getPosition() {
        return position;
    };
    public Position getoldPosition() {
        return oldPosition;
    };

    public Character() {
        inventory = new Inventory(new Bag());
    }

    /**
     * Retourne vraie si une cible est à distance de l'entité
     *
     * @param posEntity
     * @return
     */
    public abstract boolean playerIsInRange(Position posEntity);

    public void setArmor(Armor armor) {
        inventory.setArmor(armor);

    }


    @Override
    public void takeDamage(int damage) {
        health.remove(damage);
    }

    @Override
    public int getHeal() {
        return health.getActual();
    }

    @Override
    public void resurrect() {
        health.resurrect();
    }

    public Weapon getWeapon() {
        return inventory.getWeapon();
    }
    public int getDamage() {
        return getWeapon().getDamage();
    }
    public ArrayList<Object> getBagArrayList(){
        return inventory.getBagArrayList();
    }
    public Bag getBag(){
        return inventory.getBag();
    }

   public boolean isNotPositionOfChest(Position position , Map map){
        for(Chest chest:map.getChests())
            if(position.equals(chest.getPosition()))
                return false;
       return true;
   }

    public void equipWeapon(Weapon weapon){
        inventory.equipWeapon(weapon);
    }

    public void dropBag(Object object){
        inventory.dropBag(object);
    }

    public Inventory getInventory(){
        return inventory;
    }

    public void equipAmor(Armor armor) {
        inventory.equipArmor(armor);
    }

}
