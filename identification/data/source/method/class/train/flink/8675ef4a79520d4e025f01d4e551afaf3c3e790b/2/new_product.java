public void setInputs(final List<? extends JsonStream> inputs) {
		if (inputs == null)
			throw new NullPointerException("inputs must not be null");
		if (this.minInputs > inputs.size() || inputs.size() > this.maxInputs)
			throw new IndexOutOfBoundsException();

		this.inputs.clear();
		for (final JsonStream input : inputs)
			this.inputs.add(input == null ? null : input.getSource());
	}