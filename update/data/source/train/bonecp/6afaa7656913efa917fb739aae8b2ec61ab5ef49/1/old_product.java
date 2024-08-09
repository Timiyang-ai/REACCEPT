private void maybeSignalForMoreConnections(ConnectionPartition connectionPartition) {

		if (!this.poolShuttingDown && !connectionPartition.isUnableToCreateMoreTransactions() && connectionPartition.getFreeConnections().size()*100/connectionPartition.getMaxConnections() < this.poolAvailabilityThreshold){
			this.poolWatchThreadSignalQueue.offer(new Object()); // item being pushed is not important.
		}
	}