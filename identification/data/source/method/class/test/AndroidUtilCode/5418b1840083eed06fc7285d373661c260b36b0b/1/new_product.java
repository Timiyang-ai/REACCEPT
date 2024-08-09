public static byte[] inputStream2Bytes(final InputStream is) {
        if (is == null) return null;
        return input2OutputStream(is).toByteArray();
    }