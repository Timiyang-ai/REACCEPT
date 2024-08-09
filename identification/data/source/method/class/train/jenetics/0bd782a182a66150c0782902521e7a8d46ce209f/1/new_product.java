public String toCanonicalString() {
		return stream()
			.map(g -> g.booleanValue() ? "1" : "0")
			.collect(joining());
	}