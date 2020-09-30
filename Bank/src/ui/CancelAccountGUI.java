package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
		this.client = this.clientInformation.getClient();
	}

    @FXML
    void cancelAccount(ActionEvent event) {

    }

}
