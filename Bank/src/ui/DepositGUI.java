package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.TextField;
import model.Bank;
import model.Client;

public class DepositGUI {
	
	private Bank bank;
	
	private ClientInformationGUI clientInformation;
	
	private Client client;

    @FXML
    private TextField amount;
    
    public DepositGUI(Bank bank, ClientInformationGUI clientInformation) {
		this.bank = bank;
		this.clientInformation =  clientInformation;
		this.client = this.clientInformation.getClient();
	}

    @FXML
    void deposit(ActionEvent event) {

    }

}
