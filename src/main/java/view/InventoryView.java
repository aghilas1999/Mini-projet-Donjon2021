package view;

import controller.KeyBoardController;
import model.character.inventoryPack.Equipement;
import model.object.Object;

import java.util.ArrayList;
import java.util.HashMap;

public class InventoryView {

    HashMap<Integer, Object> inventory = new HashMap<>();
    KeyBoardController keyBoardController;
    ArrayList<Integer> keys = new ArrayList<>();
    Equipement equipement ;

    public InventoryView(ArrayList<Object> bag, KeyBoardController keyBoardController, Equipement stuff){
        int index = 0;
        for(Object object : bag){
            inventory.put(index, object);
            keys.add(index);
            index++;
            this.keyBoardController = keyBoardController;
            equipement = stuff;
        }
    }

    public void print() {
        boolean close = false;
        int i = 0;
        int page = 0;
        while (!close) {

            printPages(i, page);
            String action = keyBoardController.inventaire(keys, inventory);
            switch (action) {
                case "next":
                    if (i + 40 <= keys.size())
                        i = i + 40;
                    break;
                case "back":
                    if (i + 40 >= 0)
                        i = i - 40;
                    break;
                case "close":
                    close = true;
                    break;
                default:
                    if(isParsable(action)) {
                        int key = Integer.parseInt(action);
                        if (inventory.containsKey(key)) {
                            keyBoardController.item(inventory.get(key));
                            close=true;
                        }
                    }else
                        close=true;
                    break;
            }
        }
    }


    public static boolean isParsable(String input) {
        try {
            Integer.parseInt(input);
            return true;
        } catch (final NumberFormatException e) {
            return false;
        }
    }

    private void printPages(int i, int page) {
        //Header
        System.out.println("================ Inventaire ================");
        System.out.println("---------------- Equipement ----------------\n");
        printEquipement();
        System.out.println("\n================= Bag Page:" + page + " =================");

        //Body
        int lines=0;
        while (lines<5 &&  i < keys.size() ){
            String names = buildLineName(i);
            System.out.println(names.substring(0, names.length() - 1) + "]");
            i = i+10;
            lines++;
        }

        // Footer
        buildFooter(i, page);
        System.out.println("\n\n\n");
    }

    private void printEquipement() {
        System.out.println("Armor: "+equipement.getNameAmor() +
                "[ Resistance : "+equipement.getResistance()+"]");
        System.out.println("Weapon: "+equipement.getNameWeapon() +
                "[ Range : "+equipement.getRange()+" | "+
                  "Damage : "+equipement.getDamage()+" ]");
    }

    /**
     * Construit une ligne de 10 items avec leur index
     * @param i
     * @return String lines
     */
    private String buildLineName(int i){
        String line = "[ (index) item | ";
        for (int j = i; j < j + 10 && j < keys.size(); j++) {
            line = line + "(" + keys.get(j) + ")" + inventory.get(keys.get(j)).getName() + " |";
        }
        return line;
    }

    /**
     * Build and print footer
     * @param i
     * @param page
     */
    private void buildFooter(int i, int page){
        if(i>keys.size() && page==0)
            System.out.println("================= Bag Page:" + page + " =================");
        else
        if(page==0 && i<keys.size())
            System.out.println("================= Bag Page:" + page + " == next>>========");
        else
        if(i==keys.size() && page!=0)
            System.out.println("========<<back == Bag Page:" + page + " =================");
        else
            System.out.println("========<<back == Bag Page:" + page + " == next>>========");
    }

}
