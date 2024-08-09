private void notifyCreditAvailable() {
		checkState(partitionRequestClient != null, "Tried to send task event to producer before requesting a queue.");

		partitionRequestClient.notifyCreditAvailable(this);
	}