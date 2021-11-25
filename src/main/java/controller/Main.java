package controller;

import controller.actions.Actions;
import controller.actions.ActionInterface;

import model.character.Monstres;
import model.character.Player;
import model.map.Map;
import model.map.MapInterface;
import view.ConsoleView;

import java.util.ArrayList;

public class Main {

    static ArrayList<MapInterface> maps = new ArrayList<>();
    static int stage = 0;
    static MapInterface map;
    static Player player= new Player(new ConsoleView());

    static boolean playerUseExit;
    static int turn=0;

    static ActionInterface action;


    public static void main(String[] args) {
        System.out.println("Debut de la partie");

        playGame();
    }

    private static void playGame() {
        while (true) {
            System.out.println("chargement de la partie");
            playerUseExit = false;

            //Déroulement du jeu
            playStage();

            System.out.println("Une ligne a été tirée entre les astéroïdes, il est maintenant possible de naviguer entre elles.");
            stage++;
            //recupération du joueur
        }
    }

    private static void playStage() {
        while (playerUseExit == false) {
            System.out.println("======niveau : "+stage+"======");

            //Initialisation de la game / new Level
            maps.add(new Map(stage));
            map = maps.get(stage);

            map.initialiseMobs();
            player.setSpawn((Map)map);

            turn=0;
            playWhenAlive();
        }
    }

    private static void playWhenAlive() {
        do{
            if(player.isDead())
                //Affichage " vous êtes mort"
                player.resurrect();

            player.setSpawn((Map) map);
            action = new Actions((Map) map, player, ((Monstres) map.getMobs()).getArrayList());

           // Déroulement de la partie
            while ( ! player.isDead() && ! playerUseExit) {

                map.drawingAll(player);
                System.out.println("[le tour est  numero :"+turn+"]");

                //gestion des actions
                turnPlayer();
                turnMobs();

                turn++;
            }

            if(player.isDead())
                System.out.println("vous etes mort/e");

        } while(player.isDead() && ! playerUseExit);
    }

    private static void turnMobs() {
        if ( ! player.isDead() && ! playerUseExit) {
            while ( ! action.getMobs()) {
                action.playMobs((Map) map, player);
            }
            System.out.println("les mobs on joues");
            action.resetActionMobs();
        }
    }

    private static void turnPlayer() {
        while (!action.getPlayer()) {
            //permet de faire une action au joueur(boire une potion , se heal , bouger , attaquer,...).
            action.playPlayer();
            System.out.println("action:"+action.getPlayer());
        }

        if (action.getPlayer())
            action.resetPlayer();

        playerUseExit=action.playerUseExit(map);
    }
}
