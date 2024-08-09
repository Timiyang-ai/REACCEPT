public static void copy(Reader input, OutputStream output)
            throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }