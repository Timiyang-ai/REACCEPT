@Deprecated
    public static void copy(
            final InputStream input,
            final Writer output)
                throws IOException {
        // make explicit the dependency on the default encoding
        final InputStreamReader in = new InputStreamReader(input, Charset.defaultCharset());
        copy(in, output);
    }