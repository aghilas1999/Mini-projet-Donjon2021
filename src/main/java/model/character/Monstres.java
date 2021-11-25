package model.character;

import model.Position;
import model.map.Map;
import view.MobView;

import java.util.ArrayList;

public class Monstres {

    ArrayList<Monstre> monstres = new ArrayList<>();

    public Monstres(Map map, int nbmob){
        for(int i=0; i<nbmob ; i++){
            Monstre monstre = new Monstre();
            do
                monstre.setSpawn(map);
            while ( ! positionIsEmpty(monstre.position));
            monstres.add(monstre);
        }
    }

    private boolean positionIsEmpty(Position position) {
        for(Monstre monstre : monstres)
            if( monstre.position.equals(position))
                return false;
        return true;
    }

    public ArrayList<Monstre> getArrayList(){
        return monstres;
    }

    public void clearMob(Monstre monstre) {
        if(monstre.isDead())
            monstres.remove(monstre);
        MobView mobView= new MobView(monstre);
        mobView.deadMob();
    }
}
