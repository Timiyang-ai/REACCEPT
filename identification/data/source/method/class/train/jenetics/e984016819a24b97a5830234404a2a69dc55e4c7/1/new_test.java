	private static byte[] newByteArray(final int length, final Random random) {
		final byte[] array = new byte[length];
		for (int i = 0; i < length; ++i) {
			array[i] = (byte)random.nextInt();
		}
		return array;
	}