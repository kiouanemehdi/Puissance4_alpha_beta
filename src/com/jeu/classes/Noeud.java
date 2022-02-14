package com.jeu.classes;

public class Noeud {
	
	private int[][] matrice;
	private boolean max;
	private int h;
	
	public Noeud(boolean max,int[][] matrice)
	{
		this.max=max;
		this.matrice=matrice;
	}
	public int getH()
	{
		return this.h;
	}
	public void setH(int h)
	{
		this.h=h;
	}
	public int [][] getMatrice()
	{
		return this.matrice;
	}
	public Boolean isMax()
	{
		return this.max;
	}
	
	@Override
	public String toString()
	{
		String res="";
		System.out.print("\n H = "+this.h);
		System.out.print("\n Max = "+this.max);
		res+="\t";
		for(int i=0;i<matrice.length;i++)
		{
			
			for(int j=0;j<matrice[0].length;j++)
			{
				res+=" | "+matrice[i][j];
			}
			res+="\n ";
		}
				
		return res;
	}
	
	public int troisPionsAlignesLigne(boolean typeJoueur)
	{
		int pion;
		if(typeJoueur)
			pion=1;
		else 
			pion=2;
		int count;
		for(int i=0;i<matrice.length;i++)
		{
			count=0;
			for(int j=0;j<matrice[0].length;j++)
			{
				if(matrice[i][j]==pion)
					count++;
				
				else
					count=0;

				if(count==3)
					return 1000;
			}
		}
		return 0;

	}
	public int troisPionsAlignesColonne(Boolean typeJoueur)
	{
		int pion;
		if(typeJoueur)
			pion=1;
		else 
			pion=2;
		int count;
		for(int j=0;j<matrice[0].length;j++)
		{
			count=0;
			for(int i=0;i<matrice.length-2;i++)
			{
				//if(matrice[i][j]==pion && matrice[i][j]==matrice[i+1][j] && matrice[i][j]==matrice[i+2][j])
					//return 1000;
				if(matrice[i][j]==pion)
					count++;
				
				else
					count=0;

				if(count==3)
					return 1000;
			}
		}
		return 0;
	}
	
	public int troisPionsPossiblesLigne(Boolean typeJoueur)
	{
		int pion,somme=0;
		
		if(typeJoueur)
			pion=1;
		else 
			pion=2;
		
		for(int i=0;i<matrice.length;i++)
		{
			for(int j=0;j<matrice[0].length-2;j++)
			{
				if(matrice[i][j]==pion && matrice[i][j]==matrice[i][j+1] && matrice[i][j+2]==0)
					somme+=200;
				
				else if(matrice[i][j]==pion && matrice[i][j+1]==0)
					somme+=30;
				
				if(j==matrice[0].length-3 && matrice[i][j+1]==pion && matrice[i][j+2]==0)
					somme+=30;
			}
		}
		
		return somme;
	}
	public int troisPionsPossiblesColonne(Boolean typeJoueur)
	{
		int pion,somme=0;
		
		if(typeJoueur)
			pion=1;
		else 
			pion=2;
		
		for(int j=0;j<matrice[0].length;j++)
		{
			for(int i=0;i<matrice.length-2;i++)
			{
				if(matrice[i][j]==pion && matrice[i][j]==matrice[i+1][j] && matrice[i+2][j]==0)
					somme+=200;
				
				else if(matrice[i][j]==pion && matrice[i+1][j]==0)
					somme+=30;
				
				if(i==matrice.length-3 && matrice[i+1][j]==pion && matrice[i+2][j]==0)
					somme+=30;
			}
		}
		return somme;
	}
	
	public void evaluer()
	{
		int h;
		h = -2*troisPionsAlignesLigne(false) + troisPionsAlignesLigne(true)
			-2*troisPionsAlignesColonne(false) + troisPionsAlignesColonne(true)
			-2*troisPionsPossiblesLigne(false) + troisPionsPossiblesLigne(true)
			-2*troisPionsPossiblesColonne(false) + troisPionsPossiblesColonne(true);
		
		this.setH(h);
	}
	
	

}
