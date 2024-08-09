public boolean canBeSubsumed() {
		// If the checkpoint is forced, it cannot be subsumed.
		return !props.forceCheckpoint();
	}