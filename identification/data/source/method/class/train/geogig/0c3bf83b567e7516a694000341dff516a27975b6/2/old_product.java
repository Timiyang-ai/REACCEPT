public static void writeSignedVarInt(int value, DataOutput out) throws IOException {
        // Great trick from http://code.google.com/apis/protocolbuffers/docs/encoding.html#types
        writeUnsignedVarInt((value << 1) ^ (value >> 31), out);
    }