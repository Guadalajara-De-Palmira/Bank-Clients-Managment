package ui;

import customStructureExceptions.KeyDifferenceException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import model.Bank;

public class RegisterClientGUI {

	private Bank bank;
	
    @FXML
    private TextField nameText;

    @FXML
    private TextField idText;

    @FXML
    private TextField disabilitiesText;
    
    public RegisterClientGUI(Bank bank) {
		this.bank = bank;
	}

    @FXML
    void registerNewClient(ActionEvent event) {
    	if(!nameText.getText().isEmpty() && !idText.getText().isEmpty() && !disabilitiesText.getText().isEmpty()) {
    		try {
    			int disNum = Integer.parseInt(disabilitiesText.getText());
    			if(disNum == 0) {
    				bank.addToQueue(nameText.getText(), Integer.parseInt(idText.getText()), disNum);
    			}else {

    				bank.addToPriorityQueue(nameText.getText(), Integer.parseInt(idText.getText()), disNum);

    			}
    		} catch (NumberFormatException | KeyDifferenceException e) {
    			Alert alert = new Alert(Alert.AlertType.INFORMATION);
    			alert.setHeaderText("Error");
    			alert.setTitle("Alert");
    			alert.setContentText("an error has happend, some of the fields are not the type they should be");
    			alert.showAndWait();
    		}
    	}else {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("Algunos campos estan vacios. Por favor llene todos los campos");
			alert.showAndWait();
    	}
    }

}
