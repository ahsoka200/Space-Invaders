import java.applet.AudioClip;
import java.awt.Color;
import java.awt.Font;
import java.awt.event.KeyEvent;
import java.awt.event.MouseEvent;
import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.FileNotFoundException;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.util.ArrayList;

import acm.graphics.*;
import acm.program.GraphicsProgram;
import acm.util.MediaTools;

public class SpaceInvaders extends GraphicsProgram {

	public static final int APPLICATION_WIDTH = 960;
	public static final int APPLICATION_HEIGHT = 720;

	private Rocket rocket;

	private GImage backround;

	public int SCORE = 0;

	private GLabel scoreLabel;

	private int LIVES = 5;

	private GLabel livesLabel;

	private GLabel highScoreLabel;

	private boolean start = false;

	private GLabel messageLabel = new GLabel("");

	ArrayList<GRect> bullets = new ArrayList<GRect>();

	ArrayList<GRect> enemyBullets = new ArrayList<GRect>();

	private int level = 1;

	private int shotFrequence = 12;

	private int shotCounter = shotFrequence;





	ArrayList<EnemySpaceship> enemys = new ArrayList<EnemySpaceship>();


	private double bulletSpeed = 12;

	private int pauseLength = 20;

	private int rocketSpeed = 10;

	private int enemySpeed = 5;

	private int enemyChangeDirection = 58;

	private boolean enemyDirection = true; //true is right

	private int counter = 0;

	private int enemyMoveDown = 5;

	private AudioClip enemyDieSound = MediaTools.loadAudioClip("Electricty Surge.wav");

	private AudioClip youShootSound = MediaTools.loadAudioClip("Tennis Serve.wav");

	private AudioClip youGetHit = MediaTools.loadAudioClip("Hydraulics Engaged.wav");

	private AudioClip enemyShootSound = MediaTools.loadAudioClip("Warp Engineering 01.wav");

	public int HighScore;



	public void run(){

		//runs setup
		setup();

		gameLoop();


	}//run brace



	public void gameLoop(){


		boolean alreadyStarted = false;

		while (true){



			//necessary to get start variable to work
			if(!alreadyStarted){
				println(start);
				if(start){
					alreadyStarted = true;
				}
			}

			//game runs after clicked.
			if(start == true){

				shotCounter++;

				//move spaceship
				rocket.move(rocketSpeed);


				moveAllBullets();

				allEnemysMove();

				bulletEnemyColitionDitection();

				enemysFireShots();

				moveAllEnemyBullets();

				enemyBulletSpaceshipDetection();

				pause(pauseLength);


				if(enemys.size() == 0){

					statusMessage("You win!");

					pause(2000);

					statusMessage("Click to start");


					start = false;

					level = level +1;

					levelReset();

					createEnemySpaceships(level);



				}

				if(LIVES == 0){

					statusMessage("you lost :(");

					if(SCORE > HighScore){
						
						HighScore = SCORE;
						writeHighSocre();
					}
					
					
					
					highScoreLabel();

					
					
					
					
					break;



				}


			}//if(start == true) brace




		}


	}//gameLoop brace


	public void statusMessage(String message){

		remove(messageLabel);
		messageLabel = new GLabel(message,100, 400);
		messageLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		messageLabel.setColor(Color.white);
		messageLabel.setLocation(APPLICATION_WIDTH/2 - messageLabel.getWidth()/2,300);
		add(messageLabel);

	}



	public void mouseClicked(MouseEvent mouse){

		start = true;
		remove(messageLabel);

	}



	public void setup(){

		
		reedHighscore();

		backround = new GImage("alien-space-night-dark.jpg", 0, 0);
		add(backround);

		backround.scale(1.2);


		rocket = new Rocket();
		add(rocket);

		addKeyListeners();

		statusMessage("Click To Start,         			Highscore: " + HighScore);

		createEnemySpaceships(level);

		addMouseListeners();

		//prints score on screen
		scoreLabel();

		//prints lives on screen
		livesLabel();





		creatCoverBlock();

	}//setup brace



