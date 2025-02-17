package model; // package model

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class InfoLabel extends Label{ // extends Label.. built in class
	
	public final static String FONT_PATH = "src/model/resources/kenvector_future.ttf"; // Font used in game
	public final static String BG_IMAGE = "view/resources/yellow_button06.png";// 
	
	public InfoLabel(String text) { //
		setPrefWidth(600);
		setPrefHeight(49);
		setPadding(new Insets(10, 40, 40, 50)); // setting up padding 
		setText(text);
		setWrapText(true);
		setLabelFont(); // set label font method is called
		//setAlignment(Pos.CENTER);
		
		BackgroundImage bgImage = new BackgroundImage(new Image(BG_IMAGE, 380, 50, false, true),  // creating object of background image
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		
		setBackground(new Background(bgImage)); // setting up background image
				
	}
	
	private void setLabelFont() { //method for setting up label font 
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25));// loading up font 
		} catch (FileNotFoundException e) {
			System.out.println("Could not load font. Using defaults..."); // if font file not found.. set regular font
			setFont(Font.font("Verdana", 25));
		}
	}
}
