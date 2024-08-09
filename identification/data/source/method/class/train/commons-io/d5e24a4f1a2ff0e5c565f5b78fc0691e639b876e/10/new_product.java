public static int copy(InputStream input, OutputStream output)
                throws IOException {
        return copy(input, output, DEFAULT_BUFFER_SIZE);
    }