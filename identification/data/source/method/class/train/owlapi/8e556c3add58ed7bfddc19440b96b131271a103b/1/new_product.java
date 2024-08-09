public OBODoc parse(InputStream reader) {
        return parse(new InputStreamReader(reader, StandardCharsets.UTF_8));
    }