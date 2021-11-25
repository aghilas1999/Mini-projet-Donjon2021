package model.map.mapManagement;

import model.Position;
import model.object.Object;
import model.object.Rocket;
import model.object.armors.SpacesSuit;
import model.object.weapons.whiteWeapons.Axe;
import model.map.Chest;
import model.map.Map;

import java.util.ArrayList;

public class MapChestManagement {

    //Tout les loots obtenables sur la carte à répartir entre les mobs et les rochers
    ArrayList<Object> loots;
    private int maxChest;
    private ArrayList<Chest> chests;
    int stage;
    Map map;

    public MapChestManagement(int stage, Map map){
        super();
        loots= new ArrayList<>();
        this.stage = stage;
        this.map = map;


        initialiseLoot();
    }

    public void initialiseLoot(){
        loots = new ArrayList<>();
        //@todo à retirer pour test
        loots.add(new Axe());
        loots.add(new SpacesSuit());
        initialiseRocket();
        generateChests();
        initialiseChest();
    }

    private void initialiseChest() {
        for(Object loot : loots) {
            chests.get((int) Math.random() * loots.size()).add(loot);
        }
    }

    public void initialiseRocket(){
        for(int x=0;x<stage+5;x++)
            loots.add(new Rocket());
    }

    /**
     * Generer des chests
     */
    public void generateChests(){
        maxChest= (int)(stage/5)+2;
        chests = new ArrayList<>();
        for(int x=0;x<maxChest;x++){
            chests.add(new Chest(getRandomPositionChest()));
        }

    }

    /**
     * return une position random du chest
     * @return
     */
    private Position getRandomPositionChest() {
        Position posChest;
        do{
            posChest = map.getSpawn();
        }while(posChestIsValid(posChest));
        return posChest;
    }

    /**
     * return si la position du coffre est autorisé
     * @param posChest
     * @return
     */
    private boolean posChestIsValid(Position posChest) {
        for(Chest chest : chests){
            if(existChestAround(posChest,chest.getPosition()))
                return false;
        }
        return(
                posChest.getX()<=5
                        && !posChest.equals(map.getEnter()) && !posChest.equals(map.getExit())
        );
    }

    /**
     * Return si il existe un chest autour de posChest
     * @param posChest
     * @return
     */
    private boolean existChestAround(Position posChest,Position otherChest) {
        return(
                posChest.top().equals(otherChest )&&
                        posChest.bot().equals(otherChest) &&
                        posChest.left().equals(otherChest) &&
                        posChest.right().equals(otherChest) &&
                        posChest.top().right().equals(otherChest )&&
                        posChest.top().left().equals(otherChest) &&
                        posChest.bot().right().equals(otherChest) &&
                        posChest.bot().left().equals(otherChest)
        );
    }

    public Chest getFirstChestAround(Position position) {
        for(Chest chest: chests ) {
            Position posChest = chest.getPosition();
            if (posChest.top().equals(position)
                    || posChest.bot().equals(position)
                    || posChest.left().equals(position)
                    || posChest.right().equals(position))
                return  chest;

        }
        return null;
    }

    public boolean checkChest(Position position){
        for(Chest chest : chests){
            if(existChestAround(position, chest.getPosition()))
                return true;
        }
        return false;
    }

    public ArrayList<Chest> getChests() {
        return chests;
    }

    public void setLoots(ArrayList<Object> newLoots) {
        loots = newLoots;
    }

    public ArrayList<Object> getLoots() {
        return loots;
    }

    public void addLoots(Object object) {
        loots.add(object);
    }

    public void removeLoots(Object object){
        loots.remove(object);
    }

    public boolean isEmptyLoots(){
        return loots.isEmpty();
    }

    public void clearLoots() {
        loots.clear();
    }
}
