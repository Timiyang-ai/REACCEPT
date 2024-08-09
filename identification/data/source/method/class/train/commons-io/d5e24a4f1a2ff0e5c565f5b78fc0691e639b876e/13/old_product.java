public static void copy(final String input, final OutputStream output)
            throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }