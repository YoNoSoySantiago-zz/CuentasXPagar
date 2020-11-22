package custom_exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class ValuesIsEmptyException extends Exception{
	public ValuesIsEmptyException() {
		
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("EMPTY");
		alert.setContentText("Algunos valores no son validos");
		alert.showAndWait();
	
	}
}
