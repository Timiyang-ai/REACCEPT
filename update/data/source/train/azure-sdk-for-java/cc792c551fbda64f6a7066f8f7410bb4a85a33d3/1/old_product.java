public void receive() {
        EventHubConsumerAsyncClient consumer = new EventHubClientBuilder()
            .connectionString("fake-string")
            .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
            .buildAsyncConsumer();

        // BEGIN: com.azure.messaging.eventhubs.eventhubconsumerasyncclient.receive#string-eventposition
        // Obtain partitionId from EventHubConsumerAsyncClient.getPartitionIds()
        String partitionId = "0";
        EventPosition startingPosition = EventPosition.latest();

        // Keep a reference to `subscription`. When the program is finished receiving events, call
        // subscription.dispose(). This will stop fetching events from the Event Hub.
        Disposable subscription = consumer.receiveFromPartition(partitionId, startingPosition)
            .subscribe(partitionEvent -> {
                PartitionContext partitionContext = partitionEvent.getPartitionContext();
                EventData event = partitionEvent.getData();

                System.out.printf("Received event from partition '%s'%n", partitionContext.getPartitionId());
                System.out.printf("Contents of event as string: '%s'%n", event.getBodyAsString());
            }, error -> System.err.print(error.toString()));
        // END: com.azure.messaging.eventhubs.eventhubconsumerasyncclient.receive#string-eventposition

        // When program ends, or you're done receiving all events.
        subscription.dispose();
    }