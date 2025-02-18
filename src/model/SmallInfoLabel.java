package model;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.scene.text.Font;

public class SmallInfoLabel extends Label{ //this label will show score
	
	private static final String FONT_PATH = "src/model/resources/kenvector_future.ttf"; //Font used in game
	
	public SmallInfoLabel(String text) {
		setPrefHeight(30); // width and height of label
		setPrefWidth(130);
		BackgroundImage bgImg = new BackgroundImage(new Image("/view/resources/buttonBlue.png", 130, 30, false, true), 
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);  // background image of score label
		setBackground(new Background(bgImg)); //setting up background image of score label
		setAlignment(Pos.CENTER_LEFT); // setting up alignment to center left of score label
		setPadding(new Insets(10, 10, 10, 10)); // setting up padding of score label
		setLabelFont(); // method for setting up font of text in score label is called
		setText(text); // setting up text in score label
	}
	
	private void setLabelFont() { // this method will set font 
		try {
			setFont(Font.loadFont(new FileInputStream(new File(FONT_PATH)), 10));
		} catch (FileNotFoundException e) {
			System.out.println("Font file not found. Using default font \"Verdana\""); // if font file not found.. load regular font 
			setFont(Font.font("Verdana", 15));
		}
		
	}
	
}







