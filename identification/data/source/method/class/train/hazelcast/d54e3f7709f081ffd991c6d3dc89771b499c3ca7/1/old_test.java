    @Test
    public void runQueryOnLocalPartitions() {
        Predicate predicate = Predicates.equal("this", value);
        Query query = Query.of().mapName(map.getName()).predicate(predicate).iterationType(KEY).build();

        QueryResult result = queryEngine.execute(query, Target.LOCAL_NODE);

        assertEquals(1, result.size());
        assertEquals(key, toObject(result.iterator().next().getKey()));
    }