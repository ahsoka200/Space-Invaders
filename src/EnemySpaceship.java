import acm.graphics.GImage;
import acm.util.RandomGenerator;



public class EnemySpaceship extends GImage{
	
	protected double SCALE;
	private int randomShot;
	protected int minRandomShot;
	protected int maxRandomShot;
	private RandomGenerator rgen = RandomGenerator.getInstance();
	private int counter = 0;
	private int hitPoints;
	
	public EnemySpaceship(String image, int HP){
		super(image);
		
		hitPoints = HP;
		
	}
	
	public void setNextShot(){
		
		randomShot = rgen.nextInt(minRandomShot, maxRandomShot);
	
	
	}
	
	
	public void increaseCounter(){
		
		counter = counter + 1;
	}
	
	public boolean shouldFireShot(){
		
		if(counter == randomShot){
			
			counter = 0;
			setNextShot();
			return true;
			
			
		}else{
			return false;
			
		}
		
		
	}
	
	 public void enemyHit(){
		 
		 hitPoints = hitPoints -1;
		 
	 }
	 
	 public boolean isDead(){
		 
		 if(hitPoints <= 0){
			 
			return true;
			 
		 }else{
			 
			return false;
			 
		 }
		 
	 }
	
	
	 
	 

	
	
	
}//EnemySpaceship brace