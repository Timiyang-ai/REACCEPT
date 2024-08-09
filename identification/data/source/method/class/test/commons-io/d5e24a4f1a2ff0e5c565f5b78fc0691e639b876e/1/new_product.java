public static void copy(InputStream input, Writer output)
            throws IOException {
        copy(input, output, DEFAULT_BUFFER_SIZE);
    }