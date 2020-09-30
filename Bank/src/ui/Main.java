package ui;

import java.io.IOException;

import customStructureExceptions.FullStructureException;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.event.EventHandler;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import javafx.stage.WindowEvent;
import model.Bank;

public class Main extends Application{

	private MainWindowGUI mainWindowGUI;
	
	private Bank bank;
	
	public Main() throws IOException, FullStructureException {
		bank = new Bank();
		mainWindowGUI = new MainWindowGUI(bank);
	}
	
	public static void main(String[]args) {
		launch();
	}

	@Override
	public void start(Stage primaryStage) throws Exception {
		FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("MainWindow.fxml")); 
		fxmlLoader.setController(mainWindowGUI);
		
		Parent root = fxmlLoader.load();
		Scene scene = new Scene(root,900,500);
		primaryStage.setScene(scene);
		primaryStage.setTitle("Bank");
		primaryStage.setResizable(true);
		primaryStage.show();
		
		primaryStage.setOnCloseRequest(new EventHandler<WindowEvent>() {
		    @Override
		    public void handle(WindowEvent t) {
		        Platform.exit();
		        System.exit(0);
		    }
		});
		
	}
	
}
