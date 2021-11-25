package view;

import model.character.Monstre;

public class MobView {
   Monstre monstre;

    public MobView(Monstre monstre){
        this.monstre = monstre;
    }

    public void deadMob(){
        System.out.println("Un monstre vient d'être tué");
    }

}
