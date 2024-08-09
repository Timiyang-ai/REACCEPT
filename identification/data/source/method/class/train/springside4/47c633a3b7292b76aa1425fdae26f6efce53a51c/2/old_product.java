public static Throwable unwrap(Throwable t) {
		if (t instanceof java.util.concurrent.ExecutionException
				|| t instanceof java.lang.reflect.InvocationTargetException
				|| t instanceof java.lang.reflect.UndeclaredThrowableException) {
			return t.getCause();
		}
		return t;
	}