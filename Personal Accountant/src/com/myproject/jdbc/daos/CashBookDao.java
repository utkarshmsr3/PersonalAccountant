package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.myproject.jdbc.pojos.CashBook;
import com.myproject.utilities.ConnectionPool;
import com.myproject.utilities.DateUtils;

public class CashBookDao {
	private final String book = "cashbook";

	public void create(CashBook cB) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "INSERT INTO " + book + " (account,trandate,amount,userid,operation) VALUES(?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, cB.getAccount());
				ps.setDate(2, DateUtils.convertUtilToSql(cB.getTranDate()));
				ps.setDouble(3, cB.getAmount());
				ps.setInt(4, cB.getUserId());
				ps.setString(5, cB.getOperation());
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void edit(CashBook cB) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "UPDATE " + book
						+ " SET account = ?,trandate = ?,amount = ?,userid = ?,operation = ? WHERE acid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, cB.getAccount());
				ps.setDate(2, DateUtils.convertUtilToSql(cB.getTranDate()));
				ps.setDouble(3, cB.getAmount());
				ps.setInt(4, cB.getUserId());
				ps.setString(5, cB.getOperation());
				ps.setInt(6, cB.getAcId());
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void remove(int acId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "DELETE FROM " + book + "WHERE acid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, acId);
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}

		}
	}
	
	public CashBook find(int acId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		CashBook cB = null;
		if (conn != null) {
			try {
				String sql = "SELECT * FROM" + book + "WHERE acid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, acId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					cB = new CashBook();
					cB.setAccount(rs.getString("account"));
					cB.setAcId(rs.getInt("acid"));
					cB.setAmount(rs.getDouble("amount"));
					cB.setOperation(rs.getString("operation"));
					cB.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					cB.setUserId(rs.getInt("userid"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}

		}
		return cB;
	}
	
	public ArrayList<CashBook> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<CashBook> cBList = new ArrayList<CashBook>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM " + book;
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);
				while(rs.next()) {
					CashBook cB = new CashBook();
					cB.setAccount(rs.getString("account"));
					cB.setAcId(rs.getInt("acid"));
					cB.setAmount(rs.getDouble("amount"));
					cB.setOperation(rs.getString("operation"));
					cB.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					cB.setUserId(rs.getInt("userid"));
					cBList.add(cB);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}

		}
		return cBList;
	}
	
	public ArrayList<CashBook> findAllDateWise(String sDate, String eDate, int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<CashBook> cBList = new ArrayList<CashBook>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM " + book + " WHERE userid = ? AND trandate BETWEEN ? AND ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setDate(2, DateUtils.convertUtilToSql(DateUtils.convertDate(sDate)));
				ps.setDate(3, DateUtils.convertUtilToSql(DateUtils.convertDate(eDate)));
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					CashBook cB = new CashBook();
					cB.setAccount(rs.getString("account"));
					cB.setAcId(rs.getInt("acid"));
					cB.setAmount(rs.getDouble("amount"));
					cB.setOperation(rs.getString("operation"));
					cB.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					cB.setUserId(rs.getInt("userid"));
					cBList.add(cB);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}

		}
		return cBList;
	}

	public double closingBalance(int userId) {
		double closingBalance = 0;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "SELECT sum(amount) as totalpayment FROM " + book + " WHERE userid = ? AND operation = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setString(2, "receive");
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					closingBalance = rs.getDouble("totalpayment");
				}
				sql = "SELECT sum(amount) as totalreceived FROM " + book + " WHERE userid = ? AND operation = ?";
				ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setString(2, "pay");
				rs = ps.executeQuery();
				if(rs.next()) {
					closingBalance -= rs.getDouble("totalreceived");
				}
				
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return closingBalance;
	}

}
