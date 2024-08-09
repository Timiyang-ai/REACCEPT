private void degreesAndPersist(String resourceType) {
        MindmapsTransaction transaction= graph.getTransaction();
        insertOntology(resourceType, Data.LONG);
        ComputerResult result = computer.compute(new DegreeAndPersistVertexProgram(keySpace,allTypes));

        // TODO: get rid of this in the future MASSIVE bottleneck
        // collect relation ids and delete them in a single thread
        result.graph().traversal().V().forEachRemaining(v -> {
            if (v.keys().contains(DegreeAndPersistVertexProgram.OLD_ASSERTION_ID)) {
                Relation relation = transaction.getRelation(v.value(DegreeAndPersistVertexProgram.OLD_ASSERTION_ID));
                relation.delete();
            }
        });

        try {
            transaction.commit();
        } catch (MindmapsValidationException e) {
            e.printStackTrace();
        }
    }