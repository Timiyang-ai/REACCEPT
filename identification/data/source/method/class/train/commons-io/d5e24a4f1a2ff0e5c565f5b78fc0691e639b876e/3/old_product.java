public static int copy(Reader input, Writer output)
                throws IOException {
        return copy(input, output, DEFAULT_BUFFER_SIZE);
    }