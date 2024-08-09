boolean matches(final BiPredicate<V, String> equals) {
		return _pattern.matches(_tree, equals);
	}