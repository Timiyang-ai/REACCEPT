public Connection getConnection() throws SQLException {
		if (this.poolShuttingDown){
			throw new SQLException(this.shutdownStackTrace);
		}
		int partition = (int) (Thread.currentThread().getId() % this.partitionCount);
		ConnectionPartition connectionPartition = this.partitions[partition];

		ConnectionHandle result;

		result = connectionPartition.getFreeConnections().poll();

		if (result == null) { 
			// we ran out of space on this partition, pick another free one
			for (int i=0; i < this.partitionCount ; i++){
				if (i == partition) {
					continue; // we already determined it's not here
				}
				result = this.partitions[i].getFreeConnections().poll(); // try our luck with this partition
				connectionPartition = this.partitions[i];
				if (result != null) {
					break;  // we found a connection
				}
			}
		}

		if (!connectionPartition.isUnableToCreateMoreTransactions()){ // unless we can't create any more connections...   
			maybeSignalForMoreConnections(connectionPartition);  // see if we need to create more
		}

		// we still didn't find an empty one, wait forever (or as per config) until our partition is free
		if (result == null) {
			try {
				result = connectionPartition.getFreeConnections().poll(this.connectionTimeout, TimeUnit.MILLISECONDS);
			}
			catch (InterruptedException e) {
				throw new SQLException(e.getMessage());
			}
		}
		result.setOriginatingPartition(connectionPartition); // track the winning partition to keep partitions balanced
		result.renewConnection(); // mark it as being logically "open"

		// Give an application a chance to do something with it.
		if (result.getConnectionHook() != null){
			result.getConnectionHook().onCheckOut(result);
		}

		if (this.closeConnectionWatch){ // a debugging tool
			watchConnection(result);
		}

		connectionPartition.getAvailableConnections().decrementAndGet();
		return result;
	}