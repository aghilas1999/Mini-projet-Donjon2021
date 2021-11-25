package model.object;

import model.character.Player;
import view.ItemView;

public class Rocket extends Object {
    int number = 1;
    ItemView itemView;

    public Rocket(){
         name = "Rocket";
         stackable = true;
         itemView = new ItemView(this);
    }

    @Override
    public void description() {

        itemView.printRocket(this);
    }

    @Override
    public void equip(Player player) {
        itemView.cantEquip(this);
    }

    public void add(int number){
        this.number = this.number+number;
    }

    public void remove(int number){
        this.number = this.number-number;
    }

    public void stack(Rocket rocket){
        number = number + rocket.getNumber();
    }

    public int getNumber(){
        return number;
    }

    public boolean use(int used){
        if(number<used)
            return false;
        number = number - used;
        return true;
    }
}
