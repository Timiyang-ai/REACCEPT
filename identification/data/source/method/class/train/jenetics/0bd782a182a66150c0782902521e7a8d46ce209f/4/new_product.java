public String toCanonicalString() {
		return toSeq().stream()
			.map(g -> g.booleanValue() ? "1" : "0")
			.collect(joining());
	}