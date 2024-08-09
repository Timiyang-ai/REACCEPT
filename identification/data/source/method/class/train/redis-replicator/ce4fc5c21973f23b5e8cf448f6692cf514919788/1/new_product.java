public static ByteArray decode(ByteArray bytes, long len) {
        ByteArray out = new ByteArray(len);
        decode(bytes, 0, out, 0, len);
        return out;
    }