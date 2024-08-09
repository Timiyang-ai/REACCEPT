public boolean isConnectionHandleAlive(ConnectionHandle connection) {
		Statement stmt = null;
		boolean result = false;
		boolean logicallyClosed = connection.logicallyClosed.get();
		try {
			connection.logicallyClosed.compareAndSet(true, false); // avoid checks later on if it's marked as closed.
			String testStatement = this.config.getConnectionTestStatement();
			ResultSet rs = null;

			if (testStatement == null) {
				// Make a call to fetch the metadata instead of a dummy query.
				rs = connection.getMetaData().getTables( null, null, KEEPALIVEMETADATA, METADATATABLE );
			} else {
				stmt = connection.createStatement();
				stmt.execute(testStatement);
			}


			if (rs != null) {
				rs.close();
			}

			result = true;
		} catch (SQLException e) {
			// connection must be broken!
			result = false;
		} finally {
			connection.logicallyClosed.set(logicallyClosed);
			connection.setConnectionLastResetInMs(System.currentTimeMillis());
			result = closeStatement(stmt, result);
		}
		return result;
	}