	private void createEnemySpaceships(int level){

		if(level == 1){

			for(int i = 0; i < 7; i++){

				EnemySpaceship enemy = new SmallSpaceship();
				add(enemy,100 * (i),50);
				enemys.add(enemy);

			}
			//creates second row of spaceships
			for(int k = 0; k < 7; k++){

				EnemySpaceship enemy = new SmallSpaceship();
				add(enemy,100 * (k),125);
				enemys.add(enemy);


			}
		}


		if(level == 2){


			for(int i = 0; i < 7; i++){

				EnemySpaceship enemy = new SmallSpaceship();
				add(enemy,100 * (i),200);
				enemys.add(enemy);

			}
			//creates second row of spaceships
			for(int k = 0; k < 7; k++){

				EnemySpaceship enemy = new SmallSpaceship();
				add(enemy,100 * (k),125);
				enemys.add(enemy);


			}

			//creates row of M spaceships
			for(int k = 0; k < 5; k++){

				EnemySpaceship enemy = new MediumSpaceship();
				add(enemy,150 * (k),25);
				enemys.add(enemy);

			}


		}




		if(level == 3){


			for(int i = 0; i < 5; i++){

				EnemySpaceship enemy = new MediumSpaceship();
				add(enemy,150f * (i),195);
				enemys.add(enemy);

			}
			//creates second row of spaceships
			for(int k = 0; k < 5; k++){

				EnemySpaceship enemy = new MediumSpaceship();
				add(enemy,150 * (k),110);
				enemys.add(enemy);


			}

			//creates row of M spaceships
			for(int k = 0; k < 5; k++){

				EnemySpaceship enemy = new MediumSpaceship();
				add(enemy,150 * (k),25);
				enemys.add(enemy);

			}


		}



		if(level == 4){


			//makes hard spaceships
			for(int i = 0; i < 5; i++){

				EnemySpaceship enemy = new LargeSpaceship();
				add(enemy,150 * (i),25);
				enemys.add(enemy);

			}


			//makes hard spaceships
			for(int i = 0; i < 5; i++){

				EnemySpaceship enemy = new LargeSpaceship();
				add(enemy,150 * (i),100);
				enemys.add(enemy);

			}



			for(int i = 0; i < 5; i++){

				EnemySpaceship enemy = new MediumSpaceship();
				add(enemy,150 * (i) + 17,175);
				enemys.add(enemy);

			}


		}

		if(level >= 5){


			//makes hard spaceships
			for(int i = 0; i < 5; i++){

				EnemySpaceship enemy = new LargeSpaceship();
				add(enemy,150 * (i),25);
				enemys.add(enemy);

			}


			//makes hard spaceships
			for(int i = 0; i < 5; i++){

				EnemySpaceship enemy = new LargeSpaceship();
				add(enemy,150 * (i),125);
				enemys.add(enemy);

			}



			for(int i = 0; i < 5; i++){

				EnemySpaceship enemy = new LargeSpaceship();
				add(enemy,150 * (i),225);
				enemys.add(enemy);



			}


		}




	}







	public void keyPressed(KeyEvent a) {


		// if(e.getKeyChar()=='w' || e.getKeyChar()=='W'){

		if(a.getKeyChar() == 'a' || a.getKeyChar() == 'A'){

			rocket.setMovingLeft();

		}


		if(a.getKeyChar() == 'd' || a.getKeyChar() == 'D'){

			rocket.setMovingRight();

		}



	}//keyPressed brace

