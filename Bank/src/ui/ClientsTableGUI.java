package ui;

import javafx.fxml.FXML;
import javafx.scene.control.ComboBox;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import model.Bank;

public class ClientsTableGUI {
	
	private Bank bank;

    @FXML
    private TableView<?> clientsTable;

    @FXML
    private TableColumn<?, ?> columnName;

    @FXML
    private TableColumn<?, ?> columnID;

    @FXML
    private TableColumn<?, ?> columnAmmount;

    @FXML
    private TableColumn<?, ?> columnVinculationTime;

    @FXML
    private TableColumn<?, ?> columnDisabilities;

    @FXML
    private ComboBox<?> sortOptions;

    public ClientsTableGUI(Bank bank) {
    	this.bank = bank;
    }
    
}
