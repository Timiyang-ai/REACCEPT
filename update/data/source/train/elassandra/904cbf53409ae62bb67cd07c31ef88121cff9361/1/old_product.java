@Override
    public QueryBuilder fromXContent(QueryParseContext parseContext) throws IOException, QueryParsingException {
        Query query = parse(parseContext);
        return new QueryWrappingQueryBuilder(query);
    }