package com.myproject.utilities;

import java.io.IOException;
import java.io.InputStream;
import java.sql.Connection;
import java.sql.DriverManager;
import java.util.ArrayList;
import java.util.Properties;

public class ConnectionPool {
	private ArrayList<Connection> connectionList = null;
	static ConnectionPool instance = new ConnectionPool();
	private int maxconnections = 0;

	private ConnectionPool() {

	}

	private void initialize() {
		connectionList = new ArrayList<Connection>();
		Properties p = new Properties();
		InputStream is = getClass().getResourceAsStream("Connection.properties");
		try {
			p.load(is);
			String driver = p.getProperty("driver");
			String url = p.getProperty("url");
			String username = p.getProperty("username");
			String password = p.getProperty("password");
			maxconnections = Integer.parseInt(p.getProperty("maxconnections"));
			int k = maxconnections;
			while (k-- != 0) {
				Class.forName(driver).newInstance();
				Connection conn = DriverManager.getConnection(url, username, password);
				connectionList.add(conn);
			}
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

	public synchronized Connection getConnection() {
		Connection conn = null;
		try {
			while (true) {
				if (connectionList == null)
					initialize();
				else if (connectionList.size() > 0) {
					conn = connectionList.get(0);
					connectionList.remove(0);
					break;
				} else
					wait();
			}
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return conn;
	}

	public static ConnectionPool getInstance() {
		return instance;
	}

	public synchronized void putConnection(Connection conn) {
		if (connectionList.size() < maxconnections) {
			connectionList.add(conn);
			notifyAll();
		}
	}

}
