public static int copy(final Reader input, final Writer output)
                throws IOException {
        return copy(input, output, DEFAULT_BUFFER_SIZE);
    }