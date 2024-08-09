  @Test(timeout = 120_000) public void resumeTransaction() {
    producer.initTransactions();
    producer.beginTransaction();
    long pid = producer.getProducerId();
    short epoch = producer.getEpoch();
    Assert.assertTrue(pid > -1);
    Assert.assertTrue(epoch > -1);
    //noinspection unchecked
    RECORDS.forEach(producer::send);
    producer.flush();
    producer.close(Duration.ZERO);

    HiveKafkaProducer secondProducer = new HiveKafkaProducer(producerProperties);
    secondProducer.resumeTransaction(pid, epoch);
    secondProducer.sendOffsetsToTransaction(ImmutableMap.of(), "__dummy_consumer_group");
    secondProducer.commitTransaction();
    secondProducer.close();

    Collection<TopicPartition> assignment = Collections.singletonList(new TopicPartition(TOPIC, 0));
    consumer.assign(assignment);
    consumer.seekToBeginning(assignment);
    long numRecords = 0;
    @SuppressWarnings("unchecked") final List<ConsumerRecord<byte[], byte[]>> actualRecords = new ArrayList();
    short attempts = 0;
    while (numRecords < RECORD_NUMBER && attempts++ < MAX_ATTEMPTS) {
      ConsumerRecords<byte[], byte[]> consumerRecords = consumer.poll(Duration.ofMillis(1_000));
      actualRecords.addAll(consumerRecords.records(new TopicPartition(TOPIC, 0)));
      numRecords += consumerRecords.count();
    }
    if(attempts >= MAX_ATTEMPTS) {
      Assert.fail("Reached max attempts and total number of records is " + numRecords);
    }
    Assert.assertEquals("Size matters !!", RECORDS.size(), actualRecords.size());
    Iterator<ProducerRecord<byte[], byte[]>> expectedIt = RECORDS.iterator();
    Iterator<ConsumerRecord<byte[], byte[]>> actualIt = actualRecords.iterator();
    while (expectedIt.hasNext()) {
      ProducerRecord<byte[], byte[]> expected = expectedIt.next();
      ConsumerRecord<byte[], byte[]> actual = actualIt.next();
      Assert.assertArrayEquals("value not matching", expected.value(), actual.value());
      Assert.assertArrayEquals("key not matching", expected.key(), actual.key());
    }
  }