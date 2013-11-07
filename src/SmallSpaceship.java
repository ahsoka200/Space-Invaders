


public class SmallSpaceship extends EnemySpaceship {

	public SmallSpaceship(){
		super("cartoon spaceship 1.png", 1);
		
		SCALE = .13;
		minRandomShot = 100;
		maxRandomShot = 1000;
		super.scale(SCALE);
		setNextShot();
		
		
	}//SmallSpaceship brace
	
	
}


