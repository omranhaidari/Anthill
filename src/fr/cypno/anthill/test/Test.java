package fr.cypno.anthill.test;

import fr.cypno.anthill.map.*;
import fr.cypno.anthill.ant.*;
import fr.cypno.anthill.ant.exceptions.NotAnthillCellException;
import fr.cypno.anthill.ant.exceptions.NotFoodCellException;
import java.io.File;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Test {

    public static void main(String[] args) {
        try {
            Map map = new Map(System.getProperty("user.dir") + File.separator + "ressources" + File.separator + "maps" + File.separator + "map.txt");
            map.afficher();
        } catch (Exception e) {
            e.printStackTrace();
        }

        try {
            Anthill ah = new Anthill();
            Ant a = new Ant(25, 10, 4);

            System.out.println("Orientation de la fourmi : " + a.getDirection());
            Food f1 = new Food(14);
            Food f2 = new Food(12);
            Empty e1 = new Empty(0);
            Empty e2 = new Empty(0);
            System.out.println("Quantité de nourriture de la cellule f1 : " + f1.getQuantity());
            System.out.println("Quantité de nourriture de la cellule f2 : " + f2.getQuantity());
            a.getCells().add(ah);
            a.getCells().add(f1);
            a.getCells().add(f2);
            a.getCells().add(e1);
            a.getCells().add(e2);

            a.setPosition(f1);
            System.out.println("Déplacement sur la cellule f1");
            a.pullFood();
            System.out.println("Quantité de nourriture de la cellule f1 : " + f1.getQuantity());
            System.out.println("Quantité de nourriture sur la fourmi : " + a.getFood());
            System.out.println("Capacité fourmi : " + a.getFoodCapacity());
            a.setPosition(f2);
            System.out.println("Déplacement sur la cellule f2");
            a.pullFood();
            System.out.println("Quantité de nourriture de la cellule f2 : " + f2.getQuantity());
            System.out.println("Quantité de nourriture sur la fourmi : " + a.getFood());
            System.out.println("Capacité fourmi : " + a.getFoodCapacity());

            a.setDirection(2);
            System.out.println("Orientation de la fourmi : " + a.getDirection());
            a.setPosition(ah);
            System.out.println("Déplacement sur la cellule ah");
            System.out.println("Quantité nourrite AntHill avant : " + ah.getQuantity());
            a.pushFood();
            System.out.println("Quantité nourrite AntHill après : " + ah.getQuantity());
            System.out.println("Quantité de nourriture sur la fourmi : " + a.getFood());

            a.setPosition(e2);
            System.out.println("Déplacement sur la cellule e2");
            //a.pushFood();

        } catch (NotFoodCellException | NotAnthillCellException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
