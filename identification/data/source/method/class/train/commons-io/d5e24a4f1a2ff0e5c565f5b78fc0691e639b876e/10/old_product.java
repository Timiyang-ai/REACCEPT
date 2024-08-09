public static int copy(final InputStream input, final OutputStream output)
                throws IOException {
        return copy(input, output, DEFAULT_BUFFER_SIZE);
    }