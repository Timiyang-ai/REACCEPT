public static byte[] stringToByteArray(String text, int length) {
        return Arrays.copyOf(text.getBytes(), length);
    }