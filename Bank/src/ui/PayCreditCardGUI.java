package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ChoiceBox;
import javafx.scene.control.TextField;
import model.Bank;
import model.Client;

public class PayCreditCardGUI {
	
	private Bank bank;
	
	private ClientInformationGUI clientInformation;
	
	private Client client;

    @FXML
    private TextField amount;

    @FXML
    private ChoiceBox<String> paimentMethodsBox;
    
    public PayCreditCardGUI(Bank bank, ClientInformationGUI clientInformation) {
		this.bank = bank;
		this.clientInformation =  clientInformation;

	}
    
    public void initialize() {
		this.client = this.clientInformation.getClient();
		paimentMethodsBox.getItems().add("cuenta Bancaria");
		paimentMethodsBox.getItems().add("efectivo");
    }
    
    @FXML
    public void payCreditCard(ActionEvent event) {
    	
    	if(amount.getText().isEmpty() || paimentMethodsBox.getValue()==null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("algunos campos están vacios");
			alert.showAndWait();
    	}else {
        	boolean method;
        	if(((String)paimentMethodsBox.getValue()).equalsIgnoreCase("efectivo")) {
        		method = false;
        	}else {
        		method = true;
        	}
        	bank.payCreditCard(client, method, Integer.parseInt(amount.getText()));
    	}

    }

}