static byte[] toByteArray(final String data) {
		final String[] parts = data.split("\\|");
		final byte[] bytes = new byte[parts.length];

		for (int i = 0; i < parts.length; ++i) {
			if (parts[i].length() != 8) {
				throw new IllegalArgumentException(
					"Byte value doesn't contain 8 bit: " + parts[i]
				);
			} else {
				try {
					bytes[parts.length - 1 - i] = (byte)Integer.parseInt(parts[i], 2);
				} catch (NumberFormatException e) {
					throw new IllegalArgumentException(e);
				}
			}
		}

		return bytes;
	}