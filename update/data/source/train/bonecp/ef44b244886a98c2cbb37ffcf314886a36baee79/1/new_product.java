protected void putConnectionBackInPartition(ConnectionHandle connectionHandle) throws SQLException {
		BoundedLinkedTransferQueue<ConnectionHandle> queue = connectionHandle.getOriginatingPartition().getFreeConnections();

		if (!queue.tryTransfer(connectionHandle)){
			if (!queue.offer(connectionHandle)){
				connectionHandle.internalClose();
			}
		}


	}