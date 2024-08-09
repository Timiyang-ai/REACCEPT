@Procedure(mode = Mode.READ)
    @Description("apoc.index.search('name', 'query', [maxNumberOfResults]) YIELD node, weight - search for nodes in the free text index matching the given query")
    public Stream<WeightedNodeResult> search(@Name("index") String index, @Name("query") String query,
                                             @Name(value="numberOfResults", defaultValue = "100") long maxNumberOfresults) throws Exception {
        if (!db.index().existsForNodes(index)) {
            return Stream.empty();
        }
        QueryContext queryParam = new QueryContext(parseFreeTextQuery(query)).sort(Sort.RELEVANCE);
        if (maxNumberOfresults!=-1) {
            queryParam = queryParam.top((int)maxNumberOfresults);
        }
        return toWeightedNodeResult(db.index().forNodes(index).query(queryParam));    
    }