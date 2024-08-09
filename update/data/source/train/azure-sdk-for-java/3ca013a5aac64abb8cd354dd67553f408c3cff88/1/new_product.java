public void receive() {
        // BEGIN: com.azure.messaging.eventhubs.eventhubconsumerclient.receive#int-duration
        // Obtain partitionId from EventHubClient.getPartitionIds().
        String partitionId = "0";
        Instant twelveHoursAgo = Instant.now().minus(Duration.ofHours(12));
        EventHubConsumerClient consumer = new EventHubClientBuilder()
            .connectionString("event-hub-instance-connection-string")
            .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
            .startingPosition(EventPosition.fromEnqueuedTime(twelveHoursAgo))
            .buildConsumer();

        IterableStream<PartitionEvent> events = consumer.receive(partitionId, 100, Duration.ofSeconds(30));

        for (PartitionEvent partitionEvent : events) {
            // For each event, perform some sort of processing.
            System.out.print("Event received: " + partitionEvent.getEventData().getSequenceNumber());
        }

        // Gets the next set of events to consume and process.
        IterableStream<PartitionEvent> nextEvents = consumer.receive(partitionId, 100, Duration.ofSeconds(30));
        // END: com.azure.messaging.eventhubs.eventhubconsumerclient.receive#int-duration

        for (PartitionEvent partitionEvent : nextEvents) {
            // For each event, perform some sort of processing.
            System.out.print("Event received: " + partitionEvent.getEventData().getSequenceNumber());
        }
    }