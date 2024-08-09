public static int crc32AsInt(byte[] input) {
		CRC32 crc32 = new CRC32();
		crc32.update(input);
		// CRC32 只是 32bit int，为了CheckSum接口强转成long，此处再次转回来
		return (int) crc32.getValue();
	}