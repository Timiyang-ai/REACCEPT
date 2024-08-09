public static byte[] invert(final byte[] data)	{
		for (int i = data.length; --i >= 0;) {
			data[i] = (byte)~data[i];
		}
		return data;
	}