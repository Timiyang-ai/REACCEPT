public static void copy(
            final InputStream input,
            final Writer output,
            final String encoding)
                throws IOException {
        final InputStreamReader in = new InputStreamReader(input, encoding);
        copy(in, output);
    }