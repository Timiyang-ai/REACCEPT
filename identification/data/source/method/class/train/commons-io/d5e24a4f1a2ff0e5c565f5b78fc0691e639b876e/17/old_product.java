public static void copy(
            final InputStream input,
            final Writer output)
                throws IOException {
        final InputStreamReader in = new InputStreamReader(input);
        copy(in, output);
    }