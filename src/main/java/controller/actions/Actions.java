package controller.actions;

import model.character.Monstre;
import model.character.Player;
import model.map.Map;
import model.map.MapInterface;

import java.util.ArrayList;

public class Actions implements ActionInterface {

    private int max;
    private int number;
    private int duration;

    private Player player;
    private Map map;
    private ArrayList<Monstre> monstres;
    private ArrayList<ActionMob> actionsMobs = new ArrayList<>();
    private ActionPlayer actionPlayer;

    public Actions(Map map, Player player, ArrayList<Monstre> monstres){
         this.map=map;
         this.player=player;
         this.monstres = monstres;
         for(Monstre monstre : monstres){
             actionsMobs.add(new ActionMob(monstre));
         }
         this.actionPlayer = new ActionPlayer(player, map);
    }

    //@todo à tester
    @Override
    public boolean getPlayer() {
        return actionPlayer.getAction();
    }

    //@todo à tester
    /***
     * Permet au joueur de réaliser une action
     */
    @Override
    public void playPlayer() {
        actionPlayer.play();
    }

    //@todo à tester
    /**
     *
     * @param map
     * @return bool if player had played
     */
    @Override
    public boolean playerUseExit(MapInterface map) {
        return actionPlayer.canUseExit();
    }


    /**
     *
     * @return bool if mobs had played
     */
    @Override
    public boolean getMobs() {

        for(ActionMob actionMob :actionsMobs){
           if( ! actionMob.hasPlay())
               return false;
        }
        return true;
    }

    //@todo à tester
    /**
     * Fait joueur automatiquement chaque mob
     */
    @Override
    public void playMobs(Map map, Player player) {
        for(ActionMob actionMob :actionsMobs)
            actionMob.play(map, player);
    }

    @Override
    public void resetPlayer() {
        actionPlayer.resetAction();
    }

    @Override
    public void resetActionMobs() {
        for(ActionMob actionMob :actionsMobs)
            actionMob.resetActions();
    }
}
