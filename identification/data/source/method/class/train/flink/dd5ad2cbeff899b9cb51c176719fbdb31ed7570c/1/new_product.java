private void notifyCreditAvailable() {
		checkState(partitionRequestClient != null, "Tried to send task event to producer before requesting a queue.");

		// We should skip the notification if this channel is already released.
		if (!isReleased.get()) {
			partitionRequestClient.notifyCreditAvailable(this);
		}
	}