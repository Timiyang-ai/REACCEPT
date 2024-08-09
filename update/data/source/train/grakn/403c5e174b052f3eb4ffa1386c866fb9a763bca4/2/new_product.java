private void degreesAndPersist(String resourceType) {
        insertOntology(resourceType, ResourceType.DataType.LONG);
        MindmapsComputer computer = MindmapsClient.getGraphComputer(keySpace);
        computer.compute(new DegreeAndPersistVertexProgram(keySpace,allTypes));
    }