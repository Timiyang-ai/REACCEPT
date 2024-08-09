protected void putConnectionBackInPartition(ConnectionHandle connectionHandle) throws SQLException {

		if (this.cachedPoolStrategy && connectionHandle.inUseInThreadLocalContext.get()){
			// this might fail if we have a thread that takes up more than one thread
			// (we only track one)
			connectionHandle.inUseInThreadLocalContext.set(false);
			((CachedConnectionStrategy)this.connectionStrategy).tlConnections.set(connectionHandle);
		} else {
			TransferQueue<ConnectionHandle> queue = connectionHandle.getOriginatingPartition().getFreeConnections();
			if (!queue.tryTransfer(connectionHandle)){
				if (!queue.offer(connectionHandle)){
					connectionHandle.internalClose();
				}
			}
		}


	}