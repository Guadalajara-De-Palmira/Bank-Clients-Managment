package ui;

import java.io.IOException;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.canvas.Canvas;
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
    void attendPriorityQueue(ActionEvent event) throws IOException {
    	
    	//añadir que aquí se haga una busqueda del cliente con priorityQueue
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformationWindow.fxml"));
    	fxmlLoader.setController(clientInformation);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainWindow.getMainPane().setCenter(newWindow);
    }

    @FXML
    void attendQueue(ActionEvent event) throws IOException {
    	//añadir que aquí se haga una busqueda del cliente con Queue
    	FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("ClientInformationWindow.fxml"));
    	fxmlLoader.setController(clientInformation);
    	
    	Parent newWindow = fxmlLoader.load();
    	mainWindow.getMainPane().setCenter(newWindow);
    }

    public Client getClient() {
    	return client;
    }
}
