package Gesion;

import java.util.List;

import classMetier.casier;
import classMetier.client;

public interface IAdminGestion {
	public int AddUser(client client);
	public int UpdateUser(client client);
	public List<client> listerUser();
	public List<client> findUser(String log);
	public client findbyLogin(String log);
	public int addCasier(client client, casier cas);
	public int deleteCasier(String id, client client);
}
