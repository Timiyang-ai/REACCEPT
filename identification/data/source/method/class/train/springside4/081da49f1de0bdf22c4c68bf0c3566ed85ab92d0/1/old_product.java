public static int crc32(String input) {
		CRC32 crc32 = new CRC32();
		crc32.update(input.getBytes(Charsets.UTF_8));
		return (int) crc32.getValue();
	}