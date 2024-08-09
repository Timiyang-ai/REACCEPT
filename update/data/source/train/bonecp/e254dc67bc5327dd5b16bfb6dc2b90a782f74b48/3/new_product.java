private void maybeSignalForMoreConnections(ConnectionPartition connectionPartition) {

		if (!this.poolShuttingDown && !connectionPartition.isUnableToCreateMoreTransactions() && 
				connectionPartition.getAvailableConnections().get()*100/connectionPartition.getMaxConnections() <= this.poolAvailabilityThreshold){
			try{
				connectionPartition.lockAlmostFullLock();
				connectionPartition.almostFullSignal();
			} finally {
				connectionPartition.unlockAlmostFullLock(); 
			}
		}


	}