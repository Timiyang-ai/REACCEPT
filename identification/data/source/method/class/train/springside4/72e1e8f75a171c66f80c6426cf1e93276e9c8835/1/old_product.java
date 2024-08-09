public static Runnable wrapException(@NotNull Runnable runnable) {
		return new WrapExceptionRunnable(runnable);
	}