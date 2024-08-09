public static byte[] asciiStringToByteArray(String text, int length) {
        return Arrays.copyOf(text.getBytes(StandardCharsets.US_ASCII), length);
    }