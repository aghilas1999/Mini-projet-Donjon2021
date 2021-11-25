import model.Position;
import model.map.Map;
import model.map.mapManagement.Plan;
import model.map.mapManagement.Wall;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;

import static org.junit.Assert.assertEquals;

public class PlanTest {

    @Test //ok
    public void buildTest() {
        int maxWidth = 2;
        int maxLenght =2;
        ArrayList<Wall> walls = new ArrayList<>(); //@todo à implémenté
        walls.add(new Wall(new Position(0,0), new Position(0,1)));
        walls.add(new Wall(new Position(0,1), new Position(0,2)));
        walls.add(new Wall(new Position(0,2), new Position(1,2)));
        walls.add(new Wall(new Position(1,2), new Position(2,2)));
        walls.add(new Wall(new Position(2,2), new Position(3,2)));
        walls.add(new Wall(new Position(3,2), new Position(4,2)));
        walls.add(new Wall(new Position(4,2), new Position(4,1)));
        walls.add(new Wall(new Position(4,1), new Position(4,0)));
        walls.add(new Wall(new Position(4,0), new Position(4,-1)));
        walls.add(new Wall(new Position(4,-1), new Position(4,-2)));
        walls.add(new Wall(new Position(4,-2), new Position(3,-2)));
        walls.add(new Wall(new Position(3,-2), new Position(2,-2)));
        walls.add(new Wall(new Position(2,-2), new Position(1,-2)));
        walls.add(new Wall(new Position(1,-2), new Position(0,-2)));
        walls.add(new Wall(new Position(0,-2), new Position(0,-1)));
        walls.add(new Wall(new Position(0,-1), new Position(0,0)));
        Plan plan = new Plan(2,2,walls);
        plan.print();

    }

    @Test //ok
    public void getTest(){
        int x=5, y=-3;
        HashMap<String, Integer> plan= new HashMap<>();
        plan.put(key(x,y),1);
        int result = get(x,y,plan);
        assertEquals(1, result);
    }

    @Test //ok
    public void keyTest() {
        int x=5, y=-4;
        assertEquals("5:-4",key(x,y));
    }

    @Test //ok
    public void isWallTest(){
        ArrayList<Wall> walls = new ArrayList<>();
        walls.add(new Wall(new Position(0,0), new Position(0,1)));
        assertEquals(true,isWall(new Position(0,0), walls));
        assertEquals(false,isWall(new Position(0,2), walls));
    }

    @Test //ok
    public void getNewPathTest(){
        int x=4;
        int y=3;
        HashMap<String, Integer> plan = exemplePlan();
        print2(4,4,plan);
        getNewPath(x,y,plan).print();
    }

    @Test //ok
    public void existAnOtherPathTest(){
        int x=1;
        int y=-3;
        HashMap<String, Integer> plan = exemplePlan();
        print1(4,4);
        print2(4,4,plan);
        System.out.println("("+x+":"+y+") a un chemin:"+existAnOtherPath( x, y, plan));
        System.out.println("(3:-3) a un chemin:"+existAnOtherPath( 3, -3, plan));
    }

    @Test //Ok
    public void printTest(){
        HashMap<String, Integer> plan = exemplePlan();
        print1(4,4);
        System.out.println("============================");
        print2(4,4,plan);
    }

    @Test
    public void getLenghtPlanTest(){
        Map map = new Map(0);
        System.out.println("max Lenght:" + map.getLenghtPlan());
        System.out.println("====================");
        map.getExit().print();
        System.out.println("hello");
        map.print();
       // print2(map.getPlan().width, map.getPlan().length,map.getPlan().plan);
      //  print1(map.getPlan().width, map.getPlan().length);

    }


    @Test
    public void test(){
        int x=0,y=3;
        while ( x!= 1000) {
            x++;
            if((int) (Math.random() * (2)) == 0)
                y++;
        }
        System.out.println(y
        );

    }

    //=========Fonction à test =============

    public Integer get(int x, int y, HashMap<String, Integer> plan){
        return plan.get(key(x,y));
    }

    private String key(int x, int y) {
        return x+ ":" +y;
    }

    public boolean isWall(Position position, ArrayList<Wall> walls){
        for(Wall wall:walls){
            if(wall.right.equals(position) || wall.left.equals(position))
                return true;
        }
        return false;
    }

    private Position getNewPath(int x, int y,HashMap<String, Integer> plan) {

        ArrayList<String> ohterPath = getExistAnOtherPath(x,y, plan);
        if (ohterPath.contains("top"))
            return new Position(x,y+1);
        if (ohterPath.contains("bot"))
            return new Position(x,y-1);
        if (ohterPath.contains("right"))
            return new Position(x+1,y);
        if (ohterPath.contains("left"))
            return new Position(x-1,y);
        return new Position(x,y);
    }

    public void print1(int width, int length){
        for(int y=width+1; y>-width-2; y--) {
            String line = "[ ";
            for (int x = -1; x < length+2; x++) {
               // if (plan.containsKey(key(x, y)))
                  //  if(get(x,y,plan)>=0)
                        //line = line+"+"+get(x,y,plan)+" ";
                        line = line+"("+x+":"+y+") ";
                   // else
                    //    line = line+get(x,y,plan)+" ";
              //  else
                //    line = line + ".. ";
            }
            System.out.println(line+"]");
        }
    }

    public void print2(int width, int length, HashMap<String,Integer> plan){
        for(int y=width+1; y>-width-2; y--) {
            String line = "[ ";
            for (int x = 0-1; x < length+2; x++) {
                 if (plan.containsKey(key(x, y)))
                  if(get(x,y,plan)>=0)
                line = line+"+"+get(x,y,plan)+" ";

                 else
                    line = line+get(x,y,plan)+" ";
                  else
                    line = line + ".. ";
            }
            System.out.println(line+"]");
        }
    }

    private ArrayList<String> getExistAnOtherPath(int x, int y,HashMap<String,Integer> plan) {
        ArrayList<String> otherPath= new ArrayList<>();
        if (  plan.containsKey(key(x,(y+1))) )
            if (  plan.get(key(x,(y+1))) == 0 )
                otherPath.add("top ");
        if (   plan.containsKey(key(x,(y-1))) )
            if (  plan.get(key(x,(y-1))) == 0 )
                otherPath.add("bot");
        if (   plan.containsKey(key((x+1),y)) )
            if (  plan.get(key((x+1),y)) == 0 )
                otherPath.add("right");
        if (   plan.containsKey(key((x-1),y)) )
            if (  plan.get(key((x-1),y)) == 0 )
                otherPath.add("left");
        return otherPath;
    }

    //==================Outil==================
    private HashMap<String, Integer> exemplePlan(){
        HashMap<String, Integer> plan = new HashMap<>();
        plan.put(key(0,0),-1);
        plan.put(key(1,0),0);
        plan.put(key(2,0),0);
        plan.put(key(3,0),0);
        plan.put(key(4,0),-1);
        plan.put(key(0,1),-1);
        plan.put(key(1,1),0);
        plan.put(key(2,1),0);
        plan.put(key(3,1),0);
        plan.put(key(4,1),-1);
        plan.put(key(0,2),-1);
        plan.put(key(1,2),0);
        plan.put(key(2,2),0);
        plan.put(key(3,2),0);
        plan.put(key(4,2),-1);
        plan.put(key(0,3),-1);
        plan.put(key(1,3),0);
        plan.put(key(2,3),0);
        plan.put(key(3,3),0);
        plan.put(key(4,3),-1);
        plan.put(key(0,4),-1);
        plan.put(key(1,4),-1);
        plan.put(key(2,4),-1);
        plan.put(key(3,4),-1);
        plan.put(key(4,4),-1);
        plan.put(key(0,-1),-1);
        plan.put(key(1,-1),0);
        plan.put(key(2,-1),0);
        plan.put(key(3,-1),0);
        plan.put(key(4,-1),-1);
        plan.put(key(0,-2),-1);
        plan.put(key(1,-2),0);
        plan.put(key(2,-2),0);
        plan.put(key(3,-2),0);
        plan.put(key(4,-2),-1);
        plan.put(key(0,-3),-1);
        plan.put(key(1,-3),0);
        plan.put(key(2,-3),0);
        plan.put(key(3,-3),0);
        plan.put(key(4,-3),-1);
        plan.put(key(0,-4),-1);
        plan.put(key(1,-4),-1);
        plan.put(key(2,-4),-1);
        plan.put(key(3,-4),-1);
        plan.put(key(4,-4),-1);
        return plan;
    }

    private boolean existAnOtherPath(int x, int y, HashMap<String, Integer> plan) {
        String line = "[";
        if (  plan.containsKey(key(x,(y+1))) )
            if (  plan.get(key(x,(y+1))) == 0 )
                line= line+"top ";
        if (   plan.containsKey(key(x,(y-1))) )
            if (  plan.get(key(x,(y-1))) == 0 )
                line= line+"bot ";
        if (   plan.containsKey(key((x+1),y)) )
            if (  plan.get(key((x+1),y)) == 0 )
                line= line+"right ";
        if (   plan.containsKey(key((x-1),y)) )
            if (  plan.get(key((x-1),y)) == 0 )
                line= line+"left ";
        System.out.println("For key:"+key(x,y)+line+"]");
        return (line.length()>2);
    }

}
