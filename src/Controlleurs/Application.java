package Controlleurs;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

/**
 * Servlet implementation class Application
 */
@WebServlet("/Application")
public class Application extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Application() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		if(request.getParameter("action").equals("index")){
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
		if(request.getParameter("action").equals("home")){
			request.getRequestDispatcher("WEB-INF/home.jsp").forward(request, response);
		}
		if(request.getParameter("action").equals("sign_up")){
			request.getRequestDispatcher("sign_up.jsp").forward(request, response);
		}
		if(request.getParameter("action").equals("sign_in")){
			request.getRequestDispatcher("sign_in.jsp").forward(request, response);
		}
		if(request.getParameter("action").equals("disconnect")){
			request.setAttribute("disconnect", "true");
			request.getRequestDispatcher("WEB-INF/disconnect.jsp").forward(request, response);
		} else {
			request.getRequestDispatcher("index.jsp").forward(request, response);
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

}
