package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Label;
import model.Bank;
import model.Client;

public class ClientInformationGUI {
	
	private Bank bank;
	
	private Client client;
	
	private AttendClientGUI attendclient;
	
	private MainWindowGUI mainWindow;
	
	private PayCreditCardGUI payCard;
	
	private DepositGUI deposit;
	
	private CancelAccountGUI cancelAccount;

    @FXML
    private Label nameLabel;

    @FXML
    private Label IDLabel;

    @FXML
    private Label entranceDateLabel;

    @FXML
    private Label accountIDLabel;

    @FXML
    private Label ammountLabel;

    @FXML
    private Label cardIDLabel;

    @FXML
    private Label creditInCardLabel;

    public ClientInformationGUI(Bank bank, MainWindowGUI mainWindow, AttendClientGUI attendClient) {
		this.bank = bank;
		this.mainWindow = mainWindow;
		this.attendclient = attendClient;
		this.client = this.attendclient.getClient();
		
		if(payCard==null) {
			payCard = new PayCreditCardGUI(bank,this);
			deposit = new DepositGUI(bank,this);
			cancelAccount = new CancelAccountGUI(bank,this);
		}
	}
    
    @FXML
    void cancelAccount(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("CalcelAccountWindow.fxml"));
    	fxmlLoader.setController(cancelAccount);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainWindow.getMainPane().setCenter(newWindow);
    }

    @FXML
    void deposit(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("DepositWindow.fxml"));
    	fxmlLoader.setController(deposit);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainWindow.getMainPane().setCenter(newWindow);
    }

    @FXML
    void payCreditCard(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("PayCreditCardWindow.fxml"));
    	fxmlLoader.setController(payCard);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainWindow.getMainPane().setCenter(newWindow);
    }
    
    public Client getClient() {
    	return client;
    }
}
