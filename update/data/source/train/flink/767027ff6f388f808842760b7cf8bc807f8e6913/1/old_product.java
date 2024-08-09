public static boolean isEvent(final Buffer buffer,
		final Class<?> eventClass,
		final ClassLoader classLoader) throws IOException {
		return !buffer.isBuffer() &&
			isEvent(buffer.getNioBufferReadable(), eventClass, classLoader);
	}