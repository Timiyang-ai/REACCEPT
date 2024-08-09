public static int writeSignedVarLong(long value, DataOutput out) throws IOException {
        // Great trick from http://code.google.com/apis/protocolbuffers/docs/encoding.html#types
        return writeUnsignedVarLong((value << 1) ^ (value >> 63), out);
    }