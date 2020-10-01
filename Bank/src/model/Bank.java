package model;

import java.io.BufferedReader;
import java.io.FileReader;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Calendar;
import java.util.GregorianCalendar;
import java.util.List;

import customStructureExceptions.EmptyStructureException;
import customStructureExceptions.FullStructureException;
import customStructureExceptions.KeyDifferenceException;
import customStructureExceptions.NotFoundException;
import structures.*;
import tests.Client;

public class Bank {
	
	public static final String CLIENTS_PATH = "data/BANK_CLIENTS.csv";
	private Queue<ClientInQueue> generalQueue;
	private PriorityQueue<ClientInQueue> priorityQueue;
	private Stack<Client> undoStack;
	private HashTable<Integer,Client> clientDataBase;
	private HashTable<Integer,Client> retiredClients;
	private Calendar calendar = new GregorianCalendar();
	
	public Bank () throws IOException, FullStructureException {
		generalQueue = new Queue<ClientInQueue>();
		priorityQueue = new PriorityQueue<ClientInQueue>(10);
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

	}
	
	public void addToQueue(String name, int id, int disabilities) {
		ClientInQueue newClient = new ClientInQueue(name,id,disabilities);
		generalQueue.enqueue(newClient);
	}
	
	public void addToPriorityQueue(String name, int id, int disabilities) throws KeyDifferenceException {
		ClientInQueue newClient = new ClientInQueue(name,id,disabilities);
		priorityQueue.insert(newClient);
	}
	
	public Client searchClientFromQueue() throws NotFoundException {
		int key = generalQueue.dequeue().getId();
		System.out.println(key);
		System.out.println(clientDataBase.tableLength());
		Client client = clientDataBase.tableRetrieve(key);
		
		return client;
	}
	
	public Client searchClientFromPriorityQueue() throws EmptyStructureException, NotFoundException {
		int key = priorityQueue.extractMax().getId();
		System.out.println(clientDataBase.tableLength());
		Client client = clientDataBase.tableRetrieve(key);
		System.out.println(key);
		return client;
	}
	
	public void payCreditCard(Client client,boolean option,int amount) {
		Client newClient = cloneClient(client);
		undoStack.push(newClient);
		if(option && client.getAccount().getSavings()>=amount) {
			double savings = client.getAccount().getSavings();
			client.getAccount().setSavings(savings-amount);
			double credit = client.getCreditCard().getUsedAmount();
			client.getCreditCard().setUsedAmount(credit-amount);
			
		}else if(!option) {
			double savings = client.getAccount().getSavings();
			client.getAccount().setSavings(savings-amount);
			double credit = client.getCreditCard().getUsedAmount();
			client.getCreditCard().setUsedAmount(credit-amount);
		}
	}
	
	public void cancelAccount(Client client,String reason) throws FullStructureException, NotFoundException {
		String date = String.valueOf(calendar.get(Calendar.HOUR_OF_DAY));
		date = date +" "+String.valueOf(calendar.get(Calendar.SECOND));
		date = date +" "+String.valueOf(calendar.get(Calendar.MINUTE));
		client.getAccount().setRetirementReason(date+"\n"+reason);
		retiredClients.tableInsert(client);
		clientDataBase.tableDelete(client.getId());
	}
	
	public void deposit(Client client, double amount) {
		double savings = client.getAccount().getSavings();
		client.getAccount().setSavings(savings+amount);
		
	}
	
	public void getTable(int sortAlgorithm) throws NotFoundException {
		List<ClientInQueue> list = new ArrayList<>();
		
		List<ClientInQueue> queueList = generalQueue.getElementsList();
		
		for(int i=0;i<queueList.size();i++) {
			list.add(queueList.get(i));
		}
		
		queueList = priorityQueue.getList();
		
		for(int i=0;i<queueList.size();i++) {
			list.add(queueList.get(i));
		}
		
		List<Client> allClientsList = new ArrayList<>();
		
		for(int i=0;i<list.size();i++) {
			int clientkey = list.get(i).getId();
			allClientsList.add(clientDataBase.tableRetrieve(clientkey));
		}

		switch(sortAlgorithm) {

		case 0: mergeSort(allClientsList,0,allClientsList.size()-1);
			
		case 1:	selectionSort(allClientsList);
			
		case 2:
			
		case 3:

		}
	}
	
