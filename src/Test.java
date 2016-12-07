
public class Test {
	
	public static void main(String[] args) {

		GUI gui = new GUI();
		gui.selectPlayerFrame(800, 800);

		//TODO Just testing, remove later.
		Setups setups = new Setups();
		System.out.println("Player 1 Setup:");
		setups.printSetup(setups.randomPreset(1));
		System.out.println("Player 2 Setup:");
		setups.printSetup(setups.randomPreset(2));
	}
}
