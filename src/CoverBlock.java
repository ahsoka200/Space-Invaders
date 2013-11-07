import java.awt.Color;

import acm.graphics.GRect;


public class CoverBlock extends GRect {
	private int damage = 0;
	private Color[] colors;

	public CoverBlock(int x,int y){

		super(x,y,20,20);

		colors = new Color[3];
		colors[0] = new Color(13,219,86,255);
		colors[1] = new Color(13, 219,86, 160);
		colors[2] = new Color(13,219,86,80);

		setColor(colors[damage]);
		setFilled(true);
		setFillColor(colors[damage]);
	}

	public void blockHit(){

		damage = damage + 1;
		if(damage < 3){
			setColor(colors[damage]);
			setFilled(true);
			setFillColor(colors[damage]);
		}

	}//blockHit brace
	
	public boolean isDestriod(){
		
		if(damage == 3){
			
			return true;
		}else{
			
			return false;
		}
		
		
	}



}// end brace
