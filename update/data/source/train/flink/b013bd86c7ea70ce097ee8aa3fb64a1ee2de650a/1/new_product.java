public void setInput(final int index, final JsonStream input) {
		this.inputs.set(index, input == null ? null : input.getSource());
	}