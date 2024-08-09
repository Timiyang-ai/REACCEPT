public static Runnable safeRunnable(@NotNull Runnable runnable) {
		return new SafeRunnable(runnable);
	}