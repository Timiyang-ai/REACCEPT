@Test
    public void testDegreesAndPersist() throws Exception {
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

        Map<Instance, Long> correctDegrees = new HashMap<>();

        // compute degrees on subgraph
        Analytics computer = new Analytics(keyspace,Sets.newHashSet(thing, related));
        computer.degreesAndPersist();

        // fetch instances
        graph = MindmapsClient.getGraph(keyspace);
        entity1 = graph.getEntity("1");
        entity2 = graph.getEntity("2");
        entity3 = graph.getEntity("3");

        correctDegrees.clear();
        correctDegrees.put(entity1, 1l);
        correctDegrees.put(entity2, 3l);
        correctDegrees.put(entity3, 1l);
        correctDegrees.put(graph.getRelation(id1), 2l);
        correctDegrees.put(graph.getRelation(id2), 2l);
        correctDegrees.put(graph.getRelation(id3), 1l);

        // assert persisted degrees are correct
        checkDegrees(graph,correctDegrees);

        long numVertices = 0;

        // compute again and again ...
        for (int i = 0; i < 2; i++) {
            graph.close();
            computer.degreesAndPersist();

            // refresh everything after commit
            graph = MindmapsClient.getGraph(keyspace);
            // fetch instances
            entity1 = graph.getEntity("1");
            entity2 = graph.getEntity("2");
            entity3 = graph.getEntity("3");

            correctDegrees.clear();
            correctDegrees.put(entity1, 1l);
            correctDegrees.put(entity2, 3l);
            correctDegrees.put(entity3, 1l);
            correctDegrees.put(graph.getRelation(id1), 2l);
            correctDegrees.put(graph.getRelation(id2), 2l);
            correctDegrees.put(graph.getRelation(id3), 1l);

            checkDegrees(graph, correctDegrees);

            // assert the number of vertices remain the same
            if (i == 0) {
                numVertices = computer.count();
            } else {
                assertEquals(numVertices, computer.count());
            }
        }


        // compute degrees on all types, again and again ...
        for (int i = 0; i < 2; i++) {

            graph.close();
            computer = new Analytics(keyspace);
            computer.degreesAndPersist();

            // after computation refresh concepts
            graph = MindmapsClient.getGraph(keyspace);

            // fetch instances
            entity1 = graph.getEntity("1");
            entity2 = graph.getEntity("2");
            entity3 = graph.getEntity("3");
            entity4 = graph.getEntity("4");

            correctDegrees.clear();
            correctDegrees.put(entity1, 1l);
            correctDegrees.put(entity2, 3l);
            correctDegrees.put(entity3, 1l);
            correctDegrees.put(graph.getRelation(id1), 2l);
            correctDegrees.put(graph.getRelation(id2), 2l);
            correctDegrees.put(entity4, 1l);
            correctDegrees.put(graph.getRelation(id3), 2l);

            checkDegrees(graph,correctDegrees);

            // assert the number of vertices remain the same
            if (i == 0) {
                assertEquals(1, computer.count() - numVertices);
                numVertices = computer.count();
            } else {
                assertEquals(numVertices, computer.count());
            }
        }
    }