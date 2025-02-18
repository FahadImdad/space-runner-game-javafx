 package view;

import java.net.URI;
import java.net.URISyntaxException;
import java.util.Random;

import javafx.animation.AnimationTimer;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.KeyCode;
import javafx.scene.input.KeyEvent;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.GridPane;
import javafx.stage.Stage;
import model.SHIP;
import model.SmallInfoLabel;
import model.SoundEffects;

public class GameViewManager {
	
	private AnchorPane gamePane;
	private AnchorPane gamePane_1;
	private AnchorPane gamePane_2;
	private Scene gameScene;
	private Scene gameScene_1;
	private Scene gameScene_2;
	private Stage gameStage;
	private Stage gameStage_1;
	private Stage gameStage_2;
	private Stage menuStage;
	private ImageView ship;

	private boolean isLeftKeyPressed;
	private boolean isRightKeyPressed;
//	private boolean isSpacePressed;
//	private boolean isBulletFired = false;
	private int angle;

	private GridPane gridPane1, gridPane2;
	private static final String BG_IMAGE = "view/resources/purple.png";
	public static final String METEOR_BROWN = "view/resources/index1.png";
	public static final String METEOR_GREY = "view/resources/index2.png";
//	private static final String BULLET = "view/resources/missile.png";
	private AnimationTimer gameTimer;

	private static final int GAME_WIDTH = 600; //stage width
	private static final int GAME_HEIGHT = 700; // stage height

	private ImageView[] brownMeteors; // image view arrays
	private ImageView[] greyMeteors;
	Random randomPosGen; 

	private ImageView star;
	private ImageView life;
//	private ImageView bullet;
	private SmallInfoLabel pointsLabel;
	private ImageView[] playerLifes;
	private int playerLife = 3;
	private int points;
	public static final String GOLD_STAR = "view/resources/star_gold.png";
	public static final String LIFE = "view/resources/heart.png";
	private static final String LIFE_LOST = "file:src/model/resources/life-lost.wav";
	private static final String LIFE_GAIN = "file:src/model/resources/life-gain.wav";

	private static final int STAR_RADIUS = 12;
	private static final int SHIP_RADIUS = 30;
	//private static final int METEOR_RADIUS = 40;
	private static final int METEOR_BROWN_RADIUS = 40;
	private static final int METEOR_GREY_RADIUS = 20;
	public static int score;

	public GameViewManager() {
		randomPosGen = new Random(); // creating random positions 
		initializeStage();
		createKeysListeners(); // calling createKeysListener method
	}

	private void createKeysListeners() {  // this method will create key listeners that will listen left and right arrow key
		
		gameScene.setOnKeyPressed(new EventHandler<KeyEvent>() {

			@Override
			public void handle(KeyEvent event) {
				if (event.getCode() == KeyCode.LEFT) { //comparing pressed key code with javafx keycode value
					isLeftKeyPressed = true;   // if left is pressed.. Space ship moves left
				} 
				else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = true;   // if left is pressed.. Space ship moves left
				}
				//if (event.getCode() == KeyCode.SPACE) {
//					isSpacePressed = true;
//					isBulletFired = true;  // if Space is pressed.. bullet will be fired 
				//}
			}
		});

