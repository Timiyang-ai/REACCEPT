public void setInputs(final List<? extends JsonStream> inputs) {
		if (inputs == null)
			throw new NullPointerException("inputs must not be null");

		this.inputs.clear();
		for (final JsonStream input : inputs)
			this.inputs.add(input == null ? null : input.getSource());
	}