public Stream<TreeMatchResult<V>> results() {
		return results(TreePattern::equals);
	}