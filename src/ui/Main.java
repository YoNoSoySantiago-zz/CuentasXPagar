package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	
	private Scene scene;
	private CuentasGUI cuentasGUI;
	
	
	public static void main(String[] args) throws IOException{
		
		launch(args);
		
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("cuantasXpagar.fxml"));

		loader.setController(cuentasGUI);
		Parent root = loader.load();

		scene = new Scene(root);
		stage.setScene(scene);
		stage.setTitle("Cuentas por pagar");
		//cuentasGUI.loadUserWindow(null);
		stage.show();
		stage.sizeToScene();
		
	}

}
