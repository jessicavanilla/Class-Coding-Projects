/* This will handle the "Hot Key" system. */

package Main;

import logic.Control;
import timer.stopWatchX;

public class KeyProcessor{
	// Static Fields
	private static char last = ' ';			// For debouncing purposes
	private static stopWatchX sw = new stopWatchX(250);
	
	// Static Method(s)
	public static void processKey(char key){
		if(key == ' ')				return;
		// Debounce routine below...
		if(key == last)
			if(sw.isTimeUp() == false)			return;
		last = key;
		sw.resetWatch();
		
		switch(key){
		case '%':								// ESC key
			System.exit(0);
			break;
		case 'w':
			Main.gio.adjustY(-40);
			if(Main.isCollide()) {
				Main.gio.adjustY(40);
				Main.currentSpriteIndex = 11;
				break;
			}
			Main.y -= 40;
			if(Main.currentSpriteIndex > 13 || Main.currentSpriteIndex < 11) {
				Main.currentSpriteIndex = 11;
			}
			else {
				Main.currentSpriteIndex++;
			}
			break;
		case 'a':
			Main.gio.adjustX(-45);
			if(Main.isCollide()) {
				Main.gio.adjustX(45);
				Main.currentSpriteIndex = 6;
				break;
			}
			Main.x -= 45;
			if(Main.currentSpriteIndex > 9 || Main.currentSpriteIndex < 6) {
				Main.currentSpriteIndex = 6;
			}
			else {
				Main.currentSpriteIndex++;
			}
			break;
		case 's':
			Main.gio.adjustY(40);
			if(Main.isCollide()) {
				Main.gio.adjustY(-40);
				Main.currentSpriteIndex = 15;
				break;
			}
			Main.y += 40;
			if(Main.currentSpriteIndex < 15 || Main.currentSpriteIndex > (Main.spriteFrames.length - 2)) {
				Main.currentSpriteIndex = 15;
			}
			else {
				Main.currentSpriteIndex++;
			}
			break;
		case 'd':
			Main.gio.adjustX(45);
			if(Main.isCollide()) {
				Main.gio.adjustX(-45);
				Main.currentSpriteIndex = 1;
				break;
			}
			Main.x += 45;
			if(Main.currentSpriteIndex > 4) {
				Main.currentSpriteIndex = 1;
			}
			else {
				Main.currentSpriteIndex++;
			}
			break;
		case '$':			//$ is used to represent space bar for the gaming API
			Main.random = Main.rand.nextInt(2);	//randomizes what text is displayed
			
			if(Main.isMomoTextDisplayed) {		//if text is displayed, even if you aren't close, remove text from screen
				Main.isMomoTextDisplayed = false;
			}
			else{
				if(Main.momo)		//will only display text if facing sprite
					Main.isMomoTextDisplayed = !Main.isMomoTextDisplayed;
			}
			
			if(Main.isJijiTextDisplayed) {
				Main.isJijiTextDisplayed = false;
			}
			else {
				if(Main.jiji)
					Main.isJijiTextDisplayed = !Main.isJijiTextDisplayed;
			}
			
			if(Main.isRockTextDisplayed) {
				Main.isRockTextDisplayed = false;
			}
			else {
				if(Main.rock)
					Main.isRockTextDisplayed = !Main.isRockTextDisplayed;
			}
			
			if(Main.isRockTextDisplayed2) {
				Main.isRockTextDisplayed2 = false;
			}
			else {
				if(Main.smallRock)
					Main.isRockTextDisplayed2 = !Main.isRockTextDisplayed2;
			}
			break;
		case 'm':
			// For mouse coordinates
			Control.isMouseCoordsDisplayed = !Control.isMouseCoordsDisplayed;
			break;
		}
	}
}