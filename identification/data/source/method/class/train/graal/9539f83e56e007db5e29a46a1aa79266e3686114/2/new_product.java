@MethodSubstitution(optional = true)
    static int update(int crc, int b) {
        final Pointer crcTableRawAddress = WordFactory.pointer(GraalHotSpotVMConfigNode.crcTableAddress());

        int c = ~crc;
        int index = (b ^ c) & 0xFF;
        int offset = index << 2;
        int result = crcTableRawAddress.readInt(offset);
        result = result ^ (c >>> 8);
        return ~result;
    }