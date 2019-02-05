package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.myproject.jdbc.pojos.ExpenseCategory;
import com.myproject.utilities.ConnectionPool;

public class ExpenseCategoryDao {

	public void create(ExpenseCategory expCat) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "INSERT INTO expensecategory (expcatname,expcatdetails,userid) VALUES (?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, expCat.getExpCatName());
				ps.setString(2, expCat.getExpCatDetails());
				ps.setInt(3, expCat.getUserId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void edit(ExpenseCategory expCat) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "UPDATE expensecategory SET expcatname = ?,expcatdetails = ?,userid = ? WHERE expcatid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, expCat.getExpCatName());
				ps.setString(2, expCat.getExpCatDetails());
				ps.setInt(3, expCat.getUserId());
				ps.setInt(4, expCat.getExpCatId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}
	
	public void remove(int expCatId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "DELETE FROM expensecategory WHERE expcatid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, expCatId);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}
	
	public ExpenseCategory find(int expCatId) {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ExpenseCategory expCat = null;
		if (conn != null) {
			try {
				String sql = "SELECT * FROM expensecategory WHERE expcatid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, expCatId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					expCat = new ExpenseCategory();
					expCat.setExpCatDetails(rs.getString("expcatdetails"));
					expCat.setExpCatId(rs.getInt("expcatid"));
					expCat.setExpCatName(rs.getString("expcatname"));
					expCat.setUserId(rs.getInt("userid"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}

		return expCat;
	}
	
	public ArrayList<ExpenseCategory> findAll() {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<ExpenseCategory> expCatList = new ArrayList<ExpenseCategory>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM expensecategory";
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);
				while(rs.next()) {
					ExpenseCategory expCat = new ExpenseCategory();
					expCat.setExpCatDetails(rs.getString("expcatdetails"));
					expCat.setExpCatId(rs.getInt("expcatid"));
					expCat.setExpCatName(rs.getString("expcatname"));
					expCat.setUserId(rs.getInt("userid"));
					expCatList.add(expCat);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}

		return expCatList;
	}

	public ArrayList<ExpenseCategory> findAll(int userId) {

		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<ExpenseCategory> expCatList = new ArrayList<ExpenseCategory>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM expensecategory WHERE userid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					ExpenseCategory expCat = new ExpenseCategory();
					expCat.setExpCatDetails(rs.getString("expcatdetails"));
					expCat.setExpCatId(rs.getInt("expcatid"));
					expCat.setExpCatName(rs.getString("expcatname"));
					expCat.setUserId(rs.getInt("userid"));
					expCatList.add(expCat);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}

		return expCatList;
	}
}
