package controller;

import java.io.IOException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Gesion.AdminGestion;
import Gesion.ClientGestion;
import Gesion.IAdminGestion;
import Gesion.IClientGestion;
import classMetier.TypeUser;
import classMetier.casier;
import classMetier.client;


@WebServlet("/adminPanelCONTROL")
public class adminPanelCONTROL extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		IAdminGestion gestion = new AdminGestion();
		client admin =(client) sess.getAttribute("user");
		List<client> liste = null;
		
		if(admin == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			String search = request.getParameter("search");
			String update = request.getParameter("update");
			String logOut = request.getParameter("logOut");
			String addUser = request.getParameter("addUser");
			String addCasier = request.getParameter("addCasier");
			if( search != null ) {
				liste = gestion.findUser(search);
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("/adminPanel.jsp").forward(request, response);
			}
			else if(update != null) {
				client userUp = gestion.findbyLogin(update);
				request.setAttribute("userUp", userUp);
				request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
			}else if(logOut != null){
				request.getSession(false).invalidate();
				request.getRequestDispatcher("login.jsp").forward(request, response);
			} else if(addUser != null){
				request.setAttribute("addUser", addUser);
				request.getRequestDispatcher("userUpdate.jsp").forward(request, response);
			}else if(addCasier != null){
				String par = request.getParameter("login");
				request.setAttribute("login", par);
				request.setAttribute("addCasier", addCasier);
				request.getRequestDispatcher("casierUpdate.jsp").forward(request, response);
			}else {
				liste = gestion.listerUser();
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
		}
	}
	}
	


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		IAdminGestion gestion = new AdminGestion();
		IClientGestion gest = new ClientGestion();
		String opUser = request.getParameter("opUser");
		String opCasier = request.getParameter("opCasier");
		client admin = (client) sess.getAttribute("user");
		List<client> liste;
		if( admin != null) {
			if(opUser != null) {
				String password = request.getParameter("password");
				String login = request.getParameter("login");
				TypeUser myenum = TypeUser.valueOf(request.getParameter("role"));
				if(opUser.equals("Ajouter")) {
					gestion.AddUser(new client(login, password, myenum));
					liste = gestion.listerUser();
					request.setAttribute("liste", liste);
					request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
				}else {
					gestion.UpdateUser(new client(login, password, myenum));
					liste = gestion.listerUser();
					request.setAttribute("liste", liste);
					request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
				}
			}else if(opCasier != null) {
					String nom = request.getParameter("nom");
					String prenom = request.getParameter("prenom");
					String cne = request.getParameter("cne");
					String id = request.getParameter("id");
					String login = request.getParameter("login");
					casier cas = new casier(id,nom, prenom, cne);
					client log = gestion.findbyLogin(login);
					if(opCasier.equals("Ajouter")) {
						gestion.addCasier(log,cas);
						request.setAttribute("userActif", log);
					}else {
						gest.updateCasier(cas, admin);
						request.setAttribute("userActif", admin);
					}
					request.getRequestDispatcher("clientPanelCONTROL").forward(request, response);
			}else {
				liste = gestion.listerUser();
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("adminPanel.jsp").forward(request,response);
			}
		}else {
			request.getRequestDispatcher("login.jsp").forward(request,response);
		}
}
}