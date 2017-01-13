package fr.cypno.anthill.test;

import fr.cypno.anthill.map.Map;


public class Test {
    public static void main(String[] args) throws Exception{
        try{
            Map map = new Map();
            map.afficher();
        }catch(Exception e){
            e.printStackTrace();
    }
        
    }
}
