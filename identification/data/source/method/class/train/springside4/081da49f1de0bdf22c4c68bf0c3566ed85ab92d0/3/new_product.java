public static int crc32AsInt(String input) {
		return crc32AsInt(input.getBytes(Charsets.UTF_8));
	}