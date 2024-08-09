@Nonnull
    public OBODoc parse(File file) throws IOException {
        location = file;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(file), StandardCharsets.UTF_8));
        try {
            return parse(in);
        } finally {
            in.close();
        }
    }