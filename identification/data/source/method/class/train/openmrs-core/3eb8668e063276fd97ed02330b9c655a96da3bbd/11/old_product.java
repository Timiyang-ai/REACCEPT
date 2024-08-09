protected boolean authenticateAsSuperUser(String usernameOrSystemId, String password) throws ServletException {
		Connection connection = null;
		try {
			connection = DatabaseUpdater.getConnection();
			
			String select = "select user_id, password, salt from users where (username = ? or system_id = ?) and retired = 0";
			PreparedStatement statement = connection.prepareStatement(select);
			statement.setString(1, usernameOrSystemId);
			statement.setString(2, usernameOrSystemId);
			
			if (statement.execute()) {
				ResultSet results = statement.getResultSet();
				if (results.next()) {
					Integer userId = results.getInt(1);
					String storedPassword = results.getString(2);
					String salt = results.getString(3);
					String passwordToHash = password + salt;
					return Security.hashMatches(storedPassword, passwordToHash) && isSuperUser(connection, userId);
				}
			}
		}
		catch (Throwable t) {
			log
			        .error(
			            "Error while trying to authenticate as super user. Ignore this if you are upgrading from OpenMRS 1.5 to 1.6",
			            t);
			
			// we may not have upgraded User to have retired instead of voided yet, so if the query above fails, we try
			// again the old way
			try {
				String select = "select user_id, password, salt from users where (username = ? or system_id = ?) and voided = 0";
				PreparedStatement statement = connection.prepareStatement(select);
				statement.setString(1, usernameOrSystemId);
				statement.setString(2, usernameOrSystemId);
				
				if (statement.execute()) {
					ResultSet results = statement.getResultSet();
					if (results.next()) {
						Integer userId = results.getInt(1);
						String storedPassword = results.getString(2);
						String salt = results.getString(3);
						String passwordToHash = password + salt;
						return Security.hashMatches(storedPassword, passwordToHash) && isSuperUser(connection, userId);
					}
				}
			}
			catch (Throwable t2) {
				log.error("Error while trying to authenticate as super user (voided version)", t);
			}
		}
		finally {
			if (connection != null)
				try {
					connection.close();
				}
				catch (SQLException e) {
					log.debug("Error while closing the database", e);
				}
		}
		
		return false;
	}