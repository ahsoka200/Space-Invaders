import acm.graphics.*;
import acm.program.GraphicsProgram;


public class Rocket extends GImage {

	private int wayrocketIsMoving; //move left is 0. move right is 2, and stay still is 1.

	public Rocket(){

		super("spaceship.png", SpaceInvaders.APPLICATION_WIDTH/2 - 10, 600);

		scale(.25);

		wayrocketIsMoving = 1;

	}//Rocket brace

	public void setMovingLeft(){

		wayrocketIsMoving= 0;

	}

	public void setMovingRight(){

		wayrocketIsMoving = 2;

	}


	public void setNotMoving(){

		wayrocketIsMoving = 1;

	}


	public void move(int rocketSpeed){

		//makes rocket move right
		if(wayrocketIsMoving == 2){
			move(rocketSpeed ,0);

		}

		//makes rocket move left
		if(wayrocketIsMoving == 0){
			move(-rocketSpeed ,0);


		}

		//left side off screen prevent
		if(this.getX() <= 0){

			this.setLocation(0, getY());

			wayrocketIsMoving = 1;
		}
		
		//right side off screen prevent
		if(this.getX() >= SpaceInvaders.APPLICATION_WIDTH - this.getWidth()){

			this.setLocation(SpaceInvaders.APPLICATION_WIDTH - this.getWidth(), getY());

			wayrocketIsMoving = 1;
		}

	}//move brace

}//end brace
