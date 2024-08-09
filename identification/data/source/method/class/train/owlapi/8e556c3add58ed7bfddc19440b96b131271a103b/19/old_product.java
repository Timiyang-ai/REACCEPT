@Nonnull
    public OBODoc parse(@Nonnull URL url) throws IOException {
        location = url;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                url.openStream(), StandardCharsets.UTF_8));
        return parse(in);
    }