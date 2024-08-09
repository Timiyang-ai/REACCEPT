public static RuntimeException bubble(Throwable t) {
		throwIfFatal(t);
		return new BubblingException(t);
	}