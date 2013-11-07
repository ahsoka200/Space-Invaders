public class LargeSpaceship extends EnemySpaceship{
	
	
	public LargeSpaceship(){
		super("cartoon-spaceshp2.png", 5);
		
		SCALE = .30;
		minRandomShot = 50;
		maxRandomShot = 400;
		super.scale(SCALE);
		setNextShot();
		
	}//LargeSpaceship brace
	
	
	
	
	
}//end brace 