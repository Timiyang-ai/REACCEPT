protected void putConnectionBackInPartition(ConnectionHandle connectionHandle) throws SQLException {

		if (this.cachedPoolStrategy && connectionHandle.inUseInThreadLocalContext.get()){
			// this might fail if we have a thread that takes up more than one thread
			// (we only track one)
			connectionHandle.inUseInThreadLocalContext.set(false);
			((CachedConnectionStrategy)this.connectionStrategy).tlConnections.set(connectionHandle);
		} else {
			BlockingQueue<ConnectionHandle> queue = connectionHandle.getOriginatingPartition().getFreeConnections();
				if (!queue.offer(connectionHandle)){ // this shouldn't fail
					connectionHandle.internalClose();
				}
		}


	}