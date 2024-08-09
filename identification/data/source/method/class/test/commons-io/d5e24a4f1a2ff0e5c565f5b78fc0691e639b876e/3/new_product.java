public static void copy(
            byte[] input,
            Writer output,
            String encoding,
            int bufferSize)
                throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, encoding, bufferSize);
    }