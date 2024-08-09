public double argAt(final int index) {
		if (index < 0 || index >= arity()) {
			throw new ArrayIndexOutOfBoundsException();
		}

		return _sample[index];
	}