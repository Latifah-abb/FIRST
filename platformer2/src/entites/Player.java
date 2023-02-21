package entites;

import static utilz.Constans.Directions.DOWN;
import static utilz.Constans.Directions.LEFT;
import static utilz.Constans.Directions.RIGHT;
import static utilz.Constans.Directions.UP;
import static utilz.Constans.PlayerConstants.GetSpriteAmount;
import static utilz.Constans.PlayerConstants.IDLE;
import static utilz.Constans.PlayerConstants.RUNNING;
import static utilz.Constans.PlayerConstants.*;

import java.awt.Graphics;
import java.awt.image.BufferedImage;
import java.io.IOException;
import java.io.InputStream;
import main.Game;

import javax.imageio.ImageIO;

import utilz.LoadSave;

public class Player extends Entity {
	
	private static final int ATTACK_1 = 0;
	private BufferedImage[][] animations;
	private int aniTick , aniIndex, aniSpeed =25;
	private int playerAction = IDLE;
	private boolean moving = false , attacking = false;;
	private boolean left, up, right, down;
	private float playerspeed =2.0f;
	private int playerDir;
	

	public Player(float x, float y, int width, int height) {
		super(x, y );
		loadAnimations();
		
	}
	
	
	public void update() {
		updatePos();
updateAnimationTick();
		setAnimation();
		
		
	}
	
	public void render(Graphics g) {
		g.drawImage(animations[playerAction][aniIndex],(int) x,(int) y,256,160, null);
		
		
	}
	
	

	
	
	
	
	private void updateAnimationTick() {
		
		aniTick++;
		if(aniTick >= aniSpeed) {
	aniTick =0;
			aniIndex ++;
			if (aniIndex >= GetSpriteAmount(playerAction)) {
		aniIndex =0;
				attacking= false;
			}
		}
		}
		
	private void setAnimation() {
		int startAni= playerAction;
	
		if (moving)
			playerAction = RUNNING;
		else
			playerAction = IDLE;
		
		if (attacking)
			playerAction = ATTACK_1;
		
		if (startAni != playerAction)
			resetAniTick();
	
		

		
	
		
	}
	
	
		
	
	
	private void resetAniTick() {

		aniTick = 0;
		aniIndex = 0;
	}


	private void updatePos() {
		moving= false;
	if(left && ! right) {
		x-=playerspeed;
		moving=true;
	} else if(right && !left){
	x += playerspeed;
	moving=true;
	}
	if(up && ! down) {
		y-=playerspeed;
		moving=true;
	} else if(down && !up){
	y += playerspeed;
	moving=true;
	}
	}
	
	
	private void loadAnimations() {
		
		
BufferedImage img = LoadSave.GetSpriteAtlas(LoadSave.PLAYER_ATLAS);
			animations= new BufferedImage[9][6];
			for(int j =0; j<animations.length; j++ )
			for(int i =0 ; i < animations[j].length; i++)
				animations[j][i] = img.getSubimage(i*64, j*40, 64, 40);
		}
	    
		
		
	
	
	public void resetDirBooleans() {
		left=false;
		right =false;
		up= false;
		down=false;
	}
	
	public void setAttacking(boolean attacking) {
		this.attacking = attacking;
	}


	public boolean isLeft() {
		return left;
	}


	public void setLeft(boolean left) {
		this.left = left;
	}


	public boolean isUp() {
		return up;
	}

	
	

	public void setUp(boolean up) {
		this.up = up;
	}


	public boolean isRight() {
		return right;
	}


	public void setRight(boolean right) {
		this.right = right;
	}


	public boolean isDown() {
		return down;
	}


	public void setDown(boolean down) {
		this.down = down;
	}





	


	
	

}
