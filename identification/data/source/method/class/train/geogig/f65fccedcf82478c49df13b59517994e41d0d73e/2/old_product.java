public static void writeUnsignedVarLong(long value, DataOutput out) throws IOException {
        while ((value & 0xFFFFFFFFFFFFFF80L) != 0L) {
            out.writeByte(((int) value & 0x7F) | 0x80);
            value >>>= 7;
        }
        out.writeByte((int) value & 0x7F);
    }