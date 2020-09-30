 package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.control.Button;
import javafx.scene.layout.BorderPane;
import model.Bank;


public class MainWindowGUI {

	private Bank bank;
	
	private ClientsTableGUI clientsTable;
	
	private AttendClientGUI attendClients;
	
	private RegisterClientGUI registerClients;
	
    @FXML
    private BorderPane mainPane;

    @FXML
    private Button registerButton;

    @FXML
    private Button attendButton;

    @FXML
    private Button tableButton;
    
	public MainWindowGUI(Bank bank) {
		this.bank = bank;
		
		clientsTable = new ClientsTableGUI(bank);
		attendClients = new AttendClientGUI(bank,this);
		registerClients = new RegisterClientGUI(bank);

	}

    @FXML
    public void showAttendButton(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("AttendClientsWindow.fxml"));
    	fxmlLoader.setController(attendClients);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainPane.setCenter(newWindow);
    }

    @FXML
    public void showRegisterWindow(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("RegisterClientWindow.fxml"));
    	fxmlLoader.setController(registerClients);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainPane.setCenter(newWindow);
    }

    @FXML
    public void showTableWindow(ActionEvent event) throws IOException {
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientsTableWindow.fxml"));
    	fxmlLoader.setController(clientsTable);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainPane.setCenter(newWindow);
    }
	
    public BorderPane getMainPane() {
    	return mainPane;
    }
}
