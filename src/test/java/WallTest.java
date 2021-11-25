import model.Position;
import model.map.mapManagement.Wall;
import org.junit.Test;

import java.util.ArrayList;

import static org.junit.Assert.assertEquals;

public class WallTest {

    @Test //ok
    public void printWallTest(){
        Position position  = new Position(0,0);
        Position position1 = new Position(0,1);
        Wall wall = new Wall(position, position1);
        wall.print();
    }

    @Test //ok
    public void nextTopTest(){
        Wall wall = new Wall(new Position(0,0), new Position(1,0));
        wall.print();
        int minLength   = 2;
        int maxLength   = 3;
        int maxWidthMap = 2;
        int minWidth    = 1;
        Wall b=wall.nextTop( minLength,  maxLength, maxWidthMap,  minWidth);
        Position end = new Position(3,0);
        while (! b.right.equals(end)) {
            b.print();
            b = b.nextTop(minLength, maxLength, maxWidthMap, minWidth);
        }

    }

    @Test //ok
    public void nextBotTest(){
        Wall wall = new Wall(new Position(-1,-1), new Position(0,-1));
        wall.print();
        int minLength   = 2;
        int maxLength   = 3;
        int maxWidthMap = 3;
        int minWidth    = 1;
        Wall b =  wall.nextBot( minLength,  maxLength, maxWidthMap,  minWidth);
        Position end = new Position(3,0);
        int i=8;
        while (! b.right.equals(end) && i!=0) {
            b.print();
            i--;
            b = b.nextBot(minLength, maxLength, maxWidthMap, minWidth);
        }
    }

    @Test //ok
    public void randomNextPosTest(){
        ArrayList<String> directions = new ArrayList<>();
        directions.add("Right");
        directions.add("Top");
        directions.add("Bot");
        Wall wall = new Wall(new Position(0,0), new Position(0,1));
        randomNextPos(directions,wall.right).print();
    }

    @Test //ok
    public void stringToPosTest(){
        Position position  = new Position(0,0);
        Position position1 = new Position(0,1);
        Wall wall = new Wall(position, position1);
        position1.left().print();
        stringToPos("Left",wall.right).print();
        assertEquals(true,stringToPos("Bot",wall.right).equals(position1.bot()));
        assertEquals(true,stringToPos("Top",wall.right).equals(position1.top()));
        assertEquals(true,stringToPos("Left",wall.right).equals(position1.left()));
        assertEquals(true,stringToPos("Right",wall.right).equals(position1.right()));
        assertEquals(null,stringToPos("Center",wall.right));

    }

    @Test
    public void topTest(){
        Wall wall=new Wall(new Position(0,0),new Position(0,1));
        wall.top().print();
    }

    @Test
    public void botTest(){
        Wall wall=new Wall(new Position(0,0),new Position(0,-1));
        wall.bot().print();
    }

    // =============Fonction Test ===============================
    private Position stringToPos(String direction, Position right){
        if(direction == "Bot")
            return right.bot();
        if(direction == "Top")
            return right.top();
        if(direction == "Right")
            return right.right();
        if(direction == "Left")
            return right.left();
        System.out.println("Erreur de direction:"+direction);
        return null;
    }

    private Position randomNextPos(ArrayList<String> directions, Position right) {
        String direction = directions.get((int) (Math.random() * directions.size()));
        return stringToPos(direction,right);
    }


}
