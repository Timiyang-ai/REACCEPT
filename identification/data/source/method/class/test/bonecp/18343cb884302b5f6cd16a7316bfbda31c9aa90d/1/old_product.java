protected void terminateAllConnections(){
		// close off all connections.
		for (int i=0; i < this.partitionCount; i++) {
			ConnectionHandle conn;
			while ((conn = this.partitions[i].getFreeConnections().poll()) != null){
				this.partitions[i].updateCreatedConnections(-1);
				try {
					conn.internalClose();
				} catch (SQLException e) {
					logger.error(e);
				}
			}
			this.partitions[i].setUnableToCreateMoreTransactions(false); // we can create new ones now
		}
	}