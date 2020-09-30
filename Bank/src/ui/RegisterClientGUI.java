package ui;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
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

    }

}
