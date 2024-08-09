@ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PartitionProperties> getPartitionProperties(String partitionId) {
        return connectionProcessor.flatMap(connection -> connection.getManagementNode())
            .flatMap(node -> node.getPartitionProperties(partitionId));
    }