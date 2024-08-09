public Hints parseHints(InputStream inputStream) throws HintParseException, SAXException {
        return parseHints(inputStream, HINT_SCHEMA);
    }