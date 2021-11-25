package model.character;

import model.Position;
import model.map.Map;

import java.util.ArrayList;

public class Monstre extends Character {

    boolean combat = false;
    int viewDistance=4;

    public Monstre(){
    }


    /**
     * Retourne la position du joueur ou null si rien trouvé
     * @param posJoueur
     * @return
     */
    public Position observation(Position posJoueur) {
        for(int x=position.getX()-viewDistance;x<position.getX()+viewDistance;x++){
            for(int y=position.getY()-viewDistance;y<position.getY()+viewDistance;y++){
                if(posJoueur.equals(new Position(x,y))) {
                    return posJoueur;
                }
            }
        }
        return null;
    }

    @Override
    public boolean playerIsInRange(Position posJoueur) {
        return (posJoueur.getDistance(getPosition())-1)<getWeapon().getRange();
    }

    public void follow(Position posJoueur, Map map) {
        ArrayList<Position> positionsNext = new ArrayList<>();
        if (posJoueur.getY() > position.getY() && canGoOnPosition(position.top(),map, posJoueur))
            positionsNext.add(position.top());
        if (posJoueur.getY() < position.getY() && canGoOnPosition(position.bot(),map, posJoueur))
            positionsNext.add(position.bot());
        if (posJoueur.getX() > position.getX() && canGoOnPosition(position.right(),map, posJoueur))
            positionsNext.add(position.right());
        if (posJoueur.getX() < position.getX() && canGoOnPosition(position.left(),map, posJoueur))
            positionsNext.add(position.left());

        if( positionsNext.size() !=0)
            position = positionsNext.get( (int) ( Math.random() * (positionsNext.size())) );

    }

    private Position positionToX(int minX, Position posJoueur) {
        if(position.getX()==minX)
            return position;
        if(posJoueur.getX()==position.getX())
            return posJoueur;
        return null;
    }

    /**
     * Une methode qui nous permet de faire des
     */
    public void attackPlayer(Player player) {
        if(playerIsInRange(player.getPosition()))
            player.takeDamage(getDamage());
    }



    @Override
    public Position getPosition() {
        return position;
    }

    @Override
    public void setSpawn(Map map) {
        do{
            position = map.getSpawn();
        }while (spawnIsNotValid(position, map));
    }

    private boolean spawnIsNotValid(Position position,Map map) {
        return (   position.equals(map.getExit())
                || (position.getX()< map.getMinLengthGenerate() && position.getY() < map.getMinWidthMapGenerate() &&  position.getY() > -map.getMinWidthMapGenerate())
                );
    }

    @Override
    public boolean isDead() {
        return health.isDead();
    }

    @Override
    public boolean isOnExit(Position p) {
        return false;
    }

    /**
     * Déplace un monstre à une position si possible ou reste sur place
     */
    public void move(Map map, Position playerPosition){
        ArrayList<Position> destinations = buildDestinations(map, playerPosition);
        if(destinations.size()!=0)
            position = destinations.get( (int) ( Math.random() * (destinations.size())));
    }

    /**
     * Construit un tableau de destination accessible et le retourne.
     * @return ArrayList<Position>
     */
    private ArrayList<Position> buildDestinations(Map map,Position playerPosition) {
        ArrayList<Position> destinations = new ArrayList<>();
        Position destination = position.top();
        if (canGoOnPosition(destination, map, playerPosition))
            destinations.add(destination);
        destination = position.bot();
        if (canGoOnPosition(destination, map, playerPosition))
            destinations.add(destination);
        destination = position.left();
        if (canGoOnPosition(destination, map, playerPosition))
            destinations.add(destination);
        destination = position.right();
        if (canGoOnPosition(destination, map, playerPosition))
            destinations.add(destination);
        return destinations;
    }

    /**
     * Return if position is different of ( posJoueur, posMonstre, void, wall)
     * @return practicable position
     */
    private boolean canGoOnPosition(Position destination, Map map, Position playerPosition){
        return ( isWalkablePosition(destination, map)
        && ( isNotPositionOfMob(destination, map))
        && ! destination.equals(playerPosition)
        &&  isNotPositionOfChest(destination,map)
        && ! map.getExit().equals(destination)
        && ! map.getEnter().equals(destination)
        );
    }

    /**
     * Vérifie que la position n'est pas déjà occupé par celle d'un monstre
     * @param map
     * @return
     */
    private boolean isNotPositionOfMob(Position destiation, Map map) {
        for(Monstre monstre : map.getMobs().getArrayList()){
            if(monstre.position.equals(destiation))
                return false;
        }
        return true;
    }

    /**
     * Retourne si la postion n'est ni un mur ni le vide
     * @param destination
     * @param map
     */
    private boolean isWalkablePosition(Position destination, Map map) {
        return map.spawnable(destination.getX(), destination.getY());
    }

}
