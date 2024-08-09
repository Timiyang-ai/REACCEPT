public Connection getConnection()
	throws SQLException {
		Connection connection = this.pool.getConnection();

		// set the Transaction Isolation if defined
		if (this.isolation !=null){
			connection.setTransactionIsolation( this.isolation.intValue() );
		}

		// toggle autoCommit to false if set
		if ( connection.getAutoCommit() != this.autocommit ){
			connection.setAutoCommit(this.autocommit);
		}
		return connection;


	}