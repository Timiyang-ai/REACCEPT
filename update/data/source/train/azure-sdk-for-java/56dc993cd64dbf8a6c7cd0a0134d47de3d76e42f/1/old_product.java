@ServiceMethod(returns = ReturnType.COLLECTION)
    public IterableResponse<String> getPartitionIds() {
        return new IterableResponse<>(client.getPartitionIds());
    }