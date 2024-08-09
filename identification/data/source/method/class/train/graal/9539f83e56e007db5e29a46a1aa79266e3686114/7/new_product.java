@MethodSubstitution(optional = true)
    static int updateBytes(int crc, byte[] buf, int off, int len) {
        Word bufAddr = Word.unsigned(ComputeObjectAddressNode.get(buf, arrayBaseOffset(JavaKind.Byte) + off));
        return updateBytesCRC32(UPDATE_BYTES_CRC32, crc, bufAddr, len);
    }