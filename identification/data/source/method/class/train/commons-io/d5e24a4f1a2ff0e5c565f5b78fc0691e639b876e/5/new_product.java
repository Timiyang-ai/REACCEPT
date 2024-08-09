public static void copy(
            byte[] input,
            Writer output,
            int bufferSize)
                throws IOException {
        ByteArrayInputStream in = new ByteArrayInputStream(input);
        copy(in, output, bufferSize);
    }