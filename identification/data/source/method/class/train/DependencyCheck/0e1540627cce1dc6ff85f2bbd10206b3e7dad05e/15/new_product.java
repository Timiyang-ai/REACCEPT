public synchronized Query parseQuery(String searchString) throws ParseException, IndexException {
        if (searchString == null || searchString.trim().isEmpty()) {
            throw new ParseException("Query is null or empty");
        }
        LOGGER.debug(searchString);

        final Query query = queryParser.parse(searchString);
        try {
            resetAnalyzers();
        } catch (IOException ex) {
            throw new IndexException("Unable to reset the analyzer after parsing", ex);
        }
        return query;
    }