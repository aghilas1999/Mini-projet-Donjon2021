package model.map;

import model.Position;
import model.character.Monstres;
import model.character.Player;
import model.object.Object;
import model.map.mapManagement.MapChestManagement;
import model.map.mapManagement.Plan;
import model.map.mapManagement.Wall;
import view.MapView;

import java.util.ArrayList;
import java.util.Random;

public class Map implements MapInterface{

    private int stage=0;
    private int minLengthGenerate = 10;  //min = 2
    private int minWidthMapGenerate = 5;  //partant du centre
    private int maxLengthMap = 30;
    private int maxWidthMap = 8;  // intialisé part la longuer de la map
    private ArrayList<Wall> walls;
    private Plan plan;
    private Monstres monstres;
    private MapChestManagement mapChestManagement;

    // A revoir les valeurs par défaut manque maxWidthMap
    public Map(int stage){
        this.stage= stage;

        MapView mapView = new MapView(this);
        mapView.printLegend();
        generate();
        mapChestManagement = new MapChestManagement(stage, this);
    }

    private Position enter= new Position(1,0), exit;


    //================================ Génération/Innitialisation de la map =================================
    private void generate(){
        //création des murs
        this.walls = buildWalls();
        //initialiser plan
        plan = new Plan(maxWidthMap, maxLengthMap,walls);

        exit = plan.getExit();


    }

    /**
     * Generate a new map.
     * @todo a tester!!!
     * @return ArrayList<Map>
     */
    private   ArrayList<Wall> buildWalls(){
        // Initialize map
        ArrayList<Wall> walls = new ArrayList<>();

        //create walls to not create conflicts with restrited area
        Position position = new Position(0,0);
        Wall walltop = new Wall(position, position.top());
        Wall wallbot = new Wall(position, position.bot());
        walls.add(wallbot);
        walls.add(walltop);

        for(int i = 1; i< minWidthMapGenerate; i++) {
             walltop = walltop.top();
             wallbot = wallbot.bot();
            walls.add(wallbot);
            walls.add(walltop);
        }

        //créer les contour de la map
        while ( ! walltop.right.equals(wallbot.right) &&
                ! walltop.left.equals(wallbot.right) &&
                ! walltop.right.equals(wallbot.left) &&
                (wallbot.right.getY() >= -maxWidthMap)
                &&
                (walltop.right.getY() <= maxWidthMap)
                &&
                (walltop.right.getX() <= maxLengthMap)
                &&
                (wallbot.right.getX() <= maxLengthMap)
                ){

            //condition pour obtenir un même avancement pour les murs
            //condition to generate the same advancement for walls
            if(walltop.right.getX()<=wallbot.right.getX()) {
                walltop = walltop.nextTop(minLengthGenerate, maxLengthMap, getWidthMap(walltop.right), minWidthMapGenerate);
                walls.add(walltop);
            }

            //condition to generate the same advancement for walls
            if(walltop.right.getX()>=wallbot.right.getX()) {
                wallbot = wallbot.nextBot(minLengthGenerate, maxLengthMap, getWidthMap(wallbot.right), minWidthMapGenerate);
                walls.add(wallbot);
            }
        }

        return walls;
    }

    @Override
    public int nbNeedRocket() {
        return stage+5;
    }


    //================================ Get basique  =================================
    @Override
    public int getStage(){
        return stage;
    }

    @Override
    public int getMinWidthMapGenerate(){
        return minWidthMapGenerate;
    }

    @Override
    public int getMinLengthGenerate(){
        return minLengthGenerate;
    }

    /**
     * Permet de diminuer la taille de la carte et limité un arret net de la map
     * @param right
     * @return
     */
    @Override
    public int getWidthMap(Position right){
        if(maxWidthMap<maxLengthMap - (int) right.getX())
            return maxWidthMap;
        return   maxLengthMap - (int) right.getX()  ;
    }

    @Override
    public int getMaxWidthMap() {
        return maxWidthMap;
    }

    @Override
    public int getMaxLengthMap() {
        return maxLengthMap;
    }

    @Override
    public int getLenghtPlan() {
        return plan.getLenghtPlan();
    }

    @Override
    public Monstres getMobs() {
        return monstres;
    }

    @Override
    public boolean isWall(Position position){
        for(Wall wall:walls){
            if(wall.right.equals(position) || wall.left.equals(position))
                return true;
        }
        return false;
    }

    @Override
    public Position getEnter() {
        return enter;
    }

    @Override
    public Plan getPlan() {
        return plan;
    }

    @Override
    public Position getExit() {
        return plan.getExit();
    }

    public ArrayList<Wall> getWalls() {
        return walls;
    }

    @Override
    public Position getSpawn(){
        int minX=0,maxX=getLenghtPlan(),x,y;
        do {
            y = -maxWidthMap + new Random().nextInt(maxWidthMap*2 +1);;
            x = (int) (Math.random() * (maxX - minX));
        }while ( ! spawnable(x,y));
        return new Position(x,y);
    }


    // ============ Retourne une position spawnable =====================
    @Override
    public boolean spawnable(int x, int y) {
        return plan.spawnable(x,y);
    }

    // ============ Initialisation de mobs =====================
    @Override
    public void initialiseMobs(){
        monstres = new Monstres(this, stage + 2);
    }

    // ============ Dessin =====================

    @Override
    public void drawing(Player player) {
        MapView mapView = new MapView(this);
        mapView.drawing(player);
    }

    @Override
    public void drawingAll(Player player) {
        MapView mapView = new MapView(this);
        mapView.drawingAll(player);
    }

    @Override
    public void print() {
        plan.print();
    }


    // ============ Chest / Loot =====================

    @Override
    public void setLoot(ArrayList<Object> loots) {
        mapChestManagement.setLoots(loots);
    }

    @Override
    public void addLoot(Object object) {
        mapChestManagement.addLoots(object);
    }

    @Override
    public void removeLoot(Object object) {
        mapChestManagement.removeLoots(object);
    }

    @Override
    public void clearLoot() {
        mapChestManagement.clearLoots();
    }

    @Override
    public boolean isVoidLoot() {
        return mapChestManagement.isEmptyLoots();
    }

    @Override
    public void removeChest(Chest chest){
        mapChestManagement.getChests().remove(chest);;
    }

    @Override
    public ArrayList<Chest> getChests(){
        return getMapChestManagement().getChests();
    }

    @Override
    public MapChestManagement getMapChestManagement() {
        return mapChestManagement;
    }
}

