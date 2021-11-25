package view;

import model.Position;
import model.character.Monstre;
import model.character.Monstres;
import model.character.Player;
import model.map.Chest;
import model.map.mapManagement.Plan;

import java.util.ArrayList;

public class PlanView {

    Plan plan;

    public PlanView(Plan plan){
        this.plan = plan;
    }



    /**
     * Affichage valeur des case du plan : debugueur
     */
    public void print() {
        for (int y = plan.getWidth(); y > -plan.getWidth() - 1; y--) {
            String line = "# ";
            for (int x = 0; x < plan.getLenght() + 1; x++) {
                if (plan.getPlan().containsKey(plan.key(x, y)))
                    if (get(x, y) >= 0)
                        line = line + "+" + plan.get(x, y) + " ";

                    else
                        line = line + get(x, y) + " ";
                else
                    line = line + "** ";
            }
            System.out.println(line + "#");
        }
    }

    public int get(int x, int y ){
        return plan.get(x,y);
    }

    /**
     * Affiche un dessin map
     */
    public void drawing(Player player) {

        System.out.println(" ☺ : You || ♥:"+player.getHeal()+" || ☼ : mob || ♦ : enter || ◘:exit || ■:wall [][][][][][]");
        for (int y = plan.getWidth(); y > -plan.getWidth() - 1; y--) {
            String line = "[ ";
            for (int x = 0; x < plan.getLenght() + 1; x++) {
                if ((plan.getPlan().containsKey(plan.key(x, y)))) {
                    if (get(x, y) >= 0) {
                        if (get(x, y) == 2)
                            line = line + "   ";
                        if (get(x, y) == 3)
                            line = line + " ☺ ";
                        if (get(x, y) == 4)
                            line = line + " ☼ ";
                        if(get(x,y) == 5)
                            line = line +" ◙ ";

                    }
                    else {
                        if (get(x, y) == -1)
                            line = line + "■■";
                        if (get(x, y) == -2)
                            line = line + " ♦ ";
                        if (get(x, y) == -3)
                            line = line + " ◘ ";
                    }
                }
                else
                    line = line + ".. ";
            }
            System.out.println(line + "]");
        }

    }

    /**
     * Ajout la trace du personnage sur un dessin de map
     * @param player
     */
    public void addDrawPlayer(Player player) {
        plan.put(plan.key(player.position.getX(),player.position.getY()),3);
    }

    /**
     * Ajout la trace des mobs sur un dessin de map
     * @param monstres
     */
    public void addDrawMobs(Monstres monstres) {
        for(Monstre monstre : monstres.getArrayList()){
            plan.put(plan.key(monstre.position.getX(), monstre.position.getY()),4);
        }
    }

    /**
     * Ajout la trace de l'entrée sur un dessin de map
     * @param enter
     * @param player
     */
    public void addEnter(Position enter, Player player) {
        if( ! enter.equals(player.position))
            plan.put(plan.key(enter.getX(),enter.getY()),-2);
    }

    /**
     * Ajout la trace de la sortie sur un dessin de map
     * @param exit
     * @param player
     */
    public void addExit(Position exit, Player player) {
        if( ! exit.equals(player.position))
            plan.put(plan.key(exit.getX(),exit.getY()),-3);
    }

    public void addDrawChest(ArrayList<Chest> chests) {
        for(Chest chest: chests){
            plan.put(plan.key(chest.getPosition().getX(),chest.getPosition().getY()),5);
        }

    }
}
