package model; 

import javafx.geometry.Pos;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.VBox;

public class ShipPicker extends VBox { 

	private ImageView circleImage; 
	private ImageView shipImage;
	
	private String circleNotChosen = "file:model/resources/grey_cirlce.png";
	//private String circleChosen = "model/resources/yellow_boxTick.png";
	private String circleChosen = "model/resources/missile.png";
	
	private SHIP ship; //ship object is declared
	private boolean isCircleChosen;
	
	public ShipPicker(SHIP ship) { //method for picking space ship
		circleImage = new ImageView(new Image(circleNotChosen)); 
		shipImage   = new ImageView(ship.getUrl()); //getting ship image using url
		this.ship = ship;
		isCircleChosen = false; // initially not circle chosen 
		this.setAlignment(Pos.CENTER); // setting up alignment
		this.setSpacing(20); //setting up space 
		this.getChildren().add(circleImage);
		this.getChildren().add(shipImage);			
	}
	
	public SHIP getShip() {  // method for getting ship
		return ship;
	}
	
	public boolean isCircleChosen() { // method for checking that if circle is chosen or not 
		return isCircleChosen;
	}
	
	public void setIsCircleChosen(boolean isCircleChosen) {
		this.isCircleChosen = isCircleChosen;
		String imageToSet = this.isCircleChosen ? circleChosen : circleNotChosen;
		circleImage.setImage(new Image(imageToSet));
	}
}
