@ServiceMethod(returns = ReturnType.SINGLE)
    public PartitionProperties getPartitionProperties(String partitionId) {
        return client.getPartitionProperties(partitionId).block(retry.getTryTimeout());
    }