	public void keyReleased(KeyEvent d) {

		println("key released");




		//if space bar is pushed
		if(d.getKeyCode() == 32 && shotCounter >shotFrequence){

			GRect bullet = new GRect(rocket.getX() + rocket.getWidth()/2 - 1, rocket.getY(),2,10);
			bullet.setColor(Color.yellow);
			bullet.setFilled(true);
			bullet.setFillColor(Color.yellow);
			add(bullet);

			bullets.add(bullet);

			youShootSound.play();

			shotCounter = 0;


		}




		if(d.getKeyChar() == 'a' || d.getKeyChar() == 'A' 
			||d.getKeyChar() == 'd' || d.getKeyChar() == 'D'){

			rocket.setNotMoving();
		}



	}//keyReleased brace


	public void scoreLabel(){


		scoreLabel = new GLabel("score: " + SCORE, 30, 700);
		scoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		scoreLabel.setColor(Color.white);
		add(scoreLabel);

	}//scoreLabel brace


	public void livesLabel(){


		livesLabel = new GLabel("lives: " + LIVES, 800, 700);
		livesLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		livesLabel.setColor(Color.white);
		add(livesLabel);


	}//livesLabel brace


	public void highScoreLabel(){

		highScoreLabel = new GLabel("Highscore: " + HighScore, 100, 304);
		highScoreLabel.setFont(new Font(Font.SANS_SERIF, Font.BOLD,30));
		highScoreLabel.setColor(Color.white);
		add(highScoreLabel);



	}

	public void allEnemysDown(){

		for(int j = 0; j < enemys.size(); j++){

			EnemySpaceship currentEnemy = enemys.get(j);

			currentEnemy.move(0, enemyMoveDown);

			if(currentEnemy.getY() + currentEnemy.getHeight() >=500){

				LIVES = 0;

			}

		}
	}


	public void allEnemysMove(){


		//move all enemies
		for(int j = 0; j < enemys.size(); j++){

			EnemySpaceship currentEnemy = enemys.get(j);


			if(enemyDirection == true){
				//move right
				currentEnemy.move(enemySpeed, 0);

			}else{

				//move left
				currentEnemy.move(-enemySpeed, 0);

			}

		}


		//keeps track of enemy Direction
		counter = counter + 1;


		if(counter == enemyChangeDirection){
			counter = 0;

			allEnemysDown();

			//changes enemy direction
			if(enemyDirection == false){

				enemyDirection = true;

			}else{

				enemyDirection = false;

			}

		}

	}//allEnemysMoveRL brace


	public void moveAllBullets(){

		//move all bullets
		for(int i = 0; i < bullets.size(); i++){

			GRect currentBullet = bullets.get(i);

			currentBullet.move(0, -bulletSpeed);


			//remove bullet if goes off screen
			if(currentBullet.getY() < -10){

				bullets.remove(currentBullet);
				remove(currentBullet);
			}

		}

	}//moveAllBullets brace



	public void bulletEnemyColitionDitection(){


		//detects bullet collitios with spaceship
		for(int i = 0; i < bullets.size(); i++){

			GRect currentBullet = bullets.get(i);

			GObject object = getElementAt(currentBullet.getX() + currentBullet.getWidth()/2,currentBullet.getY() -1);

			if(object != null && object !=backround && object instanceof EnemySpaceship){

				
				SCORE = SCORE + 10;
				scoreLabel.setLabel("score: "+ SCORE);

				
				((EnemySpaceship) object).enemyHit();


				if(((EnemySpaceship) object).isDead() == true){


					remove(object);
					enemys.remove(object);



				}


				bullets.remove(currentBullet);	
				remove(currentBullet);

				enemyDieSound.play();

			}

			if(object instanceof CoverBlock){

				((CoverBlock) object).blockHit();

				bullets.remove(currentBullet);	
				remove(currentBullet);


				if(((CoverBlock) object).isDestriod()){

					remove(object);


				}


			}

		}
	}


