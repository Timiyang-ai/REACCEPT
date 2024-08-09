private Flux<PartitionEvent> createConsumer(String linkName, String partitionId, EventPosition startingPosition,
            ReceiveOptions receiveOptions) {
        return openPartitionConsumers
            .computeIfAbsent(linkName, name -> {
                logger.info("{}: Creating receive consumer for partition '{}'", linkName, partitionId);
                return createPartitionConsumer(name, partitionId, startingPosition, receiveOptions);
            })
            .receive()
            .doFinally(signalType -> {
                logger.info("{}: Receiving completed. Partition: '{}'. Signal: '{}'", linkName, partitionId,
                    signalType);
                final EventHubPartitionAsyncConsumer consumer = openPartitionConsumers.remove(linkName);

                if (consumer != null) {
                    try {
                        consumer.close();
                    } catch (IOException e) {
                        logger.warning("Exception occurred while closing consumer {}", linkName, e);
                    }
                }
            });
    }