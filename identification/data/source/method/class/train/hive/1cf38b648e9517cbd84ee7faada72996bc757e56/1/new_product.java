synchronized void resumeTransaction(long producerId, short epoch) {
    Preconditions.checkState(producerId >= 0 && epoch >= 0,
        "Incorrect values for producerId {} and epoch {}",
        producerId,
        epoch);
    LOG.info("Attempting to resume transaction {} with producerId {} and epoch {}", transactionalId, producerId, epoch);

    Object transactionManager = getValue(kafkaProducer, "transactionManager");

    Object topicPartitionBookkeeper = getValue(transactionManager, "topicPartitionBookkeeper");
    invoke(transactionManager,
        "transitionTo",
        getEnum("org.apache.kafka.clients.producer.internals.TransactionManager$State.INITIALIZING"));
    invoke(topicPartitionBookkeeper, "reset");
    Object producerIdAndEpoch = getValue(transactionManager, "producerIdAndEpoch");
    setValue(producerIdAndEpoch, "producerId", producerId);
    setValue(producerIdAndEpoch, "epoch", epoch);

    invoke(transactionManager,
        "transitionTo",
        getEnum("org.apache.kafka.clients.producer.internals.TransactionManager$State.READY"));

    invoke(transactionManager,
        "transitionTo",
        getEnum("org.apache.kafka.clients.producer.internals.TransactionManager$State.IN_TRANSACTION"));
    setValue(transactionManager, "transactionStarted", true);
  }