		gameScene.setOnKeyReleased(new EventHandler<KeyEvent>() {
			@Override
			public void handle(KeyEvent event) {   
				if (event.getCode() == KeyCode.LEFT) {
					isLeftKeyPressed = false;
				} else if (event.getCode() == KeyCode.RIGHT) {
					isRightKeyPressed = false;
				}
				if (event.getCode() == KeyCode.SPACE) {
//					isSpacePressed = false;
//					isBulletFired = false;
				}

			}
		});

	}

	private void initializeStage() {
		gamePane = new AnchorPane(); 
		gamePane_1 = new AnchorPane(); 
		gamePane_2 = new AnchorPane(); 
		
		gameScene = new Scene(gamePane, GAME_WIDTH, GAME_HEIGHT); 
		gameScene_1 = new Scene(gamePane_1,300,700);
		gameScene_2 = new Scene(gamePane_2,300,700);
		
		gameStage = new Stage();
		gameStage_1 = new Stage();
		gameStage_2 = new Stage();
		
		gameStage.setTitle("Space Runner");
		
		gameStage.setScene(gameScene); 
		
		gameStage_1.setScene(gameScene_1); 
		gameStage_2.setScene(gameScene_2); 
		
		gameStage.setResizable(false);  //screen is in fixed size
		
		gameStage.setOnCloseRequest(x -> {
		//	x.consume();
		//	if(ConfirmExit.askConfirmation()) {
		//	 Platform.exit();
			gameStage.close();
			menuStage.show();
		//	 }
		});
	}

	public void createNewGame(Stage menuStage, SHIP chosenShip) { // method that will show new game window
		this.menuStage = menuStage;
		this.menuStage.hide();
		createBG();
		createShip(chosenShip); //calling method to create ship
		createGameElements(chosenShip);
		createGameLoop();
		gameStage.show();

	}

	private void createGameElements(SHIP chosenShip) { // this method will create game elements
		// playerLife = 2;
		star = new ImageView(GOLD_STAR); 
		setNewElementPos(star); // setting up position of star

		life = new ImageView(LIFE);
		setNewElementPos(life);

		pointsLabel = new SmallInfoLabel("Points:  00");
		pointsLabel.setLayoutX(460);
		// pointsLabel.setLayoutY(10);
		gamePane.getChildren().addAll(star, life, pointsLabel); // adding all star, life and points label to game pane 

		playerLifes = new ImageView[playerLife]; // creating array of player life
		
		for (int i = 0; i < playerLifes.length; i++) {
			playerLifes[i] = new ImageView(chosenShip.getUrlLife());
			playerLifes[i].setLayoutX(455  + (i * 40));
			playerLifes[i].setLayoutY(50);
			gamePane.getChildren().add(playerLifes[i]); //adding player life to game pane
		}

		brownMeteors = new ImageView[3]; //initializing brown meteors arrays
		
		for (int i = 0; i < brownMeteors.length; i++) {
			brownMeteors[i] = new ImageView(new Image(METEOR_BROWN, 60*i, 200-i*10, true, false)); // filling meteors meeting image views
			setNewElementPos(brownMeteors[i]); 
			gamePane.getChildren().add(brownMeteors[i]);
		}
		
		greyMeteors = new ImageView[3]; //initializing grey meteors arrays
		
		for (int i = 0; i < greyMeteors.length; i++) {
			greyMeteors[i] = new ImageView(new Image(METEOR_GREY, 50+i*20, 100+i*10, true, false));
			setNewElementPos(greyMeteors[i]);
			gamePane.getChildren().add(greyMeteors[i]);
		}
	}

	private void setNewElementPos(ImageView image) { // this method will keep our meteors at random position
		image.setLayoutX(randomPosGen.nextInt(370)); 
		image.setLayoutY(-(randomPosGen.nextInt(3200) + 600));
	}

	private void moveElements() {  // this method will move our meteors
		star.setLayoutY(star.getLayoutY() + 5); // moving star
		life.setLayoutY(life.getLayoutY() + 5);
//       	if (isBulletFired) {
//			System.out.println("isBulletFired: "+isBulletFired);
//			System.out.println("before moving bullet: " + bullet.getLayoutY());			bullet.setLayoutY(bullet.getLayoutY() - 5);
//			System.out.println("After moving bullet: " + bullet.getLayoutY());
//			//bullet.relocate(bullet.getLayoutX(), bullet.getLayoutY() - 5);
//			isBulletFired = false;
//			//System.out.println("isBulletFired: "+isBulletFired);
//		}

		for (int i = 0; i < brownMeteors.length; i++) {
			
			brownMeteors[i].setLayoutY(brownMeteors[i].getLayoutY() + 7);
			brownMeteors[i].setRotate(brownMeteors[i].getRotate() + 4 * i); 
		}
		
		for (int i = 0; i < greyMeteors.length; i++) {
			
			greyMeteors[i].setLayoutY(greyMeteors[i].getLayoutY() + 7);
			greyMeteors[i].setRotate(greyMeteors[i].getRotate() + 4 + i);

		}
	}

	private void checkElementsPos() {  // this method will check if elements are below screen.. it will relocate them 
		if (star.getLayoutY() > GAME_HEIGHT) { // relocating star
			setNewElementPos(star);
		}
		if (life.getLayoutY() > GAME_HEIGHT) {
			setNewElementPos(life);
		}

		for (int i = 0; i < brownMeteors.length; i++) {
			if (brownMeteors[i].getLayoutY() > GAME_HEIGHT) {
				setNewElementPos(brownMeteors[i]); // will use previously created settlements positions 
			}
		}
		for (int i = 0; i < greyMeteors.length; i++) {
			if (greyMeteors[i].getLayoutY() > GAME_HEIGHT) {
				setNewElementPos(greyMeteors[i]);
			}
		}
	}

	private void createShip(SHIP chosenShip) {
		ship = new ImageView(chosenShip.getUrl()); // loading image of ship
		ship.setLayoutX(GAME_WIDTH / 2 - 50); // starting position of our ship
		ship.setLayoutY(GAME_HEIGHT - 200);
		gamePane.getChildren().add(ship); // adding ship to game pane
	}

	private void createGameLoop() { 
		gameTimer = new AnimationTimer() { // Animation Timer object -- this object allows us to create timer that is called in each frame while it is active

			@Override
			public void handle(long now) { //calling all methods in loop
				moveBG(); // moving grid pane
				moveElements(); // moving elements (meteors,stars and life)
				
				//fireBullet(); // firing bullet... still working upon it
				//moveBullet(); // moving bullet.. still working upon it
				
				checkElementsPos();
				collisionDetection();// calling collision detection method
				moveShip(); //calling move ship method
			}
		};
		gameTimer.start(); //calling start method
	}

	/*private void fireBullet() {
		if (isSpacePressed) {
			bullet = new ImageView(BULLET);
			bullet.setLayoutX(ship.getLayoutX() + 38);
			bullet.setLayoutY(ship.getLayoutY() - 45);

			gamePane.getChildren().add(bullet);
			isBulletFired = true;
		}
	}/*
	
	/*private void moveBullet() {
		if (isBulletFired) {
			System.out.println("bullet fired at " + bullet.getY() + " now moving it");
			//bullet.setLayoutY(bullet.getLayoutY() - 5);
			bullet.setY(bullet.getY() - 5);
			System.out.println("bullet is at: " + bullet.getLayoutY());
			//isBulletFired = false;
			//bullet = new ImageView(BULLET);
		}
	}*/

	private void moveShip() { // this method is responsible for moving and rotating our ship
		
		if (isLeftKeyPressed && !isRightKeyPressed) { //when left key is pressed and right key is not pressed
			if (angle > -30) {   
				angle -= 5;
			}
			ship.setRotate(angle);
			
			if (ship.getLayoutX() > -20) {
				ship.setLayoutX(ship.getLayoutX() - 5);
			}
		}
		if (!isLeftKeyPressed && isRightKeyPressed) { //when left key is not pressed and right key is pressed
			if (angle < 30) {
				angle += 5;
			}
			ship.setRotate(angle);
			if (ship.getLayoutX() < 522) {
				ship.setLayoutX(ship.getLayoutX() + 5);
			}
		}
		if (isLeftKeyPressed && isRightKeyPressed) { //when both left key and right key are pressed
			if (angle < 0) {
				angle += 5;
			} else if (angle > 0) {
				angle -= 5;
			}
			ship.setRotate(angle);
		}
		if (!isLeftKeyPressed && !isRightKeyPressed) { //when both left key and right key are not pressed
			if (angle < 0) {     //angle will decrease and ship will not move and will be looking forward
				angle += 5;
			} else if (angle > 0) {  
				angle -= 5;
			}
			ship.setRotate(angle);
		}
	}

	private void createBG() { // for creating background 
//		Image bgImage =  new Image("view/resources/game_bg.jpg", 256, 256, false, true);
//		BackgroundImage bg = new BackgroundImage(bgImage, BackgroundRepeat.NO_REPEAT, BackgroundRepeat.NO_REPEAT, BackgroundPosition.DEFAULT,new BackgroundSize(1024, 800, false, false, false, false));
//		gamePane.setBackground(new Background(bg));
		gridPane1 = new GridPane(); // Each grid pane contain 12 images...
		gridPane2 = new GridPane();
		for (int i = 0; i < 12; i++) {
			ImageView bgImage1 = new ImageView(BG_IMAGE);
			ImageView bgImage2 = new ImageView(BG_IMAGE);
			GridPane.setConstraints(bgImage1, i % 3, i / 3); // static method that set rows and column for child..
			GridPane.setConstraints(bgImage2, i % 3, i / 3);
			gridPane1.getChildren().add(bgImage1);
			gridPane2.getChildren().add(bgImage2);
		}
		gridPane2.setLayoutY(-1024);// grid pane 2 position will be (0,-1024)...

		gamePane.getChildren().addAll(gridPane1, gridPane2);// adding both grid panes to game pane...

	}

	private void moveBG() {
		gridPane1.setLayoutY(gridPane1.getLayoutY() + 5);
		gridPane2.setLayoutY(gridPane2.getLayoutY() + 5);
		if (gridPane1.getLayoutY() >= 1024) {
			gridPane1.setLayoutY(-1024);
		}
		if (gridPane2.getLayoutY() >= 1024) {
			gridPane2.setLayoutY(-1024);
		}
	}

	private void collisionDetection() { //collision detection method
		
		//collision detection with star
		if (SHIP_RADIUS + STAR_RADIUS > calculateDistance(ship.getLayoutX() + 49, ship.getLayoutY() + 49,
				star.getLayoutX() + 37, star.getLayoutY() + 15)) {
			
			setNewElementPos(star); // after hitting star.. relocating star
			points += 2; // adding +2 to score
			
			try {
				SoundEffects.playSound(new URI(LIFE_GAIN));
			} catch (URISyntaxException e) {
				System.out.println("Error: Life gain sound could not be played!");
				e.printStackTrace();
			}

			
			String textToSet = "Points: ";
			if (points < 10) {
				textToSet += "0";
			}
			pointsLabel.setText(textToSet + points); // showing points on points label
		}
		
		
		// collision detection with life
		if (SHIP_RADIUS + 20 > calculateDistance(ship.getLayoutX() + 49, ship.getLayoutY() + 49, life.getLayoutX() + 37,
				life.getLayoutY() + 15)) {
			
			setNewElementPos(life);  // after hitting life.. relocating life
			
			points += 5; // after hitting life... +5 points will be added
			
			String textToSet = "Points: ";
			if (points < 10) {
				textToSet += "0";
			}
			pointsLabel.setText(textToSet + points); // showing points on points label
			addLife();// add life method is called for increasing life

		}
		
		// collision detection with meteors
		for (int i = 0; i < brownMeteors.length; i++) {
			if (METEOR_BROWN_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX() + 49, ship.getLayoutY() + 37,
					brownMeteors[i].getLayoutX() + 20, brownMeteors[i].getLayoutY() + 20)) {

				removeLife(); // after hitting meteor.. relocating meteor and removing life
				setNewElementPos(brownMeteors[i]);
			}
		}
		for (int i = 0; i < greyMeteors.length; i++) {
			if (METEOR_GREY_RADIUS + SHIP_RADIUS > calculateDistance(ship.getLayoutX() + 49, ship.getLayoutY() + 37,
					greyMeteors[i].getLayoutX() + 20, greyMeteors[i].getLayoutY() + 20)) {

				removeLife();
				setNewElementPos(greyMeteors[i]);
			}
		}
	}

	
	private void removeLife() {
		
		gamePane.getChildren().remove(playerLifes[playerLife-1]); // removing player life from game pane
		
		try {
			SoundEffects.playSound(new URI(LIFE_LOST));
		} catch (URISyntaxException e) {
			System.out.println("Error: Life lost sound could not be played!");
			e.printStackTrace();
		}
		playerLife--; // decreasing player life
		if (playerLife <= 0) {
			score = points;
			gameTimer.stop(); // stopping game timer
			// TODO show a message that says the game is over
			gameStage.close();// closing game stage
			//ViewManager v = new ViewManager();
			//v.CheckScore(score);
			menuStage.show();// showing menu stage
			

		}
	}

	private void addLife() { 
		if (playerLife < 3) {  // if life is less then 3 adding life to game pane
			gamePane.getChildren().add(playerLifes[playerLife]); 
			playerLife++; // increasing life by 1
		}
		try {
			SoundEffects.playSound(new URI(LIFE_GAIN));
		} catch (URISyntaxException e) {
			System.out.println("Error: Life gain sound could not be played!");
			e.printStackTrace();
		}

	}

	private double calculateDistance(double x1, double y1, double x2, double y2) {  //this method will calculate distance between 2 points
		return Math.sqrt(Math.pow(x1 - x2, 2) + Math.pow(y1 - y2, 2));
	}
}
