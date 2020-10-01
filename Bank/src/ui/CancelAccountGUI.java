package ui;

import customStructureExceptions.FullStructureException;
import customStructureExceptions.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.TextArea;
import model.Bank;
import model.Client;

public class CancelAccountGUI {
	
	private Bank bank;
	
	private ClientInformationGUI clientInformation;
	
	private Client client;

    @FXML
    private TextArea reasonOfCancelationText;
    
    public CancelAccountGUI(Bank bank, ClientInformationGUI clientInformation) {
		this.bank = bank;
		this.clientInformation =  clientInformation;
	}
    
    public void initialize() {
		this.client = this.clientInformation.getClient();
    }
    

    @FXML
    void cancelAccount(ActionEvent event) {
    	if(reasonOfCancelationText.getText().isEmpty()) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("algunos campos están vacios");
			alert.showAndWait();
    	}else {
    		try {
				bank.cancelAccount(client, reasonOfCancelationText.getText());
			} catch (FullStructureException | NotFoundException e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText("Error");
				alert.setTitle("Alert");
				alert.setContentText("el cliente no se pudo encontrar");
				alert.showAndWait();
			}
    	}
    }

}
