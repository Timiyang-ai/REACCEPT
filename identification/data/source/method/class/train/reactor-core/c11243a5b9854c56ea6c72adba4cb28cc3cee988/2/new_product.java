public static boolean isBubbling(@Nullable Throwable t) {
		return t instanceof BubblingException;
	}