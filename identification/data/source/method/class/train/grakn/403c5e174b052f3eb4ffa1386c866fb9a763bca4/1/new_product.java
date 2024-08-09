public Map<Instance, Long> degrees() {
        Map<Instance, Long> allDegrees = new HashMap<>();
        MindmapsComputer computer = MindmapsClient.getGraphComputer(keySpace);
        ComputerResult result = computer.compute(new DegreeVertexProgram(allTypes));
        MindmapsGraph graph = MindmapsClient.getGraph(keySpace);
        result.graph().traversal().V().forEachRemaining(v -> {
            if (v.keys().contains(DegreeVertexProgram.DEGREE)) {
                Instance instance = graph.getInstance(v.value(ITEM_IDENTIFIER.name()));
                allDegrees.put(instance, v.value(DegreeVertexProgram.DEGREE));
            }
        });
        return allDegrees;
    }