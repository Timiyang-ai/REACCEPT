public static void copy(
            InputStream input,
            Writer output,
            String encoding)
                throws IOException {
        InputStreamReader in = new InputStreamReader(input, encoding);
        copy(in, output);
    }