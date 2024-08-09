public OBODoc parse(String fn) throws IOException, OBOFormatParserException {
        if (fn.startsWith("http:")) {
            return parse(new URL(fn));
        }
        return parse(new File(fn));
    }