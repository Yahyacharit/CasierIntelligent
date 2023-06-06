package classMetier;

import java.util.ArrayList;
import java.util.List;

public class client {
	private String login; //clé primaire
	private String password;
	private TypeUser role;
	private int nbrCasiers;
	private List<casier> listeCasier;
	
	public client(String login, String password) {
		super();
		this.login = login;
		this.password = password;
	}
	
	public client(String login, String password, TypeUser role) {
		super();
		this.login = login;
		this.password = password;
		this.role = role;
		this.listeCasier = null;
	}

	public client(String login, String password,TypeUser role, int nbrCasiers) {
		super();
		this.login = login;
		this.password = password;
		this.role=role;
		this.nbrCasiers =nbrCasiers;
		this.listeCasier = new ArrayList<>();
	}

	public String getLogin() {
		return login;
	}
	
	public void setLogin(String login) {
		this.login = login;
	}
	
	public String getPassword() {
		return password;
	}
	
	public void setPassword(String password) {
		this.password = password;
	}

	public int getNbrCasiers() {
		return nbrCasiers;
	}

	public void setNbrCasiers(int nbrCasiers) {
		this.nbrCasiers = nbrCasiers;
	}

	public List<casier> getListeCasier() {
		return listeCasier;
	}

	public void setListeCasier(List<casier> listeCasier) {
		this.listeCasier = listeCasier;
	}

	public TypeUser getRole() {
		return role;
	}

	public void setRole(TypeUser role) {
		this.role = role;
	}
	

}
