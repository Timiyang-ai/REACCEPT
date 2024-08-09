public static byte[] decode(byte[] bytes, int len) {
        byte[] out = new byte[len];
        decode(bytes, 0, out, 0, len);
        return out;
    }