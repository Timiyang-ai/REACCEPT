public static String bytes2HexString(byte[] bytes) {
        char[] res = new char[bytes.length << 1];
        for (int i = 0, j = 0; i < bytes.length; i++) {
            res[j++] = hexDigits[bytes[i] >>> 4 & 0x0f];
            res[j++] = hexDigits[bytes[i] & 0x0f];
        }
        return new String(res);
    }