package org.reciept.dao;

import java.sql.*;
import java.util.*;

import org.reciept.model.Reciept;
import org.reciept.util.DBConnector;

public class RecieptDao {

	static Connection con = DBConnector.getConnection();

	public List<Reciept> getReciepts() throws SQLException {
		String query = "select * from RECEIPT";
		PreparedStatement ps = con.prepareStatement(query);
		ResultSet rs = ps.executeQuery();
		List<Reciept> ls = new ArrayList();

		while (rs.next()) {
			Reciept rec = new Reciept();
			rec.setId(rs.getInt("id"));
			rec.setClient_name(rs.getString("client_name"));
			rec.setDescription(rs.getString("description"));
			ls.add(rec);
		}

		return ls;
	}

/*	public List<Reciept> getRecieptsProc() throws SQLException {
		CallableStatement cs = null;
		ResultSet rs;
		List<Reciept> ls = new ArrayList();
		cs = con.prepareCall("{call select_reciept}");
		rs = cs.executeQuery();
		while (rs.next()) {
			Reciept rec = new Reciept();
			rec.setId(rs.getInt("id"));
			rec.setClient_name(rs.getString("client_name"));
			rec.setDescription(rs.getString("description"));
			System.out.println(rec.toString());
			ls.add(rec);
		}
		return ls;
	}*/

	public void insertReciept(Reciept reciept) throws SQLException {
		CallableStatement cs = null;
		cs = con.prepareCall("{call insertReceipt(?,?)}");
		cs.setString(1, reciept.getClient_name());
		cs.setString(2, reciept.getDescription());
		cs.executeQuery();

	}
	
	public void updateReciept(Reciept reciept) throws SQLException {
		CallableStatement cs = null;
		cs = con.prepareCall("{call updateReceipt(?,?,?)}");
		cs.setInt(1, reciept.getId());
		cs.setString(2, reciept.getClient_name());
		cs.setString(3, reciept.getDescription());
		cs.executeQuery();

	}
	
	
	public void deleteReciept(int recieptID) throws SQLException {
		CallableStatement cs = null;
		cs = con.prepareCall("{call deleteReceipt(?)}");
		cs.setInt(1, recieptID);
		cs.executeQuery();

	}


	public static void main(String[] args) {
		RecieptDao dao = new RecieptDao();
		try {
			dao.getReciepts();
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
