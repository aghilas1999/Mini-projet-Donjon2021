package move;

import model.Position;
import model.character.Monstre;
import model.map.Map;

public class Move {
    public Map map;
    public String message;

    public Move(Map carte){
        map=carte;
    }

    public Move(String message) {
        this.message = message;
    }

    public boolean isWall(Position position){
        position.print();
        return map.isWall(position);
    }

    public boolean isMob(Position positionPlayer){
        for(Monstre monstre : map.getMobs().getArrayList()){
            if(monstre.position.equals(positionPlayer))
                return true;
        }
        return false;
    }

    public boolean OnExit(Position position){
        return map.getExit().equals(position) ;   }

}
