package model;

import java.io.FileInputStream;
import java.io.FileNotFoundException;

import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.effect.DropShadow;
import javafx.scene.input.MouseButton;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.scene.text.Font;

public class SpaceRunnerButton extends Button { // extends built in button class 

	private final String FONT_PATH = "src/model/resources/kenvector_future.ttf"; // Font used in game
	private static final String BUTTON_PRESSED_STYLE = "-fx-background-color: transparent;-fx-background-image: url('model/resources/rocketButton.png');";
	private static final String BUTTON_FREE_STYLE = "-fx-background-color: transparent;-fx-background-image: url('model/resources/rocketButton.png');";
	//private static final String BUTTON_FREE_STYLE = "src/model/resources/rocketButton.png";

	public SpaceRunnerButton(String text) {
		setStyle(BUTTON_FREE_STYLE);		
		setBackground(getBackground());
		setText(text);
		setButtonFont();
		//setPrefWidth(190);
		setPrefWidth(190);
		setPrefHeight(49);
		initialiseButtonListeners();// calling initialize button listeners method
	}

	private void setButtonFont() { // set button font method
		try {
			setFont(Font.loadFont(new FileInputStream(FONT_PATH), 25)); // loading up font
		} catch (FileNotFoundException e) {
			setFont(Font.font("Verdana", 25));
			System.out.println("Font not found or could not be loaded. Using default \"Verdana\""); // if font file not found.. set regular font
		}

	}

	private void setButtonPressedStyle() { // method for setting up button style when button is pressed 
		setStyle(BUTTON_PRESSED_STYLE); // 
		setPrefHeight(49); 
		setLayoutY(getLayoutY() + 4);  // moving button 4 units upwards 

	}

	private void setButtonReleasedStyle() { // method for setting up button style when button is released 
		setStyle(BUTTON_FREE_STYLE);
		setPrefHeight(49);
		setLayoutY(getLayoutY() - 4); //moving button 4 units downwards back to its original position
	}

	private void initialiseButtonListeners() { //initialize button listeners method 
		setOnMousePressed(new EventHandler<MouseEvent>() { // when mouse is pressed.. this mouse event will occur
			@Override
			public void handle(MouseEvent event) {
				if (event.getButton().equals(MouseButton.PRIMARY)) { // if event button is equal to mouse button
					setButtonPressedStyle();// call set button pressed style method 
				}
			}
		});

		setOnMouseReleased(new EventHandler<MouseEvent>() {// when mouse is released.. this mouse event will occur

			@Override
			public void handle(MouseEvent event) { 
				if (event.getButton().equals(MouseButton.PRIMARY)) {// if event button is equal to mouse button
					setButtonReleasedStyle(); // call set button released style method 
				}
			}

		});
		setOnMouseEntered(new EventHandler<MouseEvent>() { // when mouse cursor is above .. this mouse event will occur
			@Override
			public void handle(MouseEvent event) {
				setEffect(new DropShadow(50, Color.YELLOW)); // drop shadow effect with yellow color will occur
			}
		});
		setOnMouseExited(new EventHandler<MouseEvent>() { // when mouse cursor is removed from above .. this mouse event will occur
			@Override
			public void handle(MouseEvent event) {
				setEffect(null); // drop shadow effect will be finished
			}
		});
	}
}
