public static void copy(byte[] input, Writer output)
            throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output);
    }