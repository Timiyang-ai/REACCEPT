public static RuntimeException unchecked(Throwable t) {
		if (t instanceof RuntimeException) {
			throw (RuntimeException) t;
		}
		if (t instanceof Error) {
			throw (Error) t;
		}
		throw new UndeclaredThrowableException(t);
	}