package Input;


import java.awt.AWTException;
import java.awt.Robot;
import java.awt.event.FocusEvent;
import java.awt.event.FocusListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.event.MouseMotionListener;

import Graphics.Display;

public class InputHandler implements KeyListener,FocusListener,MouseListener, MouseMotionListener {

	public boolean [] key = new boolean[68836];
	public static int mouseX;
	public static int mouseY;
	public static int mouseButton;
	public static int clickCount;
	
	public void mouseClicked(MouseEvent e) {
		mouseButton = e.getButton();
		clickCount++;
		System.out.println("x: " + mouseX + " | " + "y: " + mouseY + " | " + "count: " + clickCount);
		
		
	}

	public void mouseEntered(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseExited(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mousePressed(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseReleased(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void focusGained(FocusEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void focusLost(FocusEvent e) {
		for (int i = 0;i < key.length;i++){
			key[i]=false;
		}
		System.out.println("focus lost");
	}

	public void keyPressed(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if (keyCode > 0 && keyCode < key.length){
			key[keyCode]=true;
			
		}
		//System.out.println("key : " + keyCode + "was pressed");
	}

	public void keyReleased(KeyEvent e) {
		int keyCode = e.getKeyCode();
		if(keyCode > 0 && keyCode < key.length){
			key[keyCode]=false;
			
		}
		//System.out.println("key : " + keyCode + "was released");
		
	}

	public void keyTyped(KeyEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseDragged(MouseEvent e) {
		// TODO Auto-generated method stub
		
	}

	public void mouseMoved(MouseEvent e) {
		mouseX = e.getX();
		mouseY = e.getY();
	
	}

}
