package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;

import customStructureExceptions.FullStructureException;
import structures.*;

public class Bank {
	
	public static final String CLIENTS_PATH = "data/BANK_CLIENTS.csv";
	private Queue<Client> generalQueue;
	private PriorityQueue<Client> priorityQueue;
	private Stack<Client> undoStack;
	private HashTable<Integer,Client> clientDataBase;
	private HashTable<Integer,Client> retiredClients;
	
	public Bank () throws IOException, FullStructureException {
		generalQueue = new Queue<Client>();
		priorityQueue = new PriorityQueue<Client>(10);
		undoStack = new Stack<Client>();
		clientDataBase = new HashTable<Integer,Client>(50);
		retiredClients = new HashTable<Integer,Client>(50);
	}
	
	public void init () throws IOException, FullStructureException {
		
		BufferedReader br = new BufferedReader(new FileReader(CLIENTS_PATH));
		String actualClient = "";
		br.readLine();
		
		/*indices go like this : 
		 0 = name
		 1 = id
		 2 = account id
		 3 = savings
		 4 = card id (can be an empty String, and if its empty, index 5 is also empty)
		 5 = used amount of the credit card
		 6 = bank entrance date
		 7 = number of disabilities
		*/
		
		while ((actualClient = br.readLine()) != null) {
			
			String[] data = actualClient.split(",");
			
			Account account = new Account(Integer.parseInt(data[2]),Double.parseDouble(data[3]));
			
			CreditCard cCard = null;
			
			if (!data[4].equals("")) {
				cCard = new CreditCard(Integer.parseInt(data[4]), Double.parseDouble(data[5]));
			}
			
			Client client = new Client(data[0],Integer.parseInt(data[1]),account,cCard,data[6],
					Integer.parseInt(data[7]));
			
			clientDataBase.tableInsert(client);
		}
		br.close();
	}
	
	
}
