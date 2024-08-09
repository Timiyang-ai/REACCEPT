public OBODoc parse(URL url) throws IOException {
        location = url;
        return parse(url.toString());
    }