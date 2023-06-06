package classMetier;

public class casier {
	private String id;
	private String nom;
	private String prenom;
	private String cne;
	private client log;
	
	public casier(String id, String nom, String prenom, String cne) {
		super();
		this.id = id;
		this.nom = nom;
		this.prenom = prenom;
		this.cne = cne;
	}
	public casier(String nom, String prenom, String cne, client log) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cne = cne;
		this.log = log;
	}
	public casier(String nom, String prenom, String cne) {
		super();
		this.nom = nom;
		this.prenom = prenom;
		this.cne = cne;
	}
	public String getId() {
		return id;
	}
	public void setId(String id) {
		this.id = id;
	}
	public String getNom() {
		return nom;
	}
	public void setNom(String nom) {
		this.nom = nom;
	}
	public String getPrenom() {
		return prenom;
	}
	public void setPrenom(String prenom) {
		this.prenom = prenom;
	}
	public String getCne() {
		return cne;
	}
	public void setCne(String cne) {
		this.cne = cne;
	}
	public client getLog() {
		return log;
	}
	public void setLog(client log) {
		this.log = log;
	}
}
