package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.myproject.jdbc.pojos.BankBook;
import com.myproject.utilities.ConnectionPool;
import com.myproject.utilities.DateUtils;

public class BankBookDao {
	private final String book = "bankbook";

	public void create(BankBook bB) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "INSERT INTO " + book + " (account,trandate,amount,userid,operation) VALUES(?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, bB.getAccount());
				ps.setDate(2, DateUtils.convertUtilToSql(bB.getTranDate()));
				ps.setDouble(3, bB.getAmount());
				ps.setInt(4, bB.getUserId());
				ps.setString(5, bB.getOperation());
				ps.executeUpdate();
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void edit(BankBook bB) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "UPDATE " + book
						+ " SET account = ?,trandate = ?,amount = ?,userid = ?,operation = ? WHERE acid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, bB.getAccount());
				ps.setDate(2, DateUtils.convertUtilToSql(bB.getTranDate()));
				ps.setDouble(3, bB.getAmount());
				ps.setInt(4, bB.getUserId());
				ps.setString(5, bB.getOperation());
				ps.setInt(6, bB.getAcId());
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

	public BankBook find(int acId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		BankBook bB = null;
		if (conn != null) {
			try {
				String sql = "SELECT * FROM" + book + "WHERE acid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, acId);
				ResultSet rs = ps.executeQuery();
				if (rs.next()) {
					bB = new BankBook();
					bB.setAccount(rs.getString("account"));
					bB.setAcId(rs.getInt("acid"));
					bB.setAmount(rs.getDouble("amount"));
					bB.setOperation(rs.getString("operation"));
					bB.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					bB.setUserId(rs.getInt("userid"));
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}

		}
		return bB;
	}

	public ArrayList<BankBook> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<BankBook> bBList = new ArrayList<BankBook>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM " + book;
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);
				while (rs.next()) {
					BankBook bB = new BankBook();
					bB.setAccount(rs.getString("account"));
					bB.setAcId(rs.getInt("acid"));
					bB.setAmount(rs.getDouble("amount"));
					bB.setOperation(rs.getString("operation"));
					bB.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					bB.setUserId(rs.getInt("userid"));
					bBList.add(bB);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}

		}
		return bBList;
	}

	public ArrayList<BankBook> findAllDateWise(String sDate, String eDate, int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<BankBook> bBList = new ArrayList<BankBook>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM " + book + " WHERE userid = ? AND trandate BETWEEN ? AND ? ";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, userId);
				ps.setDate(2, DateUtils.convertUtilToSql(DateUtils.convertDate(sDate)));
				ps.setDate(3, DateUtils.convertUtilToSql(DateUtils.convertDate(eDate)));
				ResultSet rs = ps.executeQuery();
				while (rs.next()) {
					BankBook bB = new BankBook();
					bB.setAccount(rs.getString("account"));
					bB.setAcId(rs.getInt("acid"));
					bB.setAmount(rs.getDouble("amount"));
					bB.setOperation(rs.getString("operation"));
					bB.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					bB.setUserId(rs.getInt("userid"));
					bBList.add(bB);
				}
			} catch (Exception e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}

		}
		return bBList;
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
	
	public static void main(String args[]) {
		BankBookDao bBD = new BankBookDao();
//		bBD.create(new BankBook(1,"sav",new java.util.Date(),234.5,1,"receive"));
//		bBD.edit(new BankBook(1,"sav",new java.util.Date(),234.5,1,"pay"));
		System.out.println(bBD.findAllDateWise("2018-11-09","2018-11-10",1));
	}
}
