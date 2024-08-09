@Procedure(mode = Mode.READ)
    @Description("apoc.index.search('name', 'query') YIELD node, weight - search for nodes in the free text index matching the given query")
    public Stream<WeightedNodeResult> search(@Name("index") String index, @Name("query") String query) throws Exception {
        if (!db.index().existsForNodes(index)) {
            return Stream.empty();
        }
        QueryContext queryParam = new QueryContext(parseFreeTextQuery(query)).sort(Sort.RELEVANCE).top(100);
        List<WeightedNodeResult> hits = KernelApi.toWeightedNodeResultFromLegacyIndex(KernelApi.nodeQueryIndex(index, queryParam, db), db);

        return hits.stream();
    }