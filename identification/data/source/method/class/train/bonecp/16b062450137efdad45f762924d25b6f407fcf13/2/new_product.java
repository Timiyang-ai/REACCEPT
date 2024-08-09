protected SQLException markPossiblyBroken(SQLException e) {
		String state = e.getSQLState();
		ConnectionState connectionState = this.getConnectionHook() != null ? this.getConnectionHook().onMarkPossiblyBroken(this, state, e) : ConnectionState.NOP; 
		if (state == null){ // safety;
			state = "08999"; 
		}

		if (((sqlStateDBFailureCodes.contains(state) || connectionState.equals(ConnectionState.TERMINATE_ALL_CONNECTIONS)) && this.pool != null) && this.pool.getDbIsDown().compareAndSet(false, true) ){
			logger.error("Database access problem. Killing off all remaining connections in the connection pool. SQL State = " + state);
			this.pool.connectionStrategy.terminateAllConnections();
			this.pool.poisonAndRepopulatePartitions();
		}

		// SQL-92 says:
		//		 Class values that begin with one of the <digit>s '5', '6', '7',
		//         '8', or '9' or one of the <simple Latin upper case letter>s 'I',
		//         'J', 'K', 'L', 'M', 'N', 'O', 'P', 'Q', 'R', 'S', 'T', 'U', 'V',
		//         'W', 'X', 'Y', or 'Z' are reserved for implementation-specified
		//         conditions.

		// FIXME: We should look into this.connection.getMetaData().getSQLStateType();
		// to determine if we have SQL:92 or X/OPEN sqlstatus codes.
		
		//		char firstChar = state.charAt(0);
		// if it's a communication exception, a mysql deadlock or an implementation-specific error code, flag this connection as being potentially broken.
		// state == 40001 is mysql specific triggered when a deadlock is detected
		char firstChar = state.charAt(0);
		if (connectionState.equals(ConnectionState.CONNECTION_POSSIBLY_BROKEN) || state.equals("40001") || 
				state.startsWith("08") ||  (firstChar >= '5' && firstChar <='9') /*|| (firstChar >='I' && firstChar <= 'Z')*/){
			this.possiblyBroken = true;
		}

		// Notify anyone who's interested
		if (this.possiblyBroken  && (this.getConnectionHook() != null)){
			this.possiblyBroken = this.getConnectionHook().onConnectionException(this, state, e);
		}

		return e;
	}