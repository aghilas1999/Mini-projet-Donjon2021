package view;

import model.character.Monstre;
import model.character.Player;

public class ActionView {
    Monstre monstre;
    Player player;

    public ActionView(Monstre monstre, Player player){
        this.monstre = monstre;
        this.player=player;
    }
    public ActionView(Player player){
        this.player=player;
    }

    public void StartMob(){
        /*System.out.println("================MOB PLAY==============");*/
    }
    public void PlayerAttack(Player player){
        System.out.println("attaque -> player life:"+player.getHeal());
    }
    public void champAction(){
        System.out.println(" a: attaquer \n e: inventaire \n m:afficher map \n z,q,s,d: se déplacer \n 5: utiliser");
    }
}
