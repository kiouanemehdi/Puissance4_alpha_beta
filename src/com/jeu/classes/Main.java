package com.jeu.classes;

import java.util.Scanner;

public class Main {

	public static void main(String[] args) {
		// TODO Auto-generated method stub
		Noeud node;
        Coup coup;
        Puissance3 p3 = new Puissance3();
        int col = 0;
        System.out.println(p3);
        int count=0;
        Scanner scan = new Scanner(System.in);
        while (true) 
        {

        	if(count %2==0)
        	{
        		 do {
	                System.out.print("Donnez le numéro de la colonne : ");
	                col = scan.nextInt()-1;
        		 } while ((col > 4) || (col < 0));

        	
	        	System.out.print(" \n votre tour : \n");
	            p3.jouer(true, col, p3.getMatriceJeu());
	            System.out.println(p3);
	
	            if (p3.estFinJeu(true, p3.getMatriceJeu())) {
	                System.out.println("vous avez gagné !(MAX)");
	                break;
	            }
        	}
        	else {
        		node = new Noeud(true, p3.getMatriceJeu());
        		
	            coup = p3.alpha_beta(node, Integer.MIN_VALUE, Integer.MAX_VALUE, 4);
	            node.setH(coup.getEval());
	            System.out.print(" \n  tour de la machine: \n");

	            p3.jouer(false, coup.getColonne(), p3.getMatriceJeu());
	            System.out.println(p3);
	
        		if (p3.estFinJeu(false, p3.getMatriceJeu())) {
	                System.out.println("Machine a gagne (MIN) !");
	                break;
	            }
	            
        	}
            
            
            count++;
        }
        scan.close();
        
        
	}

}
