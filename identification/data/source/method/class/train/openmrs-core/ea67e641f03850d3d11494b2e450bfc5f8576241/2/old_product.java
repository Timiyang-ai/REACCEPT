protected boolean isSuperUser(Connection connection, Integer userId) throws Exception {
		String select = "select 1 from user_role where user_id = ? and role = ?";
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