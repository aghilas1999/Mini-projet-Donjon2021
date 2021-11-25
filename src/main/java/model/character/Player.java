package model.character;

import controller.KeyBoardController;
import model.Position;
import model.object.armors.AstronautSuit;
import model.map.Chest;
import model.map.Map;
import move.Move;
import view.View;

import java.util.ArrayList;

public class Player extends Character implements CharacterInterface {

    View view ;
    int viewDistance=3;
    public Player(View view) {
        this.view = view;
        setArmor(new AstronautSuit());
    }

    public boolean goTo(Move move, String direction){
        Position destination;
        switch (direction) {
            case "top":
                destination = position.top();
                break;
            case "right":
                destination = position.right();
                break;
            case "left":
                destination = position.left();
                break;
            case "bot":
                destination = position.bot();
                break;
            default:
                return false;
        }
        if(move.isMob(destination)) {
            view.handleMove(new Move("You will merge with a monster!\n" +
                    " What are you trying to achieve?! An hybrid space monster??"));
            return false;
        }
        if ( move.isWall(destination)) {
            view.handleMove(new Move("You face a wall"));
            return false;
        }
        if (! isNotPositionOfChest(destination,move.map)){
            view.handleMove(new Move("You face a chest"));
            return false;
        } else {
            position = destination;
            return true;

        }
    }

    public void getChest(Map map){
        Chest chest = map.getMapChestManagement().getFirstChestAround(position);
        inventory.getBagArrayList();
        if(chest != null){
            inventory.addAll(chest.getItem());
            map.removeChest(chest);
        }
    };

    @Override
    public Position getPosition() {
        return position;
    }


    /**
     * Retourne un tableau de mob se situant dans la range du joueur
     * @param monstres
     * @return
     */
    public ArrayList<Monstre> observation(Monstres monstres) {
       ArrayList<Monstre> mobsFound = new ArrayList<>();

       for(int x=position.getX()-viewDistance;x<position.getX()+viewDistance;x++){
           for(int y=position.getY()-viewDistance;y<position.getY()+viewDistance;y++){
               for(Monstre monstre : monstres.getArrayList()){
                   if(monstre.getPosition().equals(new Position(x,y)))
                       mobsFound.add(monstre);
               }
           }
       }
        return mobsFound;
    }

    /**
     * Vérifie si le joueur peut attaquer à la distance posJoueur
     * @param posJoueur
     * @return
     */
    public boolean playerIsInRange(Position posJoueur) {
        return false;
    }

    /**
     * Selectionner la direction d'attaque et attaque.
     * @param map
     * @param move
     * @return
     */
    public boolean toTargetMobAndHit(Map map, Move move){
        ArrayList<Monstre> monstres = observation(map.getMobs());
        if(monstres.size()>0){
            KeyBoardController keyBoardController = new KeyBoardController(map,this,move);
            return keyBoardController.selectTarget(map, monstres);
        }
        System.out.println("What do you expect when you attacked void?");
        return false;
    }

    /**
     * Attaque un mob dans la deriection donnée
     * @param map
     * @param monstres
     * @param dir
     * @return
     */
    public  boolean attaqueTo(Map map, ArrayList<Monstre> monstres, String dir){
        Monstre monstre = selectTheFirstMobToDir( map, monstres, dir);
        if(monstre == null)
            return false;
        System.out.println("mob heal:"+ monstre.getHeal());
        monstre.takeDamage(getDamage());
        System.out.println("mob heal:"+ monstre.getHeal());
        if(monstre.isDead()) {
            inventory.addAll(monstre.getBagArrayList());
            map.getMobs().clearMob(monstre);

        }
        return true;

    }

    /**
     * Retourne le premier mob apperçut dans la direction donnée
     * @param map
     * @param monstres
     * @param dir
     * @return
     */
    private Monstre selectTheFirstMobToDir(Map map, ArrayList<Monstre> monstres, String dir) {
      Monstre result = null;
      for(Monstre monstre : monstres) {
          switch (dir) {
              case "top":
                  if(monstre.position.getY()>position.getY() && position.getX() == monstre.position.getX() && result==null )
                    result = monstre;
                    else if(monstre.position.getY()>position.getY() && position.getX() == monstre.position.getX() && monstre.position.getY()< result.position.getY())
                        result = monstre;
                  break;
              case "bot":
                  if(monstre.position.getY()<position.getY() && position.getX() == monstre.position.getX()) {
                      if (result == null)
                          result = monstre;
                      else if (monstre.position.getY() > result.position.getY())
                          result = monstre;
                  }
                      break;
              case "rigth":
                  if(monstre.position.getX()> position.getX() && position.getY() == monstre.position.getY()) {
                      if (result == null)
                          result = monstre;
                      else if (monstre.position.getX() < result.position.getX())
                          result = monstre;
                  }
                  break;
              case "left":

                  if(monstre.position.getX()< position.getX()  && position.getY() == monstre.position.getY()) {
                      if (result == null)
                          result = monstre;
                      else if (monstre.position.getX()> result.position.getX())
                          result = monstre;
                  }
                  break;
              default:
                  break;
          }
      }
      return result;
    }

    @Override
    public void setSpawn(Map map) {
        if (map.spawnable(1, 0))
            position = new Position(1, 0);
        else position = map.getSpawn();
    }

    @Override
    public boolean isDead() {
        return getHeal()==0;
    }

    @Override
    public boolean isOnExit(Position exit) {
        return position.equals(exit);
    }

    public int getNbRocket(){
        return inventory.nbRocket();
    }

}
