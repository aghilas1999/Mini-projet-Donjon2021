package view;

import model.character.Player;
import model.map.Map;
import model.map.mapManagement.Plan;

public class MapView {

    Map map;

    public MapView(Map map){
        this.map = map;
    }


    public void drawing(Player player){
        PlanView planView = new PlanView(map.getPlan());
        planView.drawing( player);
    }


    public void drawingAll(Player player){
        Plan clone = new Plan(map.getMaxWidthMap(), map.getMaxLengthMap(),map.getWalls());
        PlanView planView = new PlanView(clone);
        planView.addDrawPlayer(player);
        planView.addDrawMobs(map.getMobs());
        planView.addEnter(map.getEnter(), player);
        planView.addExit(map.getExit(), player);
        planView.addDrawChest(map.getMapChestManagement().getChests());
        planView.drawing(player);
    }

    public void printLegend() {
        System.out.println("stage:" + map.getStage() + "\n" + "minLengthGenerate:" + map.getMinLengthGenerate() + "\n" +
                "minnWidthMapGenerate:" + map.getMinWidthMapGenerate() + "\n" +
                "maxLengthMap:" + map.getMaxLengthMap() + "\n" +
                "maxWidthMap:" + map.getMaxWidthMap());
    }
}
