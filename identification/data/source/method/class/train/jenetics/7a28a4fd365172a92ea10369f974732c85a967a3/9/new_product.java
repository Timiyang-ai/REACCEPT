public boolean matches(final BiPredicate<? super V, ? super String> equals) {
		return _pattern.matches(_tree, equals);
	}