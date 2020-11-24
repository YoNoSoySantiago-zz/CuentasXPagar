package ui;

import java.io.IOException;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;

public class Main extends Application {
	

	private CuentasGUI cuentasGUI;
	
	
	public static void main(String[] args) throws IOException{
		launch(args);
	}

	@Override
	public void start(Stage stage) throws Exception {
		FXMLLoader loader = new FXMLLoader(getClass().getResource("welcomeFX.fxml"));
		cuentasGUI = new CuentasGUI(stage);
		loader.setController(cuentasGUI);
		Parent root = loader.load();
		Scene scene = new Scene(root);
		stage.setScene(scene);
		stage.sizeToScene();
		scene.getStylesheets().add(getClass().getResource("iconos.css").toExternalForm());
		stage.setScene(scene);
		stage.centerOnScreen();
		stage.setResizable(true);
		stage.setTitle("Cuentas por pagar");
		stage.show();
		stage.sizeToScene();
		cuentasGUI.init();
		
	}

}
