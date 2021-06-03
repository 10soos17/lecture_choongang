package p04.container;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class AppMain extends Application {

	public static void main(String[] args) {
		launch(args);

	}

	@Override
	public void start(Stage stage) throws Exception {
		Parent root = FXMLLoader.load(getClass().getResource("root_border.fxml"));
		
		Scene s = new Scene(root);
		
		stage.setTitle("AppMain");
		stage.setScene(s);
		stage.show();
	}

}
