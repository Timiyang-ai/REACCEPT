@Override
    public Mono<EventHubProperties> getEventHubProperties() {
        final Map<String, Object> properties = new HashMap<>();
        properties.put(MANAGEMENT_ENTITY_TYPE_KEY, MANAGEMENT_EVENTHUB_ENTITY_TYPE);
        properties.put(MANAGEMENT_ENTITY_NAME_KEY, eventHubName);
        properties.put(MANAGEMENT_OPERATION_KEY, READ_OPERATION_VALUE);

        return getProperties(properties, mapper::toEventHubProperties);
    }