public Connection getConnection()
	throws SQLException {
		Connection connection = this.pool.getConnection();

		// set the Transaction Isolation if defined
		boolean success = false;
		try {
			// set the Transaction Isolation if defined
			if ((this.isolation != null) && (connection.getTransactionIsolation() != this.isolation.intValue())) {
				connection.setTransactionIsolation (this.isolation.intValue());
			}

			// toggle autoCommit to false if set
			if ( connection.getAutoCommit() != this.autocommit ){
				connection.setAutoCommit(this.autocommit);
			}

			success = true;
			return connection;
		} finally {
			if (!success) {
				try {
					connection.close();
				} catch (Exception e) {
					logger.warn("Failed to close a connection", e);
				}
			}
		}
	}