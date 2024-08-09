public static void copy(String input, OutputStream output)
            throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }