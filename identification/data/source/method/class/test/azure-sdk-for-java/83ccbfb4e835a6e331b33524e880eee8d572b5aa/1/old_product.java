public void receive() {
        // BEGIN: com.azure.messaging.eventhubs.eventhubconsumerasyncclient.receive
        // Obtain partitionId from EventHubAsyncClient.getPartitionIds()
        String partitionId = "0";

        EventHubConsumerAsyncClient consumer = new EventHubClientBuilder().connectionString("fake-string")
            .startingPosition(EventPosition.latest())
            .consumerGroup(EventHubClientBuilder.DEFAULT_CONSUMER_GROUP_NAME)
            .buildAsyncConsumer();

        // Keep a reference to `subscription`. When the program is finished receiving events, call
        // subscription.dispose(). This will stop fetching events from the Event Hub.
        Disposable subscription = consumer.receive(partitionId).subscribe(event -> {
            // process event
        }, error -> System.err.print(error.toString()));
        // END: com.azure.messaging.eventhubs.eventhubconsumerasyncclient.receive

        subscription.dispose();
    }