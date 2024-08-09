@Ignore
    @Test
    public void testDegrees() throws Exception {
        instantiateSimpleConcepts();

        // relate them
        String id1 = UUID.randomUUID().toString();
        graph.putRelation(id1, related)
                .putRolePlayer(relation1, entity1)
                .putRolePlayer(relation2, entity2);

        String id2 = UUID.randomUUID().toString();
        graph.putRelation(id2, related)
                .putRolePlayer(relation1, entity2)
                .putRolePlayer(relation2, entity3);

        String id3 = UUID.randomUUID().toString();
        graph.putRelation(id3, related)
                .putRolePlayer(relation1, entity2)
                .putRolePlayer(relation2, entity4);

        graph.commit();

        // assert degrees are correct
        instantiateSimpleConcepts();
        Map<Instance, Long> correctDegrees = new HashMap<>();
        correctDegrees.put(entity1, 1l);
        correctDegrees.put(entity2, 3l);
        correctDegrees.put(entity3, 1l);
        correctDegrees.put(entity4, 1l);
        correctDegrees.put(graph.getRelation(id1), 2l);
        correctDegrees.put(graph.getRelation(id2), 2l);
        correctDegrees.put(graph.getRelation(id3), 2l);

        // compute degrees
        Analytics computer = new Analytics(keyspace);
        Map<Instance, Long> degrees = computer.degrees();

        assertTrue(!degrees.isEmpty());
        degrees.entrySet().forEach(degree -> {
            assertTrue(correctDegrees.containsKey(degree.getKey()));
            assertEquals(correctDegrees.get(degree.getKey()), degree.getValue());
        });

        // compute degrees again after persisting degrees
        computer.degreesAndPersist();
        Map<Instance, Long> degrees2 = computer.degrees();

        assertEquals(degrees.size(), degrees2.size());

        assertTrue(!degrees.isEmpty());
        degrees.entrySet().forEach(degree -> {
            assertTrue(correctDegrees.containsKey(degree.getKey()));
            assertTrue(correctDegrees.get(degree.getKey()).equals(degree.getValue()));
        });

        // compute degrees on subgraph
        computer = new Analytics(keyspace,Sets.newHashSet(thing, related));
        degrees = computer.degrees();

        correctDegrees.put(graph.getRelation(id3), 1l);

        assertTrue(!degrees.isEmpty());
        degrees.entrySet().forEach(degree -> {
            assertTrue(correctDegrees.containsKey(degree.getKey()));
            assertEquals(correctDegrees.get(degree.getKey()), degree.getValue());
        });
    }