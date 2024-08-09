public OBODoc parse(String fn) throws IOException {
        if (fn.startsWith("http:")) {
            return parse(new URL(fn));
        }
        return parse(new File(fn));
    }