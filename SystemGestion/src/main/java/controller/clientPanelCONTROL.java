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
import classMetier.casier;
import classMetier.client;

@WebServlet("/clientPanelCONTROL")
public class clientPanelCONTROL extends HttpServlet {
	private static final long serialVersionUID = 1L;


	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		IClientGestion gest = new ClientGestion();
		IAdminGestion gestion = new AdminGestion();
		client user =(client) sess.getAttribute("user");
		String search = request.getParameter("search");
		String update = request.getParameter("update");
		String delete = request.getParameter("delete");
		String logOut = request.getParameter("logOut");
		String lister = request.getParameter("liste");
		List<casier> liste = null;
		
			if(user == null) {
				request.getRequestDispatcher("login.jsp").forward(request, response);
			}
			else if(request.getParameter("login") != null) {
				client admin = gestion.findbyLogin(request.getParameter("login"));
				if( search != null ) {
					liste = gest.findCasier(search, admin);
					request.setAttribute("liste", liste);
					request.setAttribute("userActif", admin);
					request.getRequestDispatcher("/clientpanel.jsp").forward(request, response);
				}else if(update != null) {
					casier cas = gest.findbyCNE(update, admin);
					request.setAttribute("cas", cas);
					request.setAttribute("userActif", admin);
					request.getRequestDispatcher("casierUpdate.jsp").forward(request, response);
				}else if(logOut != null){
					request.getSession(false).invalidate();
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else if(lister != null) {
					liste = gest.listerCasier(admin);
					request.setAttribute("userActif", admin);
					request.setAttribute("liste", liste);
					request.getRequestDispatcher("clientpanel.jsp").forward(request, response);
				}else if(delete != null) {
					gestion.deleteCasier(delete, admin);
					liste = gest.listerCasier(admin);
					request.setAttribute("userActif", admin);
					request.setAttribute("liste", liste);
					request.getRequestDispatcher("/clientpanel.jsp").forward(request, response);
				}
				else {
					liste = gest.listerCasier(admin);
					request.setAttribute("liste", liste);
					request.setAttribute("userActif", admin);
					request.getRequestDispatcher("/clientpanel.jsp").forward(request, response);
				}
			}else {
				if( search != null ) {
					liste = gest.findCasier(search, user);
					request.setAttribute("user", user);
					request.setAttribute("liste", liste);
					request.getRequestDispatcher("/clientpanel.jsp").forward(request, response);
				}
				else if(update != null) {
					casier cas = gest.findbyCNE(update, user);
					request.setAttribute("cas", cas);
					request.getRequestDispatcher("casierUpdate.jsp").forward(request, response);
				}else if(logOut != null){
					request.getSession(false).invalidate();
					request.getRequestDispatcher("/login.jsp").forward(request, response);
				}else {
					liste = gest.listerCasier(user);
					request.setAttribute("user", user);
					request.setAttribute("liste", liste);
					request.getRequestDispatcher("/clientpanel.jsp").forward(request, response);
				}
			}
	}

	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		client user = (client) sess.getAttribute("user");
		if(user == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else {
			List<casier> liste;
			IClientGestion gest = new ClientGestion();
			if(request.getAttribute("userActif") != null) {
				client userActif =(client) request.getAttribute("userActif");
				liste = gest.listerCasier(userActif);
				request.setAttribute("userActif", userActif);
				request.setAttribute("liste", liste);
				request.getRequestDispatcher("clientpanel.jsp").forward(request, response);
			}else {
			liste = gest.listerCasier(user);
			request.setAttribute("user", user);
			request.setAttribute("liste", liste);
			request.getRequestDispatcher("clientpanel.jsp").forward(request, response);
		}
		}
	}

}
