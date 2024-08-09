protected Query parseQuery(String searchString) throws ParseException {
        if (searchString == null || searchString.trim().isEmpty()) {
            throw new ParseException("Query is null or empty");
        }
        LOGGER.debug(searchString);
        final Query query = queryParser.parse(searchString);
        return query;
    }