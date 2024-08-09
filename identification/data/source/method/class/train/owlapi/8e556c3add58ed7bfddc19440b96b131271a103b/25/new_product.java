public OBODoc parse(URL url) {
        location = url;
        return parse(url.toString());
    }