public static void copy(
            final InputStream input,
            final Writer output,
            final int bufferSize)
                throws IOException {
        final InputStreamReader in = new InputStreamReader(input);
        copy(in, output, bufferSize);
    }