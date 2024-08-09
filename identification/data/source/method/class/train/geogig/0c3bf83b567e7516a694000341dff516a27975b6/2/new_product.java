public static int writeSignedVarInt(int value, DataOutput out) throws IOException {
        // Great trick from http://code.google.com/apis/protocolbuffers/docs/encoding.html#types
        return writeUnsignedVarInt((value << 1) ^ (value >> 31), out);
    }