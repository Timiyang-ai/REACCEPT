protected void terminateAllConnections(){
		if (this.terminationLock.tryLock()){
			try{
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
			} finally {
				this.terminationLock.unlock();
			}
		}
	}