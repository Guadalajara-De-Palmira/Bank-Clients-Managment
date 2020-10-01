package model;

public class ClientInQueue implements Comparable<ClientInQueue>{
	
	private String name;
	private int id;
	private int disabilities;
	
	public ClientInQueue(String name, int id, int disabilities) {
		this.name = name;
		this.id = id;
		this.disabilities = disabilities;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public int getDisabilities() {
		return disabilities;
	}

	public void setDisabilities(int disabilities) {
		this.disabilities = disabilities;
	}

	@Override
	public int compareTo(ClientInQueue o) {
		int comparation = this.disabilities-o.getDisabilities();
		return comparation;
	}
}
