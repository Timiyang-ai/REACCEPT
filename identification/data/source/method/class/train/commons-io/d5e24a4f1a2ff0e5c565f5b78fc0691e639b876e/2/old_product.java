public static void copy(
            final byte[] input,
            final Writer output,
            final String encoding)
                throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, encoding);
    }