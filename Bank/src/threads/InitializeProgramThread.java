package threads;

import java.io.IOException;

import customStructureExceptions.FullStructureException;
import model.Bank;

public class InitializeProgramThread extends Thread{
	
	private Bank bank;
	
	public InitializeProgramThread(Bank bank) {
		this.bank = bank;
	}
	
	@Override
	public void run() {
		
		try {
			bank.init();
			System.out.println("nice");
		} catch (IOException | FullStructureException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
