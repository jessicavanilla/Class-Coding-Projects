package Main;
import java.awt.Color;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.StringTokenizer;
import java.util.Random;
import logic.Control;
import timer.stopWatchX;
import Data.boundingBox;
import Data.spriteInfo;
import FileIO.EZFileRead;
import Data.Vector2D;

public class Main{
	// Fields (Static) below...
	public static Color pink = new Color(231, 84, 128);
	public static Color red = new Color (219, 0, 0);
	public static Color black = new Color(0, 0, 0);
	public static stopWatchX timer = new stopWatchX(130);
	static int x = 1250, y = 800, random;
	public static ArrayList<boundingBox> spriteBoxes = new ArrayList<>();
	public static boundingBox gio = new boundingBox(x + 20, x + 103, y, y + 128);
	public static String[] spriteFrames = new String[19];
	public static int currentSpriteIndex = 0;
	public static ArrayList<spriteInfo> otherSprites = new ArrayList<>();
	public static HashMap<String, String> map = new HashMap<>();
	public static boolean isJijiTextDisplayed = false, isMomoTextDisplayed = false, isRockTextDisplayed = false, isRockTextDisplayed2 = false;
	public static boolean jiji = false, momo = false, rock = false, smallRock = false;
	public static Random rand = new Random();
	
	// End Static fields...
	
	public static void main(String[] args) {
		Control ctrl = new Control();				// Do NOT remove!
		ctrl.gameLoop();							// Do NOT remove!
	}
	
	/* This is your access to things BEFORE the game loop starts */
	public static void start(){
		//manually populate animation array for multi-directional travel
		spriteFrames[0] = "front";
		spriteFrames[1] = "aleRight";
		spriteFrames[2] = "walk1";
		spriteFrames[3] = "aleRight";
		spriteFrames[4] = "walk3";
		spriteFrames[5] = "walk4";
		spriteFrames[6] = "aleLeft";
		spriteFrames[7] = "left2";
		spriteFrames[8] = "aleLeft";
		spriteFrames[9] = "left3";
		spriteFrames[10] = "left4";
		spriteFrames[11] = "back";
		spriteFrames[12] = "back2";
		spriteFrames[13] = "back3";
		spriteFrames[14] = "back4";
		spriteFrames[15] = "front";
		spriteFrames[16] = "front2";
		spriteFrames[17] = "front3";
		spriteFrames[18] = "front4";
		
		//populate misc. sprite data structure
		otherSprites.add(new spriteInfo(new Vector2D(0, 0), "background"));
		otherSprites.add(new spriteInfo(new Vector2D(350, 500), "rock2"));
		otherSprites.add(new spriteInfo(new Vector2D(1700, 1300), "rock1"));
		otherSprites.add(new spriteInfo(new Vector2D(800, 1060), "penguin"));
		otherSprites.add(new spriteInfo(new Vector2D(1800, 400), "duck"));
		
		//populate sprite bounding box container
		spriteBoxes.add(new boundingBox(otherSprites.get(1).getCoords().getX() + 15, otherSprites.get(1).getCoords().getX() + 108, otherSprites.get(1).getCoords().getY() + 38, otherSprites.get(1).getCoords().getY() + 94));
		spriteBoxes.add(new boundingBox(otherSprites.get(2).getCoords().getX() + 22, otherSprites.get(2).getCoords().getX() + 106, otherSprites.get(2).getCoords().getY() + 37, otherSprites.get(2).getCoords().getY() + 91));
		spriteBoxes.add(new boundingBox(otherSprites.get(3).getCoords().getX() + 26, otherSprites.get(3).getCoords().getX() + 102, otherSprites.get(3).getCoords().getY() + 29, otherSprites.get(3).getCoords().getY() + 76));
		spriteBoxes.add(new boundingBox(otherSprites.get(4).getCoords().getX() + 10, otherSprites.get(4).getCoords().getX() + 81, otherSprites.get(4).getCoords().getY() + 10, otherSprites.get(4).getCoords().getY() + 81));
		spriteBoxes.add(new boundingBox(0, 170, 0, 1600)); //left most side
		spriteBoxes.add(new boundingBox(2389, 2560, 0, 1600)); //right most side
		spriteBoxes.add(new boundingBox(0, 2560, 0, 152)); //top side
		spriteBoxes.add(new boundingBox(0, 2560, 1440, 1600)); //bottom side
		
		//populate hash map with text
		EZFileRead ezr = new EZFileRead("dialogue.txt");
		for(int i = 0; i < ezr.getNumLines(); i++) {
			StringTokenizer st = new StringTokenizer(ezr.getNextLine(), "*");
			map.put(st.nextToken(), st.nextToken());		//key, value pairs
		}
		
	}
	
	/* This is your access to the "game loop" (It is a "callback" method from the Control class (do NOT modify that class!))*/
	public static void update(Control ctrl) {
		for(int i = 0; i < 5; i++) {
			ctrl.addSpriteToFrontBuffer(otherSprites.get(i).getCoords().getX(), otherSprites.get(i).getCoords().getY(), otherSprites.get(i).getTag());
		}
		ctrl.addSpriteToFrontBuffer(x, y, spriteFrames[currentSpriteIndex]); //character
		
		//display text from hash map when prompted
		if(isJijiTextDisplayed && random == 0)
			ctrl.drawString(692, 1054, map.get("jiji1"), red);
		else if (isJijiTextDisplayed && random == 1)
			ctrl.drawString(646, 1054, map.get("jiji2"), red);
		if(isMomoTextDisplayed && random == 0)
			ctrl.drawString(1604, 376, map.get("momo1"), pink);
		else if(isMomoTextDisplayed && random == 1)
			ctrl.drawString(1662, 376, map.get("momo2"), pink);
		if(isRockTextDisplayed)
			ctrl.drawString(340, 495, map.get("gio1"), black);
		if(isRockTextDisplayed2)
			ctrl.drawString(1660, 1300, map.get("gio2"), black);
	
	}
	
	// Additional Static methods below...(if needed)
	public static boolean isCollide () {
		for(int i = 0; i < spriteBoxes.size(); i++) {
			if(!(gio.getX1() > spriteBoxes.get(i).getX2()) && !(gio.getX2() < spriteBoxes.get(i).getX1()) && !(gio.getY1() > spriteBoxes.get(i).getY2()) && !(gio.getY2() < spriteBoxes.get(i).getY1())) {
				//toggles if the player is facing sprite
				if (i == 0)
					rock = true;
				else if (i == 1)
					smallRock = true;
				else if(i == 2)
					jiji = true;
				else if (i == 3)
					momo = true;
				return true;
			}
		}
		rock = false;
		jiji = false;
		momo = false;
		smallRock = false;
		return false;
	}

}
