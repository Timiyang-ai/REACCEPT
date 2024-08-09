public static void copy(
            InputStream input,
            Writer output)
                throws IOException {
        InputStreamReader in = new InputStreamReader(input);
        copy(in, output);
    }