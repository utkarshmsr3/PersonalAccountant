package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import com.myproject.jdbc.pojos.Incomes;
import com.myproject.utilities.ConnectionPool;
import com.myproject.utilities.DateUtils;

public class IncomesDao {
	public void create(Incomes income) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "INSERT INTO incomes (incac,userid,inccatid,amount,trandate,receiveby,remark) VALUES(?,?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, income.getIncAc());
				ps.setInt(2, income.getUserId());
				ps.setInt(3, income.getIncCatId());
				ps.setDouble(4, income.getAmount());
				ps.setDate(5, DateUtils.convertUtilToSql(income.getTranDate()));
				ps.setString(6, income.getReceiveBy());
				ps.setString(7, income.getRemark());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void edit(Incomes income) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "UPDATE incomes SET incac = ?,userid = ?,inccatid = ?,amount = ?,trandate = ?,receiveby = ?,remark = ?  WHERE incid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1, income.getIncAc());
				ps.setInt(2, income.getUserId());
				ps.setInt(3, income.getIncCatId());
				ps.setDouble(4, income.getAmount());
				ps.setDate(5, DateUtils.convertUtilToSql(income.getTranDate()));
				ps.setString(6, income.getReceiveBy());
				ps.setString(7, income.getRemark());
				ps.setInt(8, income.getIncId());
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public void remove(int incId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "DELETE FROM incomes WHERE incid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, incId);
				ps.executeUpdate();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}

	public Incomes find(int incId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		Incomes income = null;
		if (conn != null) {
			try {
				String sql = "SELECT * FROM incomes WHERE incid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1, incId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					income = new Incomes();
					income.setAmount(rs.getDouble("amount"));
					income.setIncAc(rs.getString("incac"));
					income.setIncCatId(rs.getInt("inccatid"));
					income.setIncId(rs.getInt("incid"));
					income.setReceiveBy(rs.getString("receiveby"));
					income.setRemark(rs.getString("remark"));
					income.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					income.setUserId(rs.getInt("userid"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return income;
	}
	
	public ArrayList<Incomes> findAll() {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<Incomes> incomeList = new ArrayList<Incomes>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM incomes";
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(sql);
				while(rs.next()) {
					Incomes income = new Incomes();
					income.setAmount(rs.getDouble("amount"));
					income.setIncAc(rs.getString("incac"));
					income.setIncCatId(rs.getInt("inccatid"));
					income.setIncId(rs.getInt("incid"));
					income.setReceiveBy(rs.getString("receiveby"));
					income.setRemark(rs.getString("remark"));
					income.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					income.setUserId(rs.getInt("userid"));
					incomeList.add(income);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return incomeList;
	}

	public ArrayList<Incomes> findAll(int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<Incomes> incomeList = new ArrayList<Incomes>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM incomes WHERE userid = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,userId);
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Incomes income = new Incomes();
					income.setAmount(rs.getDouble("amount"));
					income.setIncAc(rs.getString("incac"));
					income.setIncCatId(rs.getInt("inccatid"));
					income.setIncId(rs.getInt("incid"));
					income.setReceiveBy(rs.getString("receiveby"));
					income.setRemark(rs.getString("remark"));
					income.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					income.setUserId(rs.getInt("userid"));
					incomeList.add(income);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return incomeList;
	}

	public ArrayList<Incomes> findAllDateWise(String sDate, String eDate, int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		ArrayList<Incomes> incomeList = new ArrayList<Incomes>();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM incomes WHERE userid = ? AND trandate BETWEEN ? AND ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setInt(1,userId);
				ps.setDate(2, DateUtils.convertUtilToSql(DateUtils.convertDate(sDate)));
				ps.setDate(3, DateUtils.convertUtilToSql(DateUtils.convertDate(eDate)));			
				ResultSet rs = ps.executeQuery();
				while(rs.next()) {
					Incomes income = new Incomes();
					income.setAmount(rs.getDouble("amount"));
					income.setIncAc(rs.getString("incac"));
					income.setIncCatId(rs.getInt("inccatid"));
					income.setIncId(rs.getInt("incid"));
					income.setReceiveBy(rs.getString("receiveby"));
					income.setRemark(rs.getString("remark"));
					income.setTranDate(DateUtils.convertSqlToUtil(rs.getDate("trandate")));
					income.setUserId(rs.getInt("userid"));
					incomeList.add(income);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return incomeList;
	}
}
