public void setInput(int index, JsonStream input) {
		this.inputs.set(index, input == null ? null : input.getSource());
	}