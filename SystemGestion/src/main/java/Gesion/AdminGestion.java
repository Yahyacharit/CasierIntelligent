package Gesion;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import classMetier.TypeUser;
import classMetier.casier;
import classMetier.client;
import mySQL.DBInteraction;

public class AdminGestion implements IAdminGestion {

	@Override
	public int AddUser(client client) {
		String rqt = "insert into clients(login,password,role,NbCasier) values('"+client.getLogin()+"','"+client.getPassword()+"','"+client.getRole()+"',"+client.getNbrCasiers()+");";
		DBInteraction.connect();
		int res = DBInteraction.maj(rqt);
		DBInteraction.disconnect();
		return res;
	}

	@Override
	public int UpdateUser(client client) {
		String rqt = "update clients set password='"+client.getPassword()+"' where login='"+client.getLogin()+"';";
		DBInteraction.connect();
		int res = DBInteraction.maj(rqt);
		DBInteraction.disconnect();
		return res;
	}

	@Override
	public List<client> listerUser() {
		String rqt = "select * from Clients;";
		List<client> list = new ArrayList<>();
		client user;
		ResultSet res;
		DBInteraction.connect();
		res = DBInteraction.selection(rqt);
		try {
			while(res.next()) {
				TypeUser myEnum =  TypeUser.valueOf(res.getString(3));
				user = new client(res.getString(1),res.getString(2), myEnum, res.getInt(4));
				list.add(user);
			}
		} catch (SQLException e) {
			System.out.println("Problem de lister les casiers " + e);
		}
		return list;
	}

	@Override
	public List<client> findUser(String log) {
		String rqt = "select * from clients where login LIKE '%"+log+"%';";
		List<client> liste = new ArrayList<>();
		client user =null;
		DBInteraction.connect();
		ResultSet res = DBInteraction.selection(rqt);
		try {
			while(res.next()) {
				TypeUser myEnum =  TypeUser.valueOf(res.getString(3));
				user = new client(res.getString(1),res.getString(2), myEnum, res.getInt(4));
				liste.add(user);
			}
		} catch (SQLException e) {
			System.out.println("problem sur FINDUSER() " + e);
		}
		DBInteraction.disconnect();
		return liste;
	}

	@Override
	public int addCasier(client client, casier cas) {
		String rqt = "insert into casiers(id,nom, prenom,cne,loginClient) values('"+cas.getId()+"','"+cas.getNom()+"','"+cas.getPrenom()+"','"+cas.getCne()+"','"+ client.getLogin()+"');";
		DBInteraction.connect();
		int res = DBInteraction.maj(rqt);
		DBInteraction.disconnect();
		return res;
	}

	@Override
	public int deleteCasier(String id, client client) {
		String rqt = "delete from casiers where cne='"+id+"' and loginClient='"+client.getLogin()+"';";
		DBInteraction.connect();
		int res = DBInteraction.maj(rqt);
		DBInteraction.disconnect();
		return res;
	}

	@Override
	public client findbyLogin(String log) {
		String rqt = "select * from clients where login='"+log+"';";
		client user =null;
		DBInteraction.connect();
		ResultSet res = DBInteraction.selection(rqt);
		try {
			if(res.next()) {
				TypeUser myEnum =  TypeUser.valueOf(res.getString(3));
				user = new client(res.getString(1),res.getString(2), myEnum, res.getInt(4));
			}
		} catch (SQLException e) {
			System.out.println("problem sur FINDUSER() " + e);
		}
		DBInteraction.disconnect();
		return user;
	}
}
