@Ignore
    @Test
    public void testDegreesAndPersist() throws Exception {
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

        Map<Instance, Long> correctDegrees = new HashMap<>();
        correctDegrees.put(entity1, 1l);
        correctDegrees.put(entity2, 3l);
        correctDegrees.put(entity3, 1l);
        correctDegrees.put(graph.getRelation(id1), 2l);
        correctDegrees.put(graph.getRelation(id2), 2l);
        correctDegrees.put(graph.getRelation(id3), 1l);

        // compute degrees on subgraph
        Analytics computer = new Analytics(keyspace,Sets.newHashSet(thing, related));
        computer.degreesAndPersist();

        // assert persisted degrees are correct
        instantiateSimpleConcepts();
        correctDegrees.entrySet().forEach(degree -> {
            Instance instance = degree.getKey();
            Collection<Resource<?>> resources = null;
            if (instance.isEntity()) {
                resources = instance.asEntity().resources();
            } else if (instance.isRelation()) {
                resources = instance.asRelation().resources();
            }
            assert resources != null;
            assertTrue(resources.iterator().next().getValue().equals(degree.getValue()));
        });

        long numVertices = 0;

        // compute again and again ...
        for (int i = 0; i < 2; i++) {
            System.out.println();
            System.out.println("i = " + i);
            computer.degreesAndPersist();

            correctDegrees.entrySet().forEach(degree -> {
                Instance instance = degree.getKey();
                Collection<Resource<?>> resources = null;
                if (instance.isEntity()) {
                    resources = instance.asEntity().resources();
                } else if (instance.isRelation()) {
                    resources = instance.asRelation().resources();
                }
                assert resources != null;
                assertTrue(resources.iterator().next().getValue().equals(degree.getValue()));
            });

            // assert the number of vertices remain the same
            if (i == 0) {
                numVertices = computer.count();
            } else {
                assertEquals(numVertices, computer.count());
            }
        }

        // compute degrees on all types, again and again ...
        for (int i = 0; i < 3; i++) {
            computer = new Analytics(keyspace);
            computer.degreesAndPersist();

            correctDegrees.put(entity4, 1l);
            correctDegrees.put(graph.getRelation(id3), 2l);

            correctDegrees.entrySet().forEach(degree -> {
                Instance instance = degree.getKey();
                Collection<Resource<?>> resources = null;
                if (instance.isEntity()) {
                    resources = instance.asEntity().resources();
                } else if (instance.isRelation()) {
                    resources = instance.asRelation().resources();
                }
                assert resources != null;
                assert !resources.isEmpty();
                assertEquals(resources.iterator().next().getValue(), degree.getValue());
            });

            // assert the number of vertices remain the same
            if (i == 0) {
                assertEquals(1, computer.count() - numVertices);
                numVertices = computer.count();
            } else {
                assertEquals(numVertices, computer.count());
            }
        }
    }