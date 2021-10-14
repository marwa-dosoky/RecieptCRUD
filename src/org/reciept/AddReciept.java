package org.reciept;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reciept.dao.RecieptDao;
import org.reciept.model.Reciept;

import com.google.gson.Gson;
import com.google.gson.GsonBuilder;
import com.google.gson.JsonArray;

/**
 * Servlet implementation class AddReciept
 */
@WebServlet("/addReciept")
public class AddReciept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddReciept() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		ArrayList<Reciept> reciepts = new ArrayList<>();
		RecieptDao dao = new RecieptDao();
		try {
			String client_name=request.getParameter("client_name");
			String description=request.getParameter("description");
			dao.insertReciept(new Reciept(client_name, description));
			reciepts = (ArrayList<Reciept>) dao.getReciepts();
			Gson gson= new GsonBuilder().create();
			JsonArray jarray = gson.toJsonTree(reciepts).getAsJsonArray();
			response.getWriter().println(jarray.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

}
