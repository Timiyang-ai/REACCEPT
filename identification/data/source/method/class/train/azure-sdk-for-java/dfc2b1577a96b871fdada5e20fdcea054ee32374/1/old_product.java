@ServiceMethod(returns = ReturnType.SINGLE)
    public Mono<PartitionProperties> getPartitionProperties(String partitionId) {
        return connection.getManagementNode().flatMap(node -> node.getPartitionProperties(partitionId));
    }