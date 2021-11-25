package controller;

import model.character.Monstre;
import model.character.Player;
import model.object.Object;
import model.map.Map;
import move.Move;


import java.util.ArrayList;
import java.util.HashMap;
import java.util.Scanner;

public class KeyBoardController {
    private Map map;
    private Player player;
    private Move move;

    public KeyBoardController(Map map, Player player, Move move){
        this.map = map;
        this.move = move;
        this.player = player;
    }

    /**
     * lis une touche au clavier et retourne l'action voulu par le joueur
     * @return boolean hasPlay
     */
    public boolean read() {

        Scanner scanner = new Scanner(System.in);

        boolean hasPlay = false;
        while (! hasPlay) {
            System.out.println("enter your action");
            switch (scanner.next()) {

                case "z":
                    hasPlay = player. goTo( move, "top" );
                    break;
                case "d":
                   hasPlay = player. goTo( move, "right" );

                    break;
                case "s":
                    hasPlay =  player.goTo( move, "bot" );
                    break;
                case "q":
                    hasPlay =  player.goTo( move, "left" );
                    break;
                case "a":
                    System.out.println("→ Attaquer la cible ");
                    hasPlay = player.toTargetMobAndHit(map,move);
                    break;
                case "e":
                    System.out.println("→ ouverture de l'inventaire");
                    player.getInventory().print(this);
                    break;
                case "m":
                    map.drawingAll(player);
                    break;
                case "5":
                    player.getChest(map);
                    map.drawingAll(player);
                /**
                 * case "
                 */
                default:
                    break;
            }
        }
        return hasPlay;
    }

    /**
     * Attaque dans la direction choisit par le joueur frapper au clavier)
     * @param map
     * @param monstres
     * @return Boolean hasHit
     */
    public Boolean selectTarget(Map map, ArrayList<Monstre> monstres) {

        Scanner scanner = new Scanner(System.in);

        boolean hasHit = false;
        System.out.println("enter the direction: ↑:8  ↓:2 →:6 ←:4 ");
        switch (scanner.next()) {

            case "8":
                hasHit = player.attaqueTo(map, monstres, "top");
                break;
            case "2":
                hasHit = player.attaqueTo(map, monstres, "bot");

                break;
            case "6":
                hasHit = player.attaqueTo(map, monstres, "rigth");
                break;
            case "4":
                hasHit = player.attaqueTo(map, monstres, "left");
                break;
            default:
                break;
        }
        return hasHit;
    }

    /**
     * returne 0 for close, -1 for back, 1 for next
     * @param keys
     * @param inventory
     * @return int actionBag
     */
    public String inventaire(ArrayList<Integer> keys, HashMap<Integer, Object> inventory) {
        Scanner scanner = new Scanner(System.in);

        System.out.println(" Select item : (index) | Close bag: c | Next : n | Back : b  ");

        String result = scanner.next();
        switch (result) {
            case "c":
                return "close";
            case "n":
                return "next";
            case "b":
                return "back";
            default:
                return result;
        }
    }

    /**
     * Read and apply an action for the selected item
     * @param object
     */
    public void item(Object object) {
        object.description();
        System.out.println("Equip : e | Drop : d | Close : otherKey");
        Scanner scanner = new Scanner(System.in);
        switch (scanner.next()) {
            case "e":
                object.equip(player);
                break;
            case "d":
                System.out.println("Item dropped");
                player.dropBag(object);
                break;
            default:
                break;
        }

    }
}



