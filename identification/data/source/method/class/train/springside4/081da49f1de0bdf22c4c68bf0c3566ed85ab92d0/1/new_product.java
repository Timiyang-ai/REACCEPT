public static int crc32(String input) {
		return crc32(input.getBytes(Charsets.UTF_8));
	}