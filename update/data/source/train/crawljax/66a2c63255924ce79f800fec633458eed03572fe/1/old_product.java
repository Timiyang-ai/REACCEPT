public void rewind() {
		synchronized (stateLock) {
			this.currentState = this.initialState;
		}
	}