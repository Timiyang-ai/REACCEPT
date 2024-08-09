public double argAt(final int index) {
		if (index < 0 || index >= arity() - 1) {
			throw new ArrayIndexOutOfBoundsException(format(
				"Argument index out or range [0, %s): %s", arity(), index
			));
		}

		return _sample[index];
	}