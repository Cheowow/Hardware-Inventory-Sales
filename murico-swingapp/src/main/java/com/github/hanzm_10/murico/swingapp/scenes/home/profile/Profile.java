package com.github.hanzm_10.murico.swingapp.scenes.home.profile;

import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.ResultSet;
import java.sql.SQLException;

import com.github.hanzm_10.murico.swingapp.lib.database.AbstractSqlQueryLoader.SqlQueryType;
import com.github.hanzm_10.murico.swingapp.lib.database.mysql.MySqlFactoryDao;
import com.github.hanzm_10.murico.swingapp.lib.database.mysql.MySqlQueryLoader;

public class Profile {

	public String getDisplayImageByDisplayname(String displayName) {
		String storedImage = null;
		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("get_user_by_display_name", "users", SqlQueryType.SELECT);
			var ps = conn.prepareStatement(query);

			ps.setString(1, displayName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				storedImage = rs.getString("display_image");
				return storedImage;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding profile picture for display name " + displayName, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return storedImage;
	}

	public String getFirstname(String userdisplayName) {
		String firstnameString = null;
		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("get_user_by_display_name", "users", SqlQueryType.SELECT);

			var statement = conn.prepareStatement(query);
			statement.setString(1, userdisplayName);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				firstnameString = rs.getString("first_name");

				return firstnameString;
			} else {

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding user_id for display name " + userdisplayName, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstnameString;

	}

	public String getGender(String userdisplayName) {
		String firstnameString = null;
		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("get_user_by_display_name", "users", SqlQueryType.SELECT);

			var statement = conn.prepareStatement(query);
			statement.setString(1, userdisplayName);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				firstnameString = rs.getString("gender");

				return firstnameString;
			} else {

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding user_id for display name " + userdisplayName, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstnameString;

	}

	public String getLastname(String userdisplayName) {
		String firstnameString = null;
		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("get_user_by_display_name", "users", SqlQueryType.SELECT);

			var statement = conn.prepareStatement(query);
			statement.setString(1, userdisplayName);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				firstnameString = rs.getString("last_name");

				return firstnameString;
			} else {

			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding user_id for display name " + userdisplayName, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return firstnameString;

	}

	public String getRoleByUsername(String displayName) {
		String storedRole = null;
		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("get_user_roles_by_username", "users_roles",
					SqlQueryType.SELECT);
			var ps = conn.prepareStatement(query);

			ps.setString(1, displayName);

			ResultSet rs = ps.executeQuery();
			if (rs.next()) {
				storedRole = rs.getString("name");
				return storedRole;

			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding _user_id for display name " + displayName, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return storedRole;
	}

	public Integer getUserIdByDisplayName(String displayName) {

		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("get_user_by_display_name", "users", SqlQueryType.SELECT);

			var statement = conn.prepareStatement(query);
			statement.setString(1, displayName);

			ResultSet rs = statement.executeQuery();
			if (rs.next()) {
				return rs.getInt("_user_id");
			} else {
				return null;
			}

		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding user_id for display name " + displayName, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}

	public boolean isAdmin(String username) {
		boolean isAdmin = false;

		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("get_user_roles_by_username", "users_roles",
					SqlQueryType.SELECT);
			var ps = conn.prepareStatement(query);

			ps.setString(1, username);

			try (ResultSet rs = ps.executeQuery()) {
				if (rs.next()) {
					String storedRole = rs.getString("name");
					if (storedRole.equalsIgnoreCase("admin")) {
						return isAdmin = true;
					} else {
						System.out.println(username + " is not admin");
						return isAdmin = false;
					}
				}
			}
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error finding _user_id for display name " + username, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return isAdmin;

	}

	public void profile(int userId, String userFirstName, String userLastName, String gender) {
		try {
			var conn = MySqlFactoryDao.createConnection();
			var query = MySqlQueryLoader.getInstance().get("update_profile", "users", SqlQueryType.UPDATE);
			var pstmtQuery = conn.prepareStatement(query);
			pstmtQuery.setString(1, userFirstName);
			pstmtQuery.setString(2, userLastName);

			pstmtQuery.setString(3, gender);

			pstmtQuery.setInt(4, userId);
			int rows = pstmtQuery.executeUpdate();
			System.out.println(rows + "updated");
		} catch (SQLException e) {
			e.printStackTrace();
			throw new RuntimeException("Error updating user with ID " + userId, e);
		} catch (FileNotFoundException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		} catch (IOException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}
}
