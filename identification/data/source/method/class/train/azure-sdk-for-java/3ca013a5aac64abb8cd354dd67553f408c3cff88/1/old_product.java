public void receive() {
        // BEGIN: com.azure.messaging.eventhubs.eventhubconsumer.receive#int-duration
        // Obtain partitionId from EventHubClient.getPartitionIds().
        String partitionId = "0";
        Instant twelveHoursAgo = Instant.now().minus(Duration.ofHours(12));
        EventHubConsumer consumer = client.createConsumer(EventHubAsyncClient.DEFAULT_CONSUMER_GROUP_NAME, partitionId,
            EventPosition.fromEnqueuedTime(twelveHoursAgo));

        IterableStream<EventData> events = consumer.receive(100, Duration.ofSeconds(30));

        for (EventData event : events) {
            // For each event, perform some sort of processing.
            System.out.print("Event received: " + event.getSequenceNumber());
        }

        // Gets the next set of events to consume and process.
        IterableStream<EventData> nextEvents = consumer.receive(100, Duration.ofSeconds(30));
        // END: com.azure.messaging.eventhubs.eventhubconsumer.receive#int-duration

        for (EventData event : nextEvents) {
            // For each event, perform some sort of processing.
            System.out.print("Event received: " + event.getSequenceNumber());
        }
    }