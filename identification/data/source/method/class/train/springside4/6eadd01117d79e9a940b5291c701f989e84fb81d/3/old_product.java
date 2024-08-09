public static RuntimeException unchecked(Throwable t) {

		Throwable unwrapped = unwrap(t);
		if (unwrapped instanceof RuntimeException) {
			throw (RuntimeException) unwrapped;
		}
		if (unwrapped instanceof Error) {
			throw (Error) unwrapped;
		}
		throw new UndeclaredThrowableException(unwrapped);
	}