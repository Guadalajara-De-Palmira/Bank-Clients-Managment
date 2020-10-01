package ui;

import java.io.IOException;

import customStructureExceptions.EmptyStructureException;
import customStructureExceptions.FullStructureException;
import customStructureExceptions.NotFoundException;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
import javafx.scene.control.Alert;
import model.Bank;
import model.Client;

public class AttendClientGUI {

	private Bank bank;
	
	private Client client;
	
	private MainWindowGUI mainWindow; 
	
	private ClientInformationGUI clientInformation;
	
    @FXML
    private Canvas canvasPriorityQueue;

    @FXML
    private Canvas canvasQueue;
    
    public AttendClientGUI(Bank bank, MainWindowGUI mainWindow) {
    	this.bank = bank;
    	this.mainWindow = mainWindow;
    	
    	if(clientInformation==null) {
    		clientInformation = new ClientInformationGUI(bank,mainWindow,this);
    	}
    }

    @FXML
    public void attendPriorityQueue(ActionEvent event) throws IOException {
    	
    	try {
			client = bank.searchClientFromPriorityQueue();
		} catch (EmptyStructureException | NotFoundException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("el cliente no se encuentra en la base de datos");
			alert.showAndWait();
		}
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformationWindow.fxml"));
    	fxmlLoader.setController(clientInformation);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainWindow.getMainPane().setCenter(newWindow);
    }

    @FXML
    void undo(ActionEvent event) {
    	try {
			bank.undo();
		} catch (NotFoundException | FullStructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
    }
    
    @FXML
    public void attendQueue(ActionEvent event) throws IOException {
    	
    	try {
			client = bank.searchClientFromQueue();
		} catch (NotFoundException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("el cliente no se encuentra en la base de datos");
			alert.showAndWait();
		}
    	
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformationWindow.fxml"));
    	fxmlLoader.setController(clientInformation);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainWindow.getMainPane().setCenter(newWindow);
    }

    public Client getClient() {
    	return client;
    }
}
