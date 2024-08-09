public OBODoc parse(InputStream reader) throws IOException {
        return parse(new InputStreamReader(reader, StandardCharsets.UTF_8));
    }