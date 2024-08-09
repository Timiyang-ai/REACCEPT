  private void run() {
    ReadDnsQueueAction action = new ReadDnsQueueAction();
    action.tldUpdateBatchSize = TEST_TLD_UPDATE_BATCH_SIZE;
    action.requestedMaximumDuration = Duration.standardSeconds(10);
    action.clock = clock;
    action.dnsQueue = dnsQueue;
    action.dnsPublishPushQueue = QueueFactory.getQueue(DNS_PUBLISH_PUSH_QUEUE_NAME);
    action.hashFunction = Hashing.murmur3_32();
    action.taskQueueUtils = new TaskQueueUtils(new Retrier(null, 1));
    action.jitterSeconds = Optional.empty();
    // Advance the time a little, to ensure that leaseTasks() returns all tasks.
    clock.advanceBy(Duration.standardHours(1));

    action.run();
  }