package controller.save;

import model.character.Player;
import model.map.Map;

public class Game {

    private Map map;
    private Player player;
    private String codeGame;

    public Game(String codeGame) {
        this.codeGame = codeGame;
    }

    public void save(Map map, Player player) {
        this.map = map;
        this.player = player;
    }

    public void load(String codeGame){
        //appel du fichier
        this.map = map;
        this.player = player;
    }
}


