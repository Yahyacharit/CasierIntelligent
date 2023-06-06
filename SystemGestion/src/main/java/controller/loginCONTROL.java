package controller;

import java.io.IOException;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Gesion.ClientGestion;
import Gesion.IClientGestion;
import classMetier.TypeUser;
import classMetier.client;

@WebServlet("/loginCONTROL")
public class loginCONTROL extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		HttpSession sess = request.getSession();
		client user = (client) sess.getAttribute("user");
		if( user == null) {
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if( user.getRole() == TypeUser.admin ){
			request.getRequestDispatcher("adminPanel.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("clientPanel.jsp").forward(request, response);
		}
	}


	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		String login = request.getParameter("login");
		String password = request.getParameter("password");
		IClientGestion auth = new ClientGestion();
		client user = auth.authentification(login, password);
		HttpSession sess = request.getSession();
		if( user == null ){
			request.setAttribute("msg", "Email ou Mot de passe incorrecte");
			request.getRequestDispatcher("login.jsp").forward(request, response);
		}
		else if(user.getRole()==TypeUser.client) {
			sess.setAttribute("user", user);
			request.getRequestDispatcher("/clientPanelCONTROL").forward(request, response);
		}
		else {
			sess.setAttribute("user", user);
			request.getRequestDispatcher("adminPanelCONTROL").forward(request, response);
		}

}
}