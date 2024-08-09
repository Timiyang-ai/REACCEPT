@Override
    public QueryBuilder fromXContent(QueryParseContext parseContext) throws IOException, QueryParsingException {
        Query query = parse(parseContext.shardContext());
        return new QueryWrappingQueryBuilder(query);
    }