@Override
	public boolean isValid() {
		if (_valid == null) {
			final byte[] check = bit.newArray(length());
			_valid = _genes.forAll(g -> !getAndSet(check, g.getAlleleIndex()));
		}

		return _valid;
	}