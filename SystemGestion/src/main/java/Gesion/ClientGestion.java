package Gesion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classMetier.client;
import classMetier.TypeUser;
import classMetier.casier;
import mySQL.DBInteraction;

public class ClientGestion implements IClientGestion{

	@Override
	public client authentification(String login, String password) {
		String rqt="select * from clients where login='"+login+"' and password='"+password+"';";
		ResultSet res;
		client user = null;
		DBInteraction.connect();
		res = DBInteraction.selection(rqt);
		try {
			if(res.next()) {
				TypeUser myEnum =  TypeUser.valueOf(res.getString(3));
				user = new client(res.getString(1), res.getString(2),myEnum,res.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println("problem du requete connexion " + e);
		}
		DBInteraction.disconnect();
		return user;
	}

	@Override
	public List<casier> listerCasier(client client) {
		String rqt = "select * from casiers where loginClient='"+client.getLogin()+"';";
		List<casier> list = new ArrayList<>();
		casier cas;
		ResultSet res;
		DBInteraction.connect();
		res = DBInteraction.selection(rqt);
		try {
			while(res.next()) {
				cas = new casier(res.getString(2), res.getString(3), res.getString(4),client);
				cas.setId(res.getString(1));
				list.add(cas);
			}
		} catch (SQLException e) {
			System.out.println("Problem de lister les casiers " + e);
		}
		return list;
	}
	@Override
		public List<casier> findCasier(String champ, client client) {
			String rqt = "select * from casiers where cne LIKE '%"+champ+"%' and loginClient='"+client.getLogin()+"';";
			List<casier> list = new ArrayList<>();
			casier cas;
			ResultSet res;
			DBInteraction.connect();
			res = DBInteraction.selection(rqt);
			try {
				while(res.next()) {
					cas = new casier(res.getString(2), res.getString(3), res.getString(4),client);
					cas.setId(res.getString(1));
					list.add(cas);
				}
			} catch (SQLException e) {
				System.out.println("Problem de lister les casiers " + e);
			}
			return list;
		}
		
		@Override
		public casier findbyCNE(String champ, client client) {
			String rqt = "select * from casiers where cne='"+champ+"' and loginClient='"+client.getLogin()+"';";
			casier cas = null;
			DBInteraction.connect();
			ResultSet res = DBInteraction.selection(rqt);
			try {
				if(res.next()) {
					cas = new casier(res.getString(2), res.getString(3), res.getString(4),client);
					cas.setId(res.getString(1));
				}
			} catch (SQLException e) {
				System.out.println("Problem de lister les casiers " + e);
			}
			return cas;
		}
		
	@Override
	public int updateCasier(casier cas, client client) {
		String rqt ="update casiers set nom='"+cas.getNom()+"', prenom='"+cas.getPrenom()+"', cne='"+cas.getCne()+"' where id='"+cas.getId()+"' and loginClient='"+client.getLogin()+"';";
		DBInteraction.connect();
		int res = DBInteraction.maj(rqt);
		DBInteraction.disconnect();
		return res;
	}
	public static void main(String[] args) {
		IClientGestion gestion = new ClientGestion();
		client cli = new client("client1", "client1", TypeUser.client);
		casier cas = new casier("reqcfrg","Taouis*", "Mohammed Amine*", "r100123311*");
		gestion.updateCasier(cas, cli);
	}
}
