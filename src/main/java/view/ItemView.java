package view;

import model.object.Object;
import model.object.Rocket;
import model.object.armors.Armor;
import model.object.weapons.Weapon;

public class ItemView {

    Object object;
    Weapon weapon;
    Armor armor;
    String descirption = "";
    public ItemView(Object object){
        this.object = object;
    }


    public void printDescription(){
        System.out.println("---------------- ↓ Description: "+ object.getName()+" ↓ ----------------");
        System.out.println(descirption);
        System.out.println("---------------- ↑ Description: "+ object.getName()+" ↑ ----------------");
    }

    public void printWeapon(Weapon weapon){
        this.weapon = weapon;
        buildDescription("Damage:",  Integer.toString(weapon.getDamage()));
        buildDescription("Range:",  Integer.toString(weapon.getRange()));
        buildDescription("Stage required:",  Integer.toString(weapon.getStageNecessary()));
        printDescription();
    }

    private void buildDescription(String specialty, String value ){
        descirption = descirption+ specialty+value +"\n";
    }

    public void printRocket(Rocket rocket){
        System.out.println("Rocket : "+rocket.getNumber());
    }

    public void printArmor(Armor rocket){
        this.armor = armor;
        buildDescription("Resistance:",  Integer.toString(armor.getResistance()));
        buildDescription("Stage required:",  Integer.toString(armor.getStageNecessary()));
        printDescription();
    }

    public void cantEquip(Object object) {
        System.out.println("You can't equip "+ object.getName());
    }
}
