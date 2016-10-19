package Cell;

import java.awt.Color;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.awt.image.ImageObserver;
import java.io.IOException;
import java.io.InputStream;

import javax.imageio.ImageIO;
import javax.swing.Icon;
import javax.swing.ImageIcon;
import javax.swing.JButton;

import GameBoard.Piece;
import GameBoard.Position;
import GameBoard.TurnState;


//fire@WILL!
//Cell state interface for the sake of better structure within the program
public abstract class Cell extends JButton {
	public Piece content;
	private ImageIcon image;
    private boolean empty=true;
    private boolean wasClicked = false;    
    private Position position ;
    private int n=0;
    private TurnState turnState;
	public Cell(){
		super();
        
		content = null;
		setBackground(Color.WHITE);
		
		this.addActionListener(new ActionListener() {
	
			public void actionPerformed(ActionEvent arg0) {
				n++;
				System.out.println("button "+position.getX()+"|"+position.getY()+" was clicked" + n+ " times "+content.getRank());
				Object source = arg0.getSource();
				
				 //make the selection green
                   ((Cell)source).setBackground(Color.green);
               
				//send this button to the buttonpress class
				GameBoard.TurnState.buttonPress(((Cell)source));
				
				//setBackground(Color.blue);
				//turnState.handle(pos);
				
			}
		});
		}
	
	
	public Piece getContent(){
		return content;
	}
	
	
	public boolean isEmpty(){
		return empty;
	}
	
	
	public void setTurnState(TurnState turnState){
		
		this.turnState = turnState;
	}
	public Position getPostion(){
		return position;
	}
	public void setPosition(Position pos) {
		this.position = pos;
	}

	
	
}
