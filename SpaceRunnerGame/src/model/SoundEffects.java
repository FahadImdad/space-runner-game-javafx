package model; // model package

import java.net.URI; 

import javafx.scene.media.AudioClip;

public class SoundEffects { // sound effect class 
	
	private static double VOL = 0.2; // default volume 20%
	
	public static void playSound(URI sound) {	 // method for playing sound taking URL of sound as argument
		AudioClip clip = new AudioClip(sound.toString()); 
		clip.setVolume(VOL); // setting up volume of sound 
		clip.play(); // playing sound
	}
}
