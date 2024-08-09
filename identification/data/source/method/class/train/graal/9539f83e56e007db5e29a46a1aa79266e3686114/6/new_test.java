    public static long updateBytes(byte[] input, int offset, int length) {
        CRC32 crc = new CRC32();
        crc.update(input, offset, length);
        return crc.getValue();
    }