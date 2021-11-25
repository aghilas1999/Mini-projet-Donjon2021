package view;

import model.character.Monstre;
import move.Move;

public class PlayerView {
    View view;
    public PlayerView(View view){
        this.view=view;
    }
    public void isInMob(){
        view.handleMove(new Move("You will merge with a monster!\n" +
                " What are you trying to achieve?! An hybrid space monster??"));
    }
    public void isInWall(){
        view.handleMove(new Move("You face a wall"));
    }
    public void isInChest(){
        view.handleMove(new Move("You face a chest"));
    }
    public void hitInVoid(){
        System.out.println("What do you expect when you attacked void?");
    }
    public void healOfMob(Monstre monstre){
        System.out.println("mob heal:"+ monstre.getHeal());
    }


}
