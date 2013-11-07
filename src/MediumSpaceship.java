
public class MediumSpaceship extends EnemySpaceship {

	
	public MediumSpaceship(){
		super("cartoon-spaceship-6.png", 3);
		
		SCALE = .20;
		minRandomShot = 100;
		maxRandomShot = 500;
		super.scale(SCALE);
		setNextShot();
		
	}//MediumSpaceship brace
	
	
	
}
