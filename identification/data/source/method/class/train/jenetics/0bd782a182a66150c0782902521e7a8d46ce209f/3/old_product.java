public String toCanonicalString() {
		StringBuilder out = new StringBuilder(length());
		for (int i = 0; i < _length; ++i) {
			out.append(BitUtils.getBit(_genes, i) ? '1' : '0');
		}
		return out.toString();
	}