public String toCanonicalString() {
		final StringBuilder out = new StringBuilder(length());
		for (int i = 0; i < _length; ++i) {
			out.append(bit.get(_genes, i) ? '1' : '0');
		}
		return out.toString();
	}