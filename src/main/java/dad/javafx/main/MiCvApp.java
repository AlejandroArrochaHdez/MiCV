package dad.javafx.main;

import javafx.application.Application;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class MiCvApp  extends Application{
	
	private MainController mainController;
	
	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		mainController = new MainController();
		
		Scene scene = new Scene(mainController.getView());
		
		primaryStage.setTitle("MiCV");
		primaryStage.setScene(scene);
		primaryStage.show();
		
	}

}
