package custom_exception;

import javafx.scene.control.Alert;
import javafx.scene.control.Alert.AlertType;

@SuppressWarnings("serial")
public class AlreadyCodeExistException extends Exception{
	public AlreadyCodeExistException() {
		Alert alert = new Alert(AlertType.ERROR);
		alert.setTitle("ERROR");
		alert.setHeaderText("CxP");
		alert.setContentText("Codigo existente");
		alert.showAndWait();
	}

}
