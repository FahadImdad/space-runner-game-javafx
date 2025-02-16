package application;
	
import javafx.application.Application;
import javafx.stage.Stage;
import view.ConfirmExit;
import view.ViewManager;


public class Main extends Application { 
	@Override
	public void start(Stage primaryStage) {
		try {
			ViewManager manager = new ViewManager();
			primaryStage = manager.getMainStage(); // getting main stage of manager as primary stage 
			primaryStage.setTitle("Space Runner Game"); // set title
			primaryStage.setResizable(false); // primary stage is not re_sizable anymore
			primaryStage.setOnCloseRequest(x ->{  // if cross is pressed.. call ask confirmation method form confirm Exit class
				x.consume(); // call consume method for x
				if(ConfirmExit.askConfirmation()== true) {
					System.exit(0); // Exit program
				}
			});
			primaryStage.show(); //show primary stage
		} catch(Exception e) { 
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) {
		launch(args); // launching main
	}

}
