@MethodSubstitution(optional = true)
    static int updateByteBuffer(int crc, long addr, int off, int len) {
        WordBase bufAddr = WordFactory.unsigned(addr).add(off);
        return updateBytesCRC32(UPDATE_BYTES_CRC32, crc, bufAddr, len);
    }