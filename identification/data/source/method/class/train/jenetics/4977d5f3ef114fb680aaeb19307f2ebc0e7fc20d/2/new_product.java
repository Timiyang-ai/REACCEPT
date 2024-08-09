public static byte[] flip(final byte[] data, final int index) {
		return get(data, index) ? unset(data, index) : set(data, index);
	}