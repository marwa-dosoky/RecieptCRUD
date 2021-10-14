package org.reciept;

import java.io.IOException;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.function.Consumer;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.reciept.dao.RecieptDao;
import org.reciept.model.Reciept;

import com.google.gson.*;

/**
 * Servlet implementation class RecieptServc
 */
@WebServlet(description = "reciept main service", urlPatterns = { "/recieptServc" })
public class RecieptServc extends HttpServlet {
	private static final long serialVersionUID = 1L;
	

	/**
	 * @see HttpServlet#HttpServlet()
	 */
	public RecieptServc() {
		super();
		// TODO Auto-generated constructor stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		ArrayList<Reciept> reciepts = new ArrayList<>();
		RecieptDao dao = new RecieptDao();
		try {
			reciepts = (ArrayList<Reciept>) dao.getReciepts();
			Gson gson= new GsonBuilder().create();
			JsonArray jarray = gson.toJsonTree(reciepts).getAsJsonArray();
			response.getWriter().println(jarray.toString());

		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse
	 *      response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response)
			throws ServletException, IOException {
		// TODO Auto-generated method stub
		response.getWriter().append("Served at: ").append(request.getContextPath());
	}

}
