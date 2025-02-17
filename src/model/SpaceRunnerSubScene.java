package model;

import javafx.animation.TranslateTransition;
import javafx.scene.SubScene;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundImage;
import javafx.scene.layout.BackgroundPosition;
import javafx.scene.layout.BackgroundRepeat;
import javafx.util.Duration;

public class SpaceRunnerSubScene extends SubScene { // this class extends built in subScene class
	
	private final static String FONT_PATH = "model/resources/yellow_panel.png";
	
	private boolean isHidden = true;	

	public SpaceRunnerSubScene() {
		super(new AnchorPane(), 600, 400);
		prefWidth(600);
		prefHeight(400);
		BackgroundImage image = new BackgroundImage(new Image(FONT_PATH, 600, 400, false, true),
				BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT, null);
		AnchorPane root2 = (AnchorPane) this.getRoot();
		
		root2.setBackground(new Background(image));
		
		setLayoutX(1024); //starting coordinates of main menu
		setLayoutY(180);
	}
	
	public void moveSubScene() {  // to move main menu
		
		TranslateTransition transition = new TranslateTransition(); // creating Translate Transition object
		
		transition.setDuration(Duration.seconds(0.5)); //duration of move
		
		transition.setNode(this); // this method decides which element should be moved 
		
		
		if(isHidden) {
			transition.setToX(-676); // this method defines how a particular element will change
			isHidden = false;			
		} 
		else {
			transition.setToX(0);
			isHidden = true;
		}
		
		
		transition.play(); // calling transition
	}
	public AnchorPane getPane() {
		return (AnchorPane) this.getRoot();
	}

}
