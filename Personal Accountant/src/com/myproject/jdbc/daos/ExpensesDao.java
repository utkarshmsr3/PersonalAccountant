package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.myproject.jdbc.pojos.Expenses;
import com.myproject.utilities.ConnectionPool;
import com.myproject.utilities.DateUtils;

public class ExpensesDao {
	public void create(Expenses expense) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "INSERT INTO expenses (expac,userid,expcatid,amount,trandate,payby,remark) VALUES(?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, expense.getExpAc());
				ps.setInt(2, expense.getUserId());
				ps.setInt(3, expense.getExpCatId());
				ps.setDouble(4, expense.getAmount());
				ps.setDate(5, DateUtils.convertUtilToSql(expense.getTranDate()));
				ps.setString(6, expense.getPayBy());
				ps.setString(7, expense.getRemark());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void edit(Expenses expense) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "UPDATE expenses SET expac = ?,userid = ?,expcatid = ?,amount = ?,trandate = ?,payby = ?,remark = ?  WHERE expid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, expense.getExpAc());
				ps.setInt(2, expense.getUserId());
				ps.setInt(3, expense.getExpCatId());
				ps.setDouble(4, expense.getAmount());
				ps.setDate(5, DateUtils.convertUtilToSql(expense.getTranDate()));
				ps.setString(6, expense.getPayBy());
				ps.setString(7, expense.getRemark());
				ps.setInt(8, expense.getExpId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void remove(int expId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "DELETE FROM expenses WHERE expid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, expId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public Expenses find(int expId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		Expenses expense = null;
		if (conn != null) {
			try {
				String sql = "SELECT * FROM expenses WHERE expid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, expId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					expense = new Expenses();
					expense.setAmount(rs.getDouble("amount"));
					expense.setExpAc(rs.getString("expac"));
					expense.setExpCatId(rs.getInt("expcatid"));
					expense.setExpId(rs.getInt("expid"));
					expense.setPayBy(rs.getString("payby"));
					expense.setRemark(rs.getString("remark"));
					expense.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					expense.setUserId(rs.getInt("userid"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return expense;
	}
	
	public ArrayList<Expenses> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<Expenses> expenseList = new ArrayList<Expenses>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM expenses";
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);
				while(rs.next()) {
					Expenses expense = new Expenses();
					expense.setAmount(rs.getDouble("amount"));
					expense.setExpAc(rs.getString("expac"));
					expense.setExpCatId(rs.getInt("expcatid"));
					expense.setExpId(rs.getInt("expid"));
					expense.setPayBy(rs.getString("payby"));
					expense.setRemark(rs.getString("remark"));
					expense.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					expense.setUserId(rs.getInt("userid"));
					expenseList.add(expense);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return expenseList;
	}
	
	public ArrayList<Expenses> findAll(int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<Expenses> expenseList = new ArrayList<Expenses>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM expenses WHERE userid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Expenses expense = new Expenses();
					expense.setAmount(rs.getDouble("amount"));
					expense.setExpAc(rs.getString("expac"));
					expense.setExpCatId(rs.getInt("expcatid"));
					expense.setExpId(rs.getInt("expid"));
					expense.setPayBy(rs.getString("payby"));
					expense.setRemark(rs.getString("remark"));
					expense.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					expense.setUserId(rs.getInt("userid"));
					expenseList.add(expense);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return expenseList;
	}
	
	public ArrayList<Expenses> findAllDateWise(String sDate, String eDate, int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<Expenses> expenseList = new ArrayList<Expenses>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM expenses WHERE userid = ? AND trandate BETWEEN ? AND ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setDate(2, DateUtils.convertUtilToSql(DateUtils.convertDate(sDate)));
				ps.setDate(3, DateUtils.convertUtilToSql(DateUtils.convertDate(eDate)));
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Expenses expense = new Expenses();
					expense.setAmount(rs.getDouble("amount"));
					expense.setExpAc(rs.getString("expac"));
					expense.setExpCatId(rs.getInt("expcatid"));
					expense.setExpId(rs.getInt("expid"));
					expense.setPayBy(rs.getString("payby"));
					expense.setRemark(rs.getString("remark"));
					expense.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					expense.setUserId(rs.getInt("userid"));
					expenseList.add(expense);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return expenseList;
	}
}