	public  void merge(List<Client> list,int p,int q, int r){
		
        int n1 = q - p + 1; 
        int n2 = r - q; 
		
		List<Client> left = new ArrayList<>();
		List<Client> right = new ArrayList<>();
		
		for(int i = 0; i < n1; i++) {
			left.add(list.get(p+i));
		}
		
		for(int j = 0; j < n2; j++) {
			right.add(list.get(q+1+j));
		}
		
		int i = 0;
		int j = 0;
		
        int k = p; 
        while (i < n1 && j < n2) { 
			int comparation = left.get(i).getName().compareTo(right.get(j).getName());
			if(comparation<=0) {
				list.set(k, left.get(i));
				i++;
			} 
            else { 
                list.set(k, right.get(j));
				j++;
            } 			

            k++; 
        }
        
        while (i < n1) { 
			list.set(k, left.get(i)); 
            i++; 
            k++; 
        } 
  
        while (j < n2) { 
            list.set(k, right.get(j));
            j++; 
            k++; 
        } 
        
		
	}
	
	public void mergeSort(List<Client> list,int p,int r) {
		
		if(p<r) {
			int q = (p+r)/2;
			mergeSort(list,p,q);
			mergeSort(list,q+1,r);
			merge(list,p,q,r);
		}
	}
	
	public void selectionSort(List<Client> list) {
        for (int i = 0; i < list.size() - 1; i++)  
        {  
            int index = i;  
            for (int j = i + 1; j < list.size(); j++){  
                if (list.get(j).getId() < list.get(index).getId()){  
                    index = j;//searching for lowest index  
                }  
            }  
            Client aux = list.get(index);   
            list.set(index, list.get(i));  
            list.set(i, aux);  
        } 
		
	}
	
	public void quickSort(List<Client> list) {
		Client[] array = new Client[list.size()];
		array = quickSortIterative(array, 0, array.length - 1);
		list = Arrays.asList(array);
	}
	
	public int partition(Client[] c, int low, int high){
		
        double pivot = c[high].getAccount().getSavings(); 
   
        int i = (low - 1); 
        for (int j = low; j <= high - 1; j++) { 

            if (c[j].getAccount().getSavings() <= pivot) { 
                i++; 

                Client temp = c[i]; 
                c[i] = c[j]; 
                c[j] = temp; 
            } 
        } 

        Client temp = c[i + 1]; 
        c[i + 1] = c[high]; 
        c[high] = temp; 
  
        return i + 1; 
    } 
  
    public Client[] quickSortIterative(Client[] c, int l, int h)  { 
         
        int[] stack = new int[h - l + 1]; 
  
        int top = -1; 
  
        stack[++top] = l; 
        stack[++top] = h; 
  
        while (top >= 0) { 
            h = stack[top--]; 
            l = stack[top--]; 
  
            int p = partition(c, l, h); 
  
            if (p - 1 > l) { 
                stack[++top] = l; 
                stack[++top] = p - 1; 
            } 

            if (p + 1 < h) { 
                stack[++top] = p + 1; 
                stack[++top] = h; 
            } 
        } 
        return c;
    }
    
    public void heapSort(List<Client> list) {
    	Client[] array = new Client[list.size()];
    	array = list.toArray(array);
    	array = heapSortRecursive(array);
    	list = Arrays.asList(array);
    }
    
    public Client[] heapSortRecursive(Client[] client) { 
    	int n = client.length; 

    	for (int i = n / 2 - 1; i >= 0; i--) {
    		heapify(client, n, i); 
    	}

    	for (int i=n-1; i>0; i--) { 

    		Client temp = client[0]; 
    		client[0] = client[i]; 
    		client[i] = temp; 

    		heapify(client, i, 0); 
    	}
    	return client;
    } 

    public void heapify(Client[] client, int n, int i) { 
    	int largest = i; 
    	int l = 2*i + 1; 
    	int r = 2*i + 2; 

    	if (l < n && client[l].getEntranceDate().compareTo(client[largest].getEntranceDate()) > 0) {
    		largest = l; 
    	}

    	if (r < n && client[r].getEntranceDate().compareTo(client[largest].getEntranceDate()) > 0) {
    		largest = r; 
    	}

    	if (largest != i) { 
    		Client swap = client[i]; 
    		client[i] = client[largest]; 
    		client[largest] = swap; 

    		heapify(client, n, largest); 
    	} 
    }
    
    public void undo() throws NotFoundException, FullStructureException {
    	if(!undoStack.isEmpty()) {
    		Client client = undoStack.pop();
    		clientDataBase.tableDelete(client.getId());
    		clientDataBase.tableInsert(client);	
    	}
    }

	
	public Client cloneClient(Client client) {
		String name = client.getName();
		int id = client.getId();
		String entranceDate = client.getEntranceDate();
		int disabilities = client.getDisabilities();
		
		int accountID = client.getAccount().getId();
		double accountSavings = client.getAccount().getSavings();
		Account newAccount = new Account(accountID, accountSavings);
		
		int cardID = client.getCreditCard().getId();
		double cardUsedAmount = client.getCreditCard().getUsedAmount();
		CreditCard newCard = new CreditCard(cardID, cardUsedAmount);
		
		
		Client newClient = new Client(name, id, newAccount, newCard, entranceDate, disabilities);
		return newClient;
	}
	
}
