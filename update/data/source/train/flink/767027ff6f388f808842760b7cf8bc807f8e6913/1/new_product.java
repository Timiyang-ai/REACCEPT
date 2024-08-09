public static boolean isEvent(Buffer buffer, Class<?> eventClass) throws IOException {
		return !buffer.isBuffer() && isEvent(buffer.getNioBufferReadable(), eventClass);
	}