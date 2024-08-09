public Connection getConnection() throws SQLException {
		int partition = (int) (Thread.currentThread().getId() % this.partitionCount);

		ConnectionPartition connectionPartition = this.partitions[partition];
		if (!connectionPartition.isUnableToCreateMoreTransactions() ){
			try{
				// don't bother checking for connections on every single connections.
				this.connectionsObtainedLock.lock();
				if (this.connectionsObtained++  % 2  == 0){ 
					// it's possible for connectionsObtained variable to wrap around but this is not dangerous
	 				maybeSignalForMoreConnections(connectionPartition);
				}
			} finally {
				this.connectionsObtainedLock.unlock(); 
			}
		}

		ConnectionHandle result;
		if (this.connectionStarvationTriggered) {
			try{
				result = connectionPartition.getFreeConnections().take();
			}
			catch (InterruptedException e) {
				throw new SQLException(e);
			}
		} else { 
			result = connectionPartition.getFreeConnections().poll();
		}


		if (result == null) { 

			// we ran out of space on this partition, pick another free one
			for (int i=0; i < this.partitionCount ; i++){
				if (i == partition) {
					continue; // we already determined it's not here
				}
				result = this.partitions[i].getFreeConnections().poll();
				connectionPartition = this.partitions[i];
				if (result != null) {
					break;
				}
			}
		}

		// we still didn't find an empty one, wait forever until our partition is free
		if (result == null) {
			try {
				this.connectionStarvationTriggered   = true; 
				result = connectionPartition.getFreeConnections().take();
			}
			catch (InterruptedException e) {
				throw new SQLException(e);
			}
		}
		result.setOriginatingPartition(connectionPartition);
		result.renewConnection();
		return result;
	}