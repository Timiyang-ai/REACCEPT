public void shouldComplyEqualsContract(T first, T second, T... more) {
		BitSet blackBitSet = new BitSet();
		blackBitSet.set(1);
		EqualsVerifier<T> equalVerifier = EqualsVerifier
			.forExamples(first, second, more)
			.suppress(Warning.NULL_FIELDS)
			.suppress(Warning.NONFINAL_FIELDS)
			.withPrefabValues(BitSet.class, new BitSet(), blackBitSet);

		this.initVerifier(equalVerifier);
		equalVerifier.usingGetClass().verify();
	}