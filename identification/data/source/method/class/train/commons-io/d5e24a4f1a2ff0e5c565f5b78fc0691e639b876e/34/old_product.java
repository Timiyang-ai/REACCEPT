public static void copy(byte[] input, Writer output)
        throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }