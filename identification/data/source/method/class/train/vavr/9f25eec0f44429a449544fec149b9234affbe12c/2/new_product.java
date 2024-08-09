public static <T> T get(Throwing.Supplier<T> supplier) {
		try {
			return supplier.get();
		} catch (Throwable t) {
			throw asRuntime(t);
		}
	}