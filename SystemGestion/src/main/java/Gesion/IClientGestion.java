package Gesion;

import java.util.List;

import classMetier.client;
import classMetier.casier;

public interface IClientGestion {
	public client authentification(String email, String password);
	public List<casier> listerCasier(client client);
	public List<casier> findCasier(String champ, client client);
	public casier findbyCNE(String champ, client client);
	public int updateCasier(casier cas, client client);
}
