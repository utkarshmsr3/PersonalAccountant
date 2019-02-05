package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.myproject.jdbc.pojos.IncomeCategory;
import com.myproject.utilities.ConnectionPool;

public class IncomeCategoryDao {
	public void create(IncomeCategory incCat) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "INSERT INTO incomecategory (inccatname,inccatdetails,userid) VALUES (?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, incCat.getIncCatName());
				ps.setString(2, incCat.getIncCatDetails());
				ps.setInt(3, incCat.getUserId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void edit(IncomeCategory incCat) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "UPDATE incomecategory SET inccatname = ?,inccatdetails = ?,userid = ? WHERE inccatid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, incCat.getIncCatName());
				ps.setString(2, incCat.getIncCatDetails());
				ps.setInt(3, incCat.getUserId());
				ps.setInt(4, incCat.getIncCatId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}
	
	public void remove(int incCatId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "DELETE FROM incomecategory WHERE inccatid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, incCatId);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}
	
	public IncomeCategory find(int incCatId) {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		IncomeCategory incCat = null;
		if (conn != null) {
			try {
				String sql = "SELECT * FROM incomecategory WHERE inccatid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, incCatId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					incCat = new IncomeCategory();
					incCat.setIncCatDetails(rs.getString("inccatdetails"));
					incCat.setIncCatId(rs.getInt("inccatid"));
					incCat.setIncCatName(rs.getString("inccatname"));
					incCat.setUserId(rs.getInt("userid"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}

		return incCat;
	}
	
	public ArrayList<IncomeCategory> findAll() {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<IncomeCategory> incCatList = new ArrayList<IncomeCategory>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM incomecategory";
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);
				while(rs.next()) {
					IncomeCategory expCat = new IncomeCategory();
					expCat.setIncCatDetails(rs.getString("inccatdetails"));
					expCat.setIncCatId(rs.getInt("inccatid"));
					expCat.setIncCatName(rs.getString("inccatname"));
					expCat.setUserId(rs.getInt("userid"));
					incCatList.add(expCat);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}

		return incCatList;
	}
	
	public ArrayList<IncomeCategory> findAll(int userId) {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<IncomeCategory> incCatList = new ArrayList<IncomeCategory>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM incomecategory WHERE userid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					IncomeCategory expCat = new IncomeCategory();
					expCat.setIncCatDetails(rs.getString("inccatdetails"));
					expCat.setIncCatId(rs.getInt("inccatid"));
					expCat.setIncCatName(rs.getString("inccatname"));
					expCat.setUserId(rs.getInt("userid"));
					incCatList.add(expCat);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}

		return incCatList;
	}
}
