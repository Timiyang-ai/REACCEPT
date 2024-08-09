protected void terminateAllConnections(){
		// close off all connections.
		for (int i=0; i < this.partitionCount; i++) {
			ConnectionHandle conn;
			while ((conn = this.partitions[i].getFreeConnections().poll()) != null){
				postDestroyConnection(conn);

				try {
					conn.internalClose();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
		}
	}