public static Throwable unwrap(Throwable t) {
		if (t instanceof java.util.concurrent.ExecutionException
				|| t instanceof java.lang.reflect.InvocationTargetException || t instanceof UncheckedException) {
			return t.getCause();
		}

		return t;
	}