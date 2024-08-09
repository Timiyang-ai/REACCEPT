protected boolean isSuperUser(Connection connection, Integer userId) throws Exception {
		// the 'System Developer' part of this string is left because the super user
		// role used to be named that.  This has to be in here so that admins can authenticate 
		String select = "select 1 from user_role where user_id = ? and (role = ? or role = 'System Developer')";
		PreparedStatement statement = connection.prepareStatement(select);
		statement.setInt(1, userId);
		statement.setString(2, OpenmrsConstants.SUPERUSER_ROLE);
		if (statement.execute()) {
			ResultSet results = statement.getResultSet();
			if (results.next()) {
				return results.getInt(1) == 1;
			}
		}
		
		return false;
	}