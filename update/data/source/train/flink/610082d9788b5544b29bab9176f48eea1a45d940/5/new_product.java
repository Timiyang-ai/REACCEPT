@Override
	public PactRecord repeatLast() {
		this.copy.copyTo(this.repeater);
		return this.repeater;
	}