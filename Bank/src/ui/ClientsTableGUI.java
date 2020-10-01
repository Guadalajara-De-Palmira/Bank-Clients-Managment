package ui;

import java.util.List;

import customStructureExceptions.NotFoundException;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Alert;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import jdk.internal.org.objectweb.asm.tree.IntInsnNode;
import model.Bank;
import model.Client;

public class ClientsTableGUI {
	
	private Bank bank;

    @FXML
    private TableView<Client> clientsTable;

    @FXML
    private TableColumn<Client, String> columnName;

    @FXML
    private TableColumn<Client, Integer> columnID;

    @FXML
    private TableColumn<Client, Double> columnAmmount;

    @FXML
    private TableColumn<Client, String> columnVinculationTime;

    @FXML
    private TableColumn<Client, Integer> columnDisabilities;

    @FXML
    private ComboBox<String> sortOptions;

    public ClientsTableGUI(Bank bank) {
    	this.bank = bank;
    }
    
    @FXML
    void sort(ActionEvent event) {
    	if(sortOptions.getValue()==null) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("debe escoger una opcion para ordenar la tabla");
			alert.showAndWait();
    	}else {
    		int sortby = -1;
    		if(sortOptions.getValue().equalsIgnoreCase("ordenar por nombre")) {
    			sortby = 0;
    		}else if(sortOptions.getValue().equalsIgnoreCase("Ordenar por id")) {
    			sortby = 1;
    		}else if(sortOptions.getValue().equalsIgnoreCase("Ordenar por tiempo")) {
    			sortby = 2;
    		}else if(sortOptions.getValue().equalsIgnoreCase("Ordenar por monto")) {
    			sortby = 3;
    		}
    		try {
				List<Client> list = bank.getTable(sortby);
				initializateTable(list);
			} catch (NotFoundException e) {
				Alert alert = new Alert(Alert.AlertType.INFORMATION);
				alert.setHeaderText("Error");
				alert.setTitle("Alert");
				alert.setContentText("esto no deberia salir pero weno, cualquier cosa es posible");
				alert.showAndWait();
			}
    		
    	}
    }
    
    private void initializateTable(List<Client> clients) {
    	ObservableList obs = FXCollections.observableArrayList(clients);
    	clientsTable.setItems(obs);
	 	columnName.setCellValueFactory(new PropertyValueFactory<Client,String>("name"));
	 	columnID.setCellValueFactory(new PropertyValueFactory<Client,Integer>("id"));
	 	columnAmmount.setCellValueFactory(new PropertyValueFactory<Client,Double>("amount"));
	 	columnVinculationTime.setCellValueFactory(new PropertyValueFactory<Client,String>("entranceDate"));
	 	columnDisabilities.setCellValueFactory(new PropertyValueFactory<Client,Integer>("disabilities"));
    }
    
    public void initialize() {
    	initializeComboBox();
    	try {
			List<Client> list = bank.getTable(0);
			initializateTable(list);
		} catch (NotFoundException e) {
			Alert alert = new Alert(Alert.AlertType.INFORMATION);
			alert.setHeaderText("Error");
			alert.setTitle("Alert");
			alert.setContentText("esto no deberia salir pero weno, cualquier cosa es posible");
			alert.showAndWait();
		}
    }
    

    public void initializeComboBox() {
    	sortOptions.getItems().addAll("ordenar por nombre","Ordenar por id","Ordenar por tiempo","Ordenar por monto");
    }
    
    public String getComboBox() {
    	return sortOptions.getValue();
    }
    
}
