public BitSet toBitSet() {
		BitSet set = new BitSet(length());
		for (int i = 0, n = length(); i < n; ++i) {
			set.set(i, getGene(i).getBit());
		}
		return set;
	}