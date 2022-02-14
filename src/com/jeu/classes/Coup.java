package com.jeu.classes;

public class Coup {
	
	private int eval;
	private int colonne;
	
	public Coup(int val,int c)
	{
		this.eval=val;
		this.colonne=c;
	}

	public int getEval() {
		return eval;
	}

	public int getColonne() {
		return colonne;
	}

}
