public void setInput(final int index, final JsonStream input) {
		if (index >= this.maxInputs)
			throw new IndexOutOfBoundsException();

		CollectionUtil.ensureSize(this.inputs, index + 1);
		this.inputs.set(index, input == null ? null : input.getSource());
	}