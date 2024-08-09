public static void copy(byte[] input, OutputStream output)
        throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }