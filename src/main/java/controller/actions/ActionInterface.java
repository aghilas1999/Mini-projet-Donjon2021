package controller.actions;

import model.character.Player;
import model.map.Map;
import model.map.MapInterface;

public interface ActionInterface {

    boolean getPlayer();

    void playPlayer();

    boolean playerUseExit(MapInterface map);

    boolean getMobs();

    void playMobs(Map map, Player player);

    void resetPlayer();

    void resetActionMobs();
}
