synchronized void resumeTransaction(long producerId, short epoch) {
    Preconditions.checkState(producerId >= 0 && epoch >= 0,
        "Incorrect values for producerId {} and epoch {}",
        producerId,
        epoch);
    LOG.info("Attempting to resume transaction {} with producerId {} and epoch {}", transactionalId, producerId, epoch);

    Object transactionManager = getValue(kafkaProducer, "transactionManager");

    Object nextSequence = getValue(transactionManager, "nextSequence");
    Object lastAckedSequence = getValue(transactionManager, "lastAckedSequence");

    invoke(transactionManager,
        "transitionTo",
        getEnum("org.apache.kafka.clients.producer.internals.TransactionManager$State.INITIALIZING"));
    invoke(nextSequence, "clear");
    invoke(lastAckedSequence, "clear");

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