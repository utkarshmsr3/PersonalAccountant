package com.myproject.jdbc.daos;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;
import java.util.ArrayList;

import com.myproject.utilities.ConnectionPool;

public class QuoteDao {
	public String getRandomQuote() {
		String quote ="";
		ConnectionPool pool = ConnectionPool.getInstance();
		Connection conn = pool.getConnection();
		if (conn != null) {
			try {
				String cmd = "SELECT quote from quote";
				Statement ps = conn.createStatement();
				ResultSet rs = ps.executeQuery(cmd);
				ArrayList<String> quoteArr = new ArrayList<String>();
				while(rs.next()) {
					quoteArr.add(rs.getString("quote"));
				}
				int randIdx = (1000*(int)Math.random())%quoteArr.size();
				quote =  quoteArr.get(randIdx);
			} catch (Exception e) {
				e.printStackTrace();
			} finally {
				pool.putConnection(conn);
			}
		}
		return quote;
	}
}
