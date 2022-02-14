package com.jeu.classes;

public class Puissance3 {
	
	private int[][] matriceJeu;
	public final int WIDTH=5;
	public final int HEIGHT=5;
	
	public Puissance3()
	{
		matriceJeu=new int[WIDTH][HEIGHT];
		for(int i=0;i<HEIGHT;i++)
		{
			for(int j=0;j<WIDTH;j++)
			{
				matriceJeu[i][j]=0;
			}
		}
	}
	
	public int [][] getMatriceJeu()
	{
		return this.matriceJeu;
	}
	
	public boolean jouer(boolean typeJoueur,int colonne,int[][] matrice)
	{
		int pion;
		
		for(int i=HEIGHT-1;i>=0;i--)
		{
			if(typeJoueur)
				pion=1;
			else 
				pion=2;
			
			if(matrice[i][colonne]==0)
			{
				matrice[i][colonne]=pion;
				return true;
			}
		}
		return false;
	}
	
	public boolean estFinJeu(boolean typeJoueur,int[][] matrice)
	{
		int i=0,j=0;
		Noeud n=new Noeud(typeJoueur, matrice);
		if(n.troisPionsAlignesLigne(typeJoueur)==1000 || n.troisPionsAlignesColonne(typeJoueur)==1000)
		{
			return true;
		}
			
		else 
		{
			for( i=0;i<HEIGHT;i++)
			{
				for( j=0;j<WIDTH;j++)
				{
					if(this.matriceJeu[i][j]==0)
					{
						return false;
						
					}
				}
			}
		}
			
		return true;
	}
	
	@Override
	public String toString()
	{
		
		String res="";
		res+="\n";
		for(int i=0;i<HEIGHT;i++)
		{
			res+="\t";
			for(int j=0;j<WIDTH;j++)
			{
				res+=" | "+matriceJeu[i][j];
			}
			res+=" |\n ";
		}
				
		return res;
	}
	
	public void copieMatrice(int[][] mSource,int[][] mDest)
	{
		for(int i=0;i<HEIGHT;i++)
		{
			for(int j=0;j<WIDTH;j++)
			{
				mDest[i][j]=mSource[i][j];
			}
		}
	}
	
	public Coup alpha_beta(Noeud n,int alpha,int beta,int profondeur)
	{
		Coup meilleurCoup;
		Noeud racine=n;
		if(profondeur==1 || estFinJeu(racine.isMax(),racine.getMatrice()) )
		{
			racine.evaluer();
			meilleurCoup=new Coup(racine.getH(), -1);
			return meilleurCoup;
		}
		
		int[][] copie= new int[HEIGHT][WIDTH];
		Noeud successeur;
		//boolean m;
		Coup coup;
		int bestj=0;
		if(n.isMax())
		{
			for(int j=0;j<WIDTH;j++)
			{
				this.copieMatrice(racine.getMatrice(), copie);
				this.jouer(racine.isMax(), j, copie);
				successeur=new Noeud(!racine.isMax(),copie);
				coup= alpha_beta(successeur,alpha ,beta, profondeur-1) ;
				successeur.setH(coup.getEval());
				if(coup.getEval() > alpha)
				{
					alpha=coup.getEval();
					bestj=j;
				} 
				if(alpha>=beta)
				{
					coup=new Coup(alpha, bestj);
					return coup;
				}
			}
			coup=new Coup(alpha, bestj);
			return coup;
		}
		else
		{
			for(int j=0;j<WIDTH;j++)
			{
				this.copieMatrice(racine.getMatrice(), copie);
				this.jouer(racine.isMax(), j, copie);
				successeur=new Noeud(!racine.isMax(),copie);
				coup= alpha_beta(successeur,alpha ,beta, profondeur-1) ;
				successeur.setH(coup.getEval());
				
				if(coup.getEval() < beta)
				{
					beta=coup.getEval();
					bestj=j;
				}
				if(beta<=alpha)
				{
					coup=new Coup(beta, bestj);
					return coup;
				}
			}
			coup=new Coup(beta, bestj);
			return coup;
		}
		
	}
}














