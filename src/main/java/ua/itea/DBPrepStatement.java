package ua.itea;

import java.math.BigInteger;
import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import models.User;

//DZ
// * Unique email
// * Auth from db
// * Registration to db
// * Users from db as table
public class DBPrepStatement {

	private final static String SELECT_QUERY = "SELECT * FROM users WHERE login = ?";
	private final static String INSERT_QUERY = "INSERT INTO users(login, password, name, region, gender, comment)"
			+ " VALUES(?, ?, ?, ?, ?, ?)";
	private final static String USER_UPDATE_QUERY = "UPDATE  users set  password= ?, name = ?, region = ?, gender = ? , comment = ?"
			+" WHERE  login = ?";
	private final static String SALT = "miniMax";
	private boolean conectionSuccsess = false;
	
	private Connection conn;

	public void openDatabase() {
		try {
			Class.forName("com.mysql.jdbc.Driver").newInstance();
			conn = DriverManager.getConnection("jdbc:mysql://localhost/iteashop?" + "user=root&password=");
			conectionSuccsess = true;
		} catch (InstantiationException | IllegalAccessException | ClassNotFoundException ex) {
			System.out.println(ex.getMessage());
		} catch (SQLException ex) {
			System.out.println("Failed");
			System.out.println("SQLException: " + ex.getMessage());
			System.out.println("SQLState: " + ex.getSQLState());
			System.out.println("VendorError: " + ex.getErrorCode());
		}
	}

	public boolean isConectionSuccsess() {
		return conectionSuccsess;
	}

	public void setConectionSuccsess(boolean conectionSuccsess) {
		this.conectionSuccsess = conectionSuccsess;
	}

	public boolean selectEmail(String formEmail) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = conn.prepareStatement(SELECT_QUERY);
			prepSt.setString(1, formEmail);
			rs = prepSt.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
				if (prepSt != null) {
					try {
						prepSt.close();
					} catch (SQLException sqlEx) {
					}
					prepSt = null;
				}
			}
		}
		return false;
	}

	public List<User> readUsers() {
		Statement selectStmt = null;
		ResultSet rs = null;
		User user = null;
		List<User> users = new ArrayList<>();
		try {
			selectStmt = conn.createStatement();
			rs = selectStmt.executeQuery("SELECT * FROM users ");
			while (rs.next()) {

				user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(rs.getString("login"));
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRegion(rs.getString("region"));
				user.setGender(rs.getBoolean("gender"));
				user.setComment(rs.getString("comment"));
				users.add(user);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (selectStmt != null) {
				try {
					selectStmt.close();
				} catch (SQLException sqlEx) {
				}
				selectStmt = null;
			}
		}
		return users;
	}

	public void insertUser(User user) {
		PreparedStatement prepSt = null;
		try {
			prepSt = conn.prepareStatement(INSERT_QUERY);
			prepSt.setString(1, user.getLogin());
			prepSt.setString(2, hash(user.getPassword()));
			prepSt.setString(3, user.getName());
			prepSt.setString(4, user.getRegion());
			prepSt.setBoolean(5, user.getGender());
			prepSt.setString(6, user.getComment());
			prepSt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepSt != null) {
				try {
					prepSt.close();
				} catch (SQLException sqlEx) {
				}
				prepSt = null;
			}
		}
	}

	public void updateUser(User user, String login) {
		PreparedStatement prepSt = null;
		try {
			prepSt = conn.prepareStatement(USER_UPDATE_QUERY);
			prepSt.setString(1, hash(user.getPassword()));
			prepSt.setString(2, user.getName());
			prepSt.setString(3, user.getRegion());
			prepSt.setBoolean(4, user.getGender());
			prepSt.setString(5, user.getComment());
			prepSt.setString(6,login);
			prepSt.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepSt != null) {
				try {
					prepSt.close();
				} catch (SQLException sqlEx) {
				}
				prepSt = null;
			}
		}
	}
	public String hash (String password) {
		try {
			MessageDigest md5 = MessageDigest.getInstance("MD5");
			md5.update(StandardCharsets.UTF_8.encode(password + SALT));
			//System.out.println(String.format("%032x",new BigInteger(md5.digest())));
			return String.format("%032x",new BigInteger(md5.digest()));
		} catch (NoSuchAlgorithmException e) {
			e.printStackTrace();
		} 
		return null;
	}
	
	public User readUserFromLogin( String login) {
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		User user = null;
		try {
			prepSt = conn.prepareStatement(SELECT_QUERY);
			prepSt.setString(1, login);
			rs = prepSt.executeQuery();
	
			while (rs.next()) {
				user = new User();
				user.setId(rs.getInt("id"));
				user.setLogin(login);
				rs.getString("login");
				user.setPassword(rs.getString("password"));
				user.setName(rs.getString("name"));
				user.setRegion(rs.getString("region"));
				user.setGender(rs.getBoolean("gender"));
				user.setComment(rs.getString("comment"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (prepSt != null) {
				try {
					prepSt.close();
				} catch (SQLException sqlEx) {
				}
				prepSt = null;
			}
		}
		return user;
	}
	public boolean checkLoginPasswords(String login, String password) {
		String pass_Query = "SELECT * FROM users WHERE login = ? AND  password = ?";
		ResultSet rs = null;
		PreparedStatement prepSt = null;
		try {
			prepSt = conn.prepareStatement(pass_Query);
			prepSt.setString(1, login);
			prepSt.setString(2, hash(password));
			rs = prepSt.executeQuery();
			while (rs.next()) {
				return true;
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			if (rs != null) {
				try {
					rs.close();
				} catch (SQLException sqlEx) {
				}
				rs = null;
				if (prepSt != null) {
					try {
						prepSt.close();
					} catch (SQLException sqlEx) {
					}
					prepSt = null;
				}
			}
		}
		return false;

	}

	public void closeConection() {
		if (conn != null) {
			try {
				conn.close();
			} catch (SQLException sqlEx) {
			}
			conn = null;
		}
	}

}
