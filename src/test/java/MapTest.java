
import model.Position;
import model.character.Player;
import model.map.Map;
import model.map.mapManagement.Plan;
import org.junit.jupiter.api.Test;
import view.PlanView;


public class MapTest {

    @Test
    public void buildWallsTest(){
        Player player = new Player(null);
        Map map=new Map(1);
        Plan plan = new Plan(map.getMaxWidthMap(), map.getMaxLengthMap(),map.getWalls());
        plan.print();
        PlanView planView = new PlanView(plan);
        planView.drawing(player);
    }

    @Test // ok
    public void getWidthMapTest(){
        Position right = new Position(0,0);
        int maxLengthMap = 20;
        int maxWidthMap = 10;

        while (maxWidthMap!= 0) {
            maxWidthMap = getWidthMap(right, maxLengthMap, maxWidthMap);
            System.out.println("maxWidthMap:"+ maxWidthMap +" x:"+ right.getX());
            right=right.right();
        }
    }
    @Test
    public void getNumberItemTest(){

    }
    @Test
    public void setLootTest(){

    }
    @Test
    public void addLootTtest(){

    }
    @Test
    public void removeLootTest(){

    }
    @Test
    public void clearLootTest(){

    }
    @Test
    public void isVoidLootTest(){

    }
    @Test
    public void counterRocketTest(){

    }
    @Test
    public void isWallTest(){

    }

    //=================================================================================================================

    private int getWidthMap(Position right, int maxLengthMap, int maxWidthMap){
        if(maxWidthMap<(maxLengthMap - (int) right.getX()))
            return maxWidthMap;
        return   maxLengthMap - (int) right.getX()  ;
    }
}