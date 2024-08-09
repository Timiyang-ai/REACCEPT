public synchronized void start(String prefix) {

	// You have to ooccupie a Server to start it
	if (!this.isFree()) {
	    if (p != null) {
		p.destroy();
	    }
	    restart(prefix);
	} else {
	    throw new IllegalStateException("Cant start a not marked Server. Occupie it first!");
	}
    }