private void maybeSignalForMoreConnections(ConnectionPartition connectionPartition) {

		if (!this.poolShuttingDown && !connectionPartition.isUnableToCreateMoreTransactions() && 
				connectionPartition.getAvailableConnections().get()*100/connectionPartition.getMaxConnections() <= this.poolAvailabilityThreshold){
			connectionPartition.getPoolWatchThreadSignalQueue().offer(new Object()); // item being pushed is not important.
		}
	}