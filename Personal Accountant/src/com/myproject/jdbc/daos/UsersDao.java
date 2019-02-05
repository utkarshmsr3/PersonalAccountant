package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

import com.myproject.jdbc.pojos.Users;
import com.myproject.utilities.ConnectionPool;

public class UsersDao {
	public void create(Users user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "INSERT INTO users (username,password,name,address,mobile,email) VALUES(?,?,?,?,?,?)";
				PreparedStatement ps = conn.prepareStatement(cmd);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getAddress());
				ps.setString(5, user.getMobile());
				ps.setString(6, user.getEmail());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}
	
	public void edit(Users user) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "UPDATE users SET username = ?,password = ?,name = ?,address = ?,mobile = ?,email = ? WHERE userid = ?";
				PreparedStatement ps = conn.prepareStatement(cmd);
				ps.setString(1, user.getUsername());
				ps.setString(2, user.getPassword());
				ps.setString(3, user.getName());
				ps.setString(4, user.getAddress());
				ps.setString(5, user.getMobile());
				ps.setString(6, user.getEmail());
				ps.setInt(7, user.getUserId());
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}
	
	public void updateLastLogIn(int userId, java.util.Date date) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "UPDATE users SET lastlogin = ? where userid=?";
				PreparedStatement ps = conn.prepareStatement(cmd);
				SimpleDateFormat sdf = new SimpleDateFormat("EEEEE dd MMMMM yyyy HH:mm:ss");
				String strDate = sdf.format(date);
				ps.setString(1, strDate);
				ps.setInt(2, userId);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}	
	}
	
	public String getLastLogIn(int userId) {
		String date = "";
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "SELECT lastlogin from users where userid = ?";
				PreparedStatement st = conn.prepareStatement(cmd);
				st.setInt(1, userId);
				ResultSet rs = st.executeQuery();
				if(rs.next()) {
					date = rs.getString("lastlogin");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return date;	
	}
	
	public void remove(int userId) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "DELETE FROM users WHERE userid = ?";
				PreparedStatement ps = conn.prepareStatement(cmd);
				ps.setInt(1, userId);
				ps.executeUpdate();
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
	}
	
	public Users find(int userId) {
		Users user = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "SELECT * FROM users WHERE userid = ?";
				PreparedStatement ps = conn.prepareStatement(cmd);
				ps.setInt(1, userId);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					user = new Users();
					user.setAddress(rs.getString("address"));
					user.setEmail(rs.getString("email"));
					user.setMobile(rs.getString("mobile"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}				
		return user;
	}
	public int giveUserId(String username, String password) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "SELECT * FROM users WHERE username = ? AND password = ?";
				PreparedStatement ps = conn.prepareStatement(cmd);
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					return rs.getInt("userid");
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return -1;
	}
	
	public Users findByUsername(String username) {
		Users user = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "SELECT * FROM users WHERE username = ?";
				PreparedStatement ps = conn.prepareStatement(cmd);
				ps.setString(1, username);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					user = new Users();
					user.setAddress(rs.getString("address"));
					user.setEmail(rs.getString("email"));
					user.setMobile(rs.getString("mobile"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}				
		return user;
	}
	
	public ArrayList<Users> findAll(){
		ArrayList<Users> userList = new ArrayList<Users>();
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "SELECT * FROM users";
				Statement s = conn.createStatement();				
				ResultSet rs = s.executeQuery(cmd);
				while(rs.next()) {
					Users user = new Users();
					user.setAddress(rs.getString("address"));
					user.setEmail(rs.getString("email"));
					user.setMobile(rs.getString("mobile"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
					userList.add(user);
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return userList;
	}
	
	public boolean checkAvailability(String username) {
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String sql = "SELECT * FROM users WHERE username = ?";
				PreparedStatement ps = conn.prepareStatement(sql);
				ps.setString(1,username);
				ResultSet rs = ps.executeQuery();
				if(rs.next())
					return true;
				
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return false;
	}
	
	public Users authenticate(String username, String password) {
		Users user = null;
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "SELECT * FROM users WHERE username = ? AND password = ?";
				PreparedStatement ps = conn.prepareStatement(cmd);
				ps.setString(1, username);
				ps.setString(2, password);
				ResultSet rs = ps.executeQuery();
				if(rs.next()) {
					user = new Users();
					user.setAddress(rs.getString("address"));
					user.setEmail(rs.getString("email"));
					user.setMobile(rs.getString("mobile"));
					user.setName(rs.getString("name"));
					user.setPassword(rs.getString("password"));
					user.setUserId(rs.getInt("userid"));
					user.setUsername(rs.getString("username"));
				}
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return user;
	}
	
	public static void main(String args[]) {
		System.out.println(new UsersDao().authenticate("ut", "ut"));
	}
}
