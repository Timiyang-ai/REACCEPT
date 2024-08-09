public static void copy(final Reader input, final OutputStream output)
            throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }