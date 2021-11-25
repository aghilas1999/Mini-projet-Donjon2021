package model.map;

import model.Position;
import model.character.Player;
import model.object.Object;
import model.map.mapManagement.MapChestManagement;
import model.map.mapManagement.Plan;

import java.util.ArrayList;

public interface MapInterface {

    /**
     * @param position
     * @return
     * @// TODO: 17/11/2020 peu êtr eoptimiser en passant par plan !!
     * Ici le choix est fait de simplifier les déplacements à des positions aux coordonnées entières.
     * Dans le cas ou l'on peu se déplacer sur des valeurs flotante il faudra addapter le code.
     */
    boolean isWall(Position position);


    void setLoot(ArrayList<Object> loots);

    void addLoot(Object object);

    void removeLoot(Object object);

    void clearLoot();

    boolean isVoidLoot();

    boolean spawnable(int x, int y);

    Position getSpawn();

    void print();

    java.lang.Object getMobs();

    Position getEnter();

    Position getExit();

    Plan getPlan();

    void initialiseMobs();

    void drawing(Player player);

    void drawingAll(Player player);

    ArrayList<Chest> getChests();

    int getLenghtPlan();

    void removeChest(Chest chest);

    MapChestManagement getMapChestManagement();

    int getMaxWidthMap();

    int getMaxLengthMap();

    int getStage();

    int getMinWidthMapGenerate();

    int getMinLengthGenerate();

    int getWidthMap(Position right);

    int nbNeedRocket();
}
