protected boolean isSuperUser(Connection connection, Integer userId) throws Exception {
        // the 'Administrator' part of this string is necessary because if the database was upgraded 
	 	// by OpenMRS 1.6 alpha then System Developer was renamed to that. This has to be here so we 
	 	// can roll back that change in 1.6 beta+ 
		String select = "select 1 from user_role join role on (user_role.role_id=role.role_id) where user_id = ? and (role.role = ? or role.role = 'Administrator')"; 
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