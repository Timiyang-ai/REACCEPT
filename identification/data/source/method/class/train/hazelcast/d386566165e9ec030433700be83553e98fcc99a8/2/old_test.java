    @Test
    public void runQueryOnGivenPartition() {
        Predicate predicate = Predicates.equal("this", value);
        Query query = Query.of().mapName(map.getName()).predicate(predicate).iterationType(ENTRY).build();

        QueryResult result = queryEngine.execute(query, createPartitionTarget(partitionId));

        assertEquals(1, result.size());
        assertEquals(key, toObject(((Map.Entry) result.iterator().next()).getKey()));
        assertEquals(map.get(key), toObject(((Map.Entry) result.iterator().next()).getValue()));
    }