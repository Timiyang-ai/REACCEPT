protected void putConnectionBackInPartition(ConnectionHandle connectionHandle) throws SQLException {
		BoundedLinkedTransferQueue<ConnectionHandle> queue = connectionHandle.getOriginatingPartition().getFreeConnections();

		if (!queue.tryTransfer(connectionHandle)){
			if (!queue.tryPut(connectionHandle)){
				connectionHandle.internalClose();
			}
		}


	}