/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

package fr.cypno.anthill.ant.behavior;

import fr.cypno.anthill.map.*;
import java.util.PriorityQueue;



public class Dijkstra {

//Algorithme de Dijkstra
	final Map map;
	final int cellNb; // nombre de cellules
	final int foodDist; // distance source du plus court chemin recherche
	final int anthillDist; // distance destination du plus court chemin recherche
	int[] dist; // distances a la source
	int[] pred; // predecesseurs de chaque cellule dans le parcours
	boolean[] settled; // cellules traitees par l'algorithme
	PriorityQueue<Cell> unsettled; // sommets a traiter

	// constructeur
	Dijkstra(Map map, int foodDist, int anthillDist) {
		this.map = map;
		cellNb = map.cellNb;
		this.foodDist = foodDist;
		this.anthillDist = anthillDist;
		dist = new int[cellNb];
		pred = new int[cellNb];
		settled = new boolean[cellNb];
		for (int i = 0; i < cellNb; i++) {
			dist[i] = Integer.MAX_VALUE;
			pred[i] = -1;
			settled[i] = false;
		}
		dist[foodDist] = 0;
		pred[foodDist] = foodDist;
		unsettled = new PriorityQueue<Cell>();
		unsettled.add(new Cell(foodDist, 0) {});
	}
	
	// mise a jour de la distance, de la priorite, et du predecesseur d'un sommet
	/*void update(int y, int x) {
		int newDist = dist[x] + map.DistEuclid(x,y); // distance euclidienne à implémenter dans une autre classe 
		if (dist[y] > newDist) {
			pred[y] = x;
			dist[y] = newDist;
			unsettled.add(new Cell(y, newDist) {});
		}
	}
	
	// trouve le prochain sommet de unvisited non traite
	int nextNode() {
		while (!unsettled.isEmpty()) {
			int x = unsettled.poll().cellID;
			if (!settled[x]) return x;
		}
		return -1;
	}*/
	

	// algorithme de Dijkstra complet
	int compute() {
		int x = foodDist;
		return (x == -1 ? -1 : dist[anthillDist]);
	}
	
    
}
