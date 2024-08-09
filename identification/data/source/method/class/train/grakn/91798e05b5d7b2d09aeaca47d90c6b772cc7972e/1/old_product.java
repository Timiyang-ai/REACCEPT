public Map<Instance, Long> degrees() {
        Map<Instance, Long> allDegrees = new HashMap<>();
        ComputerResult result = computer.compute(new DegreeVertexProgram(allTypes));
        result.graph().traversal().V().forEachRemaining(v -> {
            if (v.keys().contains(DegreeVertexProgram.DEGREE)) {
                Instance instance = graph.getTransaction().getInstance(v.value(ITEM_IDENTIFIER.name()));
                allDegrees.put(instance, v.value(DegreeVertexProgram.DEGREE));
            }
        });
        return allDegrees;
    }