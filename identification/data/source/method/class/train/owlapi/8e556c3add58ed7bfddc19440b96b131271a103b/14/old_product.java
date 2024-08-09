@Nonnull
    public OBODoc parse(File file) throws IOException {
        location = file;
        BufferedReader in = new BufferedReader(new InputStreamReader(
                new FileInputStream(file),
                OBOFormatConstants.DEFAULT_CHARACTER_ENCODING));
        return parse(in);
    }