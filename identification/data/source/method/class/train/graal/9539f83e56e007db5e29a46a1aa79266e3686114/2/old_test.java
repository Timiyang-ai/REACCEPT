    public static long update(byte[] input) {
        CRC32 crc = new CRC32();
        for (byte b : input) {
            crc.update(b);
        }
        return crc.getValue();
    }