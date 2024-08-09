@Procedure
    @PerformsWrites
    @Description("apoc.index.search('name', 'query') YIELD node, weight - search for nodes in the free text index matching the given query")
    public Stream<WeightedNodeResult> search(@Name("index") String index, @Name("query") String query) throws ParseException {
        if (!db.index().existsForNodes(index)) {
            return Stream.empty();
        }
        return result(db.index().forNodes(index).query(
                new QueryContext(parseFreeTextQuery(query)).sort(Sort.RELEVANCE).top(100)));
    }