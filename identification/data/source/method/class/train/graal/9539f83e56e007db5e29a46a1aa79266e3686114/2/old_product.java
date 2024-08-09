@MethodSubstitution(optional = true)
    static int update(int crc, int b) {
        final long crcTableRawAddress = GraalHotSpotVMConfigNode.crcTableAddress();

        int c = ~crc;
        int index = (b ^ c) & 0xFF;
        int offset = index << 2;
        int result = Word.unsigned(crcTableRawAddress).readInt(offset);
        result = result ^ (c >>> 8);
        return ~result;
    }