package org.reciept.model;

public class Reciept {

	int id;
	String client_name;
	String description;

	public Reciept() {

	}

	public Reciept(int id, String client_name, String description) {
		super();
		this.id = id;
		this.client_name = client_name;
		this.description = description;
	}

	public Reciept( String client_name, String description) {
		super();
		this.client_name = client_name;
		this.description = description;
	}
	@Override
	public String toString() {
		return "Reciept [id=" + id + ", client_name=" + client_name + ", description=" + description + "]";
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public String getClient_name() {
		return client_name;
	}

	public void setClient_name(String client_name) {
		this.client_name = client_name;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}
}
