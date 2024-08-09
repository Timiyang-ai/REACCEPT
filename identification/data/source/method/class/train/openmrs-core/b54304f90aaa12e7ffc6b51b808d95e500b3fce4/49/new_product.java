public static void rethrowIfCause(Throwable thrown, Class<? extends RuntimeException> causeType) {
		int index = ExceptionUtils.indexOfType(thrown, causeType);
		if (index >= 0) {
			throw (RuntimeException) ExceptionUtils.getThrowables(thrown)[index];
		}
	}