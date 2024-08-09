@Test
    public void testDegrees() throws Exception {
        // create instances
        EntityType thing = graph.putEntityType("thing");
        EntityType anotherThing = graph.putEntityType("another");

        Entity entity1 = graph.putEntity("1", thing);
        Entity entity2 = graph.putEntity("2", thing);
        Entity entity3 = graph.putEntity("3", thing);
        Entity entity4 = graph.putEntity("4", anotherThing);

        RoleType relation1 = graph.putRoleType("relation1");
        RoleType relation2 = graph.putRoleType("relation2");
        thing.playsRole(relation1).playsRole(relation2);
        anotherThing.playsRole(relation1).playsRole(relation2);
        RelationType related = graph.putRelationType("related").hasRole(relation1).hasRole(relation2);

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
        // create instances
        thing = graph.putEntityType("thing");
        anotherThing = graph.putEntityType("another");

        entity1 = graph.putEntity("1", thing);
        entity2 = graph.putEntity("2", thing);
        entity3 = graph.putEntity("3", thing);
        entity4 = graph.putEntity("4", anotherThing);

        relation1 = graph.putRoleType("relation1");
        relation2 = graph.putRoleType("relation2");
        thing.playsRole(relation1).playsRole(relation2);
        anotherThing.playsRole(relation1).playsRole(relation2);
        related = graph.putRelationType("related").hasRole(relation1).hasRole(relation2);

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
        graph = MindmapsClient.getGraph(keyspace);
        thing = graph.getEntityType("thing");
        related = graph.getRelationType("related");
        computer = new Analytics(keyspace,Sets.newHashSet(thing, related));
        graph.close();
        degrees = computer.degrees();

        graph = MindmapsClient.getGraph(keyspace);
        correctDegrees.put(graph.getRelation(id3), 1l);

        assertTrue(!degrees.isEmpty());
        degrees.entrySet().forEach(degree -> {
            assertTrue(correctDegrees.containsKey(degree.getKey()));
            assertEquals(correctDegrees.get(degree.getKey()), degree.getValue());
        });
    }