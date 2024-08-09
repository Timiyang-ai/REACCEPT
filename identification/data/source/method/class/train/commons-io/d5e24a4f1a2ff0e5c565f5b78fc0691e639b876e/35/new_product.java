public static void copy(
            InputStream input,
            Writer output,
            int bufferSize)
                throws IOException {
        InputStreamReader in = new InputStreamReader(input);
        copy(in, output, bufferSize);
    }