	public void enemysFireShots(){

		//move all enemies
		for(int j = 0; j < enemys.size(); j++){

			EnemySpaceship currentEnemy = enemys.get(j);
			currentEnemy.increaseCounter();
			boolean Fire = currentEnemy.shouldFireShot();

			if(Fire == true){

				GRect bullet = new GRect(currentEnemy.getX() + currentEnemy.getWidth()/2 - 1, currentEnemy.getY(),2,10);
				bullet.setColor(Color.red);
				bullet.setFilled(true);
				bullet.setFillColor(Color.red);
				add(bullet);

				enemyBullets.add(bullet);

				enemyShootSound.play();


			}
		}

	}//enemysFireShots brace



	public void moveAllEnemyBullets(){

		//move all Enemybullets
		for(int i = 0; i < enemyBullets.size(); i++){

			GRect currentBullet = enemyBullets.get(i);

			currentBullet.move(0, bulletSpeed);


			//remove bullet if goes off screen
			if(currentBullet.getY() > APPLICATION_HEIGHT){

				enemyBullets.remove(currentBullet);
				remove(currentBullet);
			}

		}

	}//moveAllBullets brace



	public void enemyBulletSpaceshipDetection(){

		for(int i = 0; i < enemyBullets.size(); i++){


			GRect currentBullet = enemyBullets.get(i);


			GObject object = getElementAt(currentBullet.getX() + currentBullet.getWidth()/2,currentBullet.getY() - 1);


			if(object == rocket){


				LIVES = LIVES - 1;

				livesLabel.setLabel("lives: "+ LIVES);


				enemyBullets.remove(currentBullet);
				remove(currentBullet);


				youGetHit.play();

			}

			if(object instanceof CoverBlock){

				((CoverBlock) object).blockHit();

				enemyBullets.remove(currentBullet);
				remove(currentBullet);

				if(((CoverBlock) object).isDestriod()){

					remove(object);


				}
			}



		}



	}//enemyBulletSpaceshipDetection()


	public void levelReset(){


		//for(int i = 0; i < 7; i++){


		for(int i = 0; i < bullets.size(); i ++){


			GRect currentBullet = bullets.get(i);

			remove(currentBullet);


		}

		bullets = new ArrayList<GRect>();



		for(int i = 0; i < enemyBullets.size(); i ++){


			GRect currentBullet = enemyBullets.get(i);

			remove(currentBullet);



		}

		enemyBullets = new ArrayList<GRect>();


		rocket.setLocation(APPLICATION_WIDTH/2 - 10, 600);


		enemyDirection = true;
		counter = 0;  

	}


	public void creatCoverBlock(){


		CreateCoverBlockStruture(150, 500);

		CreateCoverBlockStruture(335, 500);

		CreateCoverBlockStruture(520, 500);

		CreateCoverBlockStruture(705, 500);



	}//creatCoverBlock

	public void CreateCoverBlockStruture(int x, int y){

		for(int i = 0; i < 4; i ++){

			CoverBlock cb = new CoverBlock(x + 20*i, y);
			add(cb);


		}


		for(int i = 0; i < 4; i ++){

			CoverBlock cb = new CoverBlock(x + 20*i, y + 20);
			add(cb);


		}

		CoverBlock cb = new CoverBlock(x, y + 40);
		add(cb);

		cb = new CoverBlock(x, y + 60);
		add(cb);

		cb = new CoverBlock(x +60, y + 40);
		add(cb);

		cb = new CoverBlock(x +60, y + 60);
		add(cb);


	}


	public void writeHighSocre(){

		
			BufferedWriter bw;
			try {
				bw = new BufferedWriter(
						new FileWriter("highscore.txt"));

				bw.write(""+ HighScore);
				bw.newLine();

				bw.close();
			} catch (IOException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}

			

	
	}

	
	
	
	
	
	
	
	public void reedHighscore(){
		
		BufferedReader br;
		try {
			br = new BufferedReader(
			        new FileReader("highscore.txt"));
			
			 String line = br.readLine();
		     HighScore = Integer.parseInt(line);
		     br.close();
			
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}

     
	}
	
	
	
	
	
}//end brace

