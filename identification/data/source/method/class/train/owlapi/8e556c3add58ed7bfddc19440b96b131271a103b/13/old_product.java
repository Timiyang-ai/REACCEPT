@Nonnull
    public OBODoc parse(@Nonnull URL url) throws IOException {
        location = url;
        BufferedReader in = new BufferedReader(new InputStreamReader(url.openStream(),
            OBOFormatConstants.DEFAULT_CHARACTER_ENCODING));
        return parse(in);
    }