package GameBoard;



import java.awt.Color;
import java.awt.Image;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JComponent;

import Cell.Cell;
//fire@WILL!
import Player.Player;


public class Piece  {
	private BufferedImage image; 
	private Color bcolor;
	private String filename;
	private int rank;
	private Player pID;
	private Position position;
	private ImageIcon icon;
	//private boolean visible; //TODO Make screen recognise piece and either hide it or not;
	
	public Piece(Player pID, int rank){
		this.pID = pID;
		this.rank = rank;
		makeIcon();
	}
	
	private void makeIcon() {
		filename="resources\\graphics\\" + rank + ".png";
		bufferImageTocon(filename);
		
		
	}
	
	private ImageIcon opponentIcon(){
		
		filename="resources\\graphics\\op.png";
		bufferImageTocon(filename);
		return icon;
		

	}



	 public void bufferImageTocon(String dir){
	     File file = new File(dir);
	     try {
	         this.image = ImageIO.read(file);
	     } catch (IOException ex) {
	         ex.printStackTrace();
	     } 
	     
	     icon = new ImageIcon(image); 
	 }
	 

	public void setPosition(Position pos){position = pos;}
	public Position getPosition(){return position;}
	public void setRank(int rank){
		
		
		
		this.rank = rank;}
	public int getRank(){return rank;}
	public Player getPID(){return pID;}
	
	public Piece win(Piece defenseP){
		// TODO add check for pID, shouldn't be two pieces of the same player.
		int attack = this.getRank();
		int defense = defenseP.getRank();
		if(defense == 0){
			//endgame();
			return null;
		}
		if(attack == defense){
			Piece draw = new Piece(null,-10);
			return draw;
		}
		else{
			if((attack != 1 && defense != 10) || (attack!=3 && defense!= 11)){
							if(attack > defense){
								
								return this;
							}
							else {
								return defenseP;
							}
			}
			else				
				return this;	
		}
	}
	
	public boolean validWalk(int spaces){
		if(getRank()==0 || getRank()==11){
			return false;
		}
		else{
			if(getRank()!=2 && spaces > 1){
					return false;
				}
			
				return true;
		}
	}
	public ImageIcon drawPiece(Player pID) {
		if(pID == this.pID){
			
		
		Image img = icon.getImage() ;  
		Image newimg = img.getScaledInstance( 80, 80,  java.awt.Image.SCALE_SMOOTH ) ;  
		icon = new ImageIcon( newimg );
		return icon;
		}
		
		return opponentIcon();

	}

	public Icon getIcon() {
		icon= drawPiece(pID);
		return icon;
	}
	
	
}
