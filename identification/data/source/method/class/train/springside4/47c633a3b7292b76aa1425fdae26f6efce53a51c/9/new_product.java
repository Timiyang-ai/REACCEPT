public static Throwable unwrap(@Nullable Throwable t) {
		if (t instanceof UncheckedException || t instanceof java.util.concurrent.ExecutionException
				|| t instanceof java.lang.reflect.InvocationTargetException
				|| t instanceof UndeclaredThrowableException) {
			return t.getCause();
		}

		return t;
	}