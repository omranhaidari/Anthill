/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package fr.cypno.anthill;

import fr.cypno.anthill.map.Map;

/**
 *
 * @author captaincat
 */
public class Resources {
    private static Map map = null;
    
    public static Map getMap() {
        return map;
    }

    public static void init(Map map) {
        Resources.map = map;
    }
}
