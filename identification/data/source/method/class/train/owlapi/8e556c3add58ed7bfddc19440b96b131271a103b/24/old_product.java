@Nonnull
    public OBODoc parse(@Nonnull URL url) throws IOException {
        location = url;
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(), "UTF-8"));
        return parse(in);
    }