Stream<Tree<V, ?>> results() {
		return results(TreePattern::equals);
	}