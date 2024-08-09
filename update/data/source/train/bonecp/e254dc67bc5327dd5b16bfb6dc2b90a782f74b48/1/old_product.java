public boolean isConnectionHandleAlive(Connection connection) {
		Statement stmt = null;
		boolean result = false; 
		try {
			String testStatement = this.config.getConnectionTestStatement();
			ResultSet rs = null;

			if (testStatement == null) {
				// Make a call to fetch the metadata instead of a dummy query.
				rs = connection.getMetaData().getTables( null, null, KEEPALIVEMETADATA, METADATATABLE );
			} else {
				stmt = connection.createStatement();
				rs = stmt.executeQuery(testStatement);
			}


			if (rs != null) { 
				rs.close();
			}

			result = true;
		} catch (SQLException e) {
			// connection must be broken!
			result = false;
		} finally {
			result = closeStatement(stmt, result);
		}
		return result;
	}