package controller.save;

import java.io.File;

public class FileCreator {
    public static void main(String args[])
    {

        try {

            // Recevoir le fichier
            File f = new File("D:\\example.txt");

            // Créer un nouveau fichier
            // Vérifier s'il n'existe pas
            if (f.createNewFile())
                System.out.println("File created");
            else
                System.out.println("File already exists");
        }
        catch (Exception e) {
            System.err.println(e);
        }
    }
}