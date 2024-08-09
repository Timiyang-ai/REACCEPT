public static void copy(
            final byte[] input,
            final Writer output,
            final String encoding,
            final int bufferSize)
                throws IOException {
        final ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, encoding, bufferSize);
    }