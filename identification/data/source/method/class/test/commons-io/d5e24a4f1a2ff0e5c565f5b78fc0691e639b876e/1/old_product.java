public static void copy(final InputStream input, final Writer output)
            throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }