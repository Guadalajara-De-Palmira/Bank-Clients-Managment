package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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
    private ChoiceBox<?> paimentMethodsBox;
    
    public PayCreditCardGUI(Bank bank, ClientInformationGUI clientInformation) {
		this.bank = bank;
		this.clientInformation =  clientInformation;
		this.client = this.clientInformation.getClient();
	}

    @FXML
    public void payCreditCard(ActionEvent event) {

    }

}