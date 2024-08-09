@Override
    public Mono<PartitionProperties> getPartitionProperties(String partitionId) {
        final Map<String, Object> properties = new HashMap<>();
        properties.put(MANAGEMENT_ENTITY_TYPE_KEY, MANAGEMENT_PARTITION_ENTITY_TYPE);
        properties.put(MANAGEMENT_ENTITY_NAME_KEY, eventHubName);
        properties.put(MANAGEMENT_PARTITION_NAME_KEY, partitionId);
        properties.put(MANAGEMENT_OPERATION_KEY, READ_OPERATION_VALUE);

        return getProperties(properties, mapper::toPartitionProperties);
    }