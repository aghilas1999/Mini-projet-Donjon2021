import org.junit.Test;

import java.util.ArrayList;

public class MonstreTest {

    @Test
    public void randtest(){
        ArrayList<String> destinations = new ArrayList<>();
        destinations.add("left");
        destinations.add("right");
        destinations.add("top");
        destinations.add("bot");

        int bot=0, top=0, left=0, right=0;
        for( int i = 0; i<1000 ; i++) {
            String destination = destinations.get( (int) ( Math.random() * (destinations.size())));
            switch (destination){
                case "bot":
                    bot++;
                    break;
                case "top":
                    top++;
                    break;
                case "left":
                    left++;
                    break;
                case "right":
                    right++;
                    break;
                default:
                    break;
            }
        }
        System.out.println("left: "+left+"\nright: "+right+"\ntop:  "+top+"\nbot: "+bot);
    }
}
