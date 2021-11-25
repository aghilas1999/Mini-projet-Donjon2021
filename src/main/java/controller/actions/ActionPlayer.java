package controller.actions;

import controller.KeyBoardController;
import model.character.Player;
import model.map.Map;
import move.Move;
import view.ActionView;

public class ActionPlayer {

    private Player player;
    private Map map;
    private KeyBoardController keyBoardController;
    private boolean hasplay = false;
    private int actions;
    private Move move;

    //@todo ++Test
    public ActionPlayer(Player player, Map map) {
        this.player = player;
        Move move = new Move(map);
        keyBoardController = new KeyBoardController(map ,player, move);
        this.map = map;

    }

    //@todo ++Test
    public boolean getAction() {
        return hasplay;
    }

    //@todo Addapter le text ++Test
    public void play() {
       ActionView actionView= new ActionView(player);
       actionView.champAction();
        hasplay = keyBoardController.read();
    }

    //@todo ajouter la notion de fusée  ++Test
    public boolean canUseExit() {
        return  player.isOnExit( map.getExit() ) &&
                player.getNbRocket() >= map.nbNeedRocket(); }

    public void attack(){}

    public void resetAction(){
        hasplay = false;
    }
}
