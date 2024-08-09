public void initialization() {
        // BEGIN: com.azure.messaging.eventhubs.eventhubconsumerasyncclient.instantiation
        // The required parameters are startingPosition, consumerGroup, and a way to authenticate with Event Hubs
        // using credentials.
        EventHubConsumerAsyncClient consumer = new EventHubClientBuilder()
            .connectionString("fake-string")
            .startingPosition(EventPosition.latest())
            .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
            .buildAsyncConsumer();
        // END: com.azure.messaging.eventhubs.eventhubconsumerasyncclient.instantiation
    }