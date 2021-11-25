import model.Position;
import org.junit.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class PositionTest {

    @Test // ok
    public void printTest(){
        Position position=new Position(1,2.5);
        position.print();
    }

    @Test //ok
    public void mooveTest(){
        Position position=new Position(1,2.5);
        Position left = position.left();
        Position right = position.right();
        Position top = position.top();
        Position bot = position.bot();

        assertEquals(0, left.getX());
        assertEquals(2.5, left.getY());

        assertEquals(2, right.getX());
        assertEquals(2.5, right.getY());

        assertEquals(1, top.getX());
        assertEquals(3.5, top.getY());

        assertEquals(1, bot.getX());
        assertEquals(1.5, bot.getY());
    }

    @Test //ok
    public void equalsTest(){
        Position position  = new Position(1,2);
        Position position1 = new Position(1,2);
        Position position2 = new Position(2,8);

        assertEquals(true,position.equals(position1));
        assertEquals(false,position.equals(position2));
    }

    @Test // ok
    public void getsTest(){
        Position position=new Position(1,2.5);
        assertEquals(1, position.getX());
        assertEquals(2.5, position.getY());
    }
    @Test
    public void getDistanceTest(){
        Position position = new Position (-1,1);
        Position position1 = new Position(2,1);
        assertEquals(position.getDistance(position1),3);
    }

}
