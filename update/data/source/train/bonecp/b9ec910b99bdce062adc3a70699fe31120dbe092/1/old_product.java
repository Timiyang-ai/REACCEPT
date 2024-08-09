public void terminateAllConnections(){
		this.terminationLock.lock();
		try{
			ConnectionHandle conn;
			// close off all connections.
			for (int i=0; i < this.pool.partitionCount; i++) {
				this.pool.partitions[i].setUnableToCreateMoreTransactions(false); // we can create new ones now, this is an optimization
				List<ConnectionHandle> clist = new LinkedList<ConnectionHandle>(); 
				this.pool.partitions[i].getFreeConnections().drainTo(clist);
				for (ConnectionHandle c: clist){
					this.pool.destroyConnection(c);
				}

			}
		} finally {
			this.terminationLock.unlock();
		}
	}