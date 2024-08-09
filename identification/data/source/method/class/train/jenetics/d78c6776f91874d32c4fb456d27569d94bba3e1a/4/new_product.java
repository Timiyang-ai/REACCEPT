@Override
	public boolean isValid() {
		if (_valid == null) {
			final byte[] check = bit.newArray(_validAlleles.length());
			_valid = _genes.forAll(g -> !getAndSet(check, g.getAlleleIndex()));
		}

		return _valid;
	}