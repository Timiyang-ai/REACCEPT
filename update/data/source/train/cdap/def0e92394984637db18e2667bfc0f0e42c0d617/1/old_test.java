@Test
  public void testUnack() throws OperationException {

    CConfiguration conf = new CConfiguration();

    StreamQueueConsumer consumer = new StreamQueueConsumer(0, 0, 1,
                                                          new QueueConfig(QueuePartitioner.PartitionerType.FIFO, true));

    TTQueueOnVCTable table = new TTQueueOnVCTable( new MemoryOVCTable(Bytes.toBytes("TestStreamMemoryQueue")),
                                                   Bytes.toBytes("TestStream"),oracle,conf);
    OrderedVersionedColumnarTable metaTable = new MemoryOVCTable(Bytes.toBytes("StreamMeta"));
    StreamTable streamTable = new StreamTable(Bytes.toBytes("StreamTest"),table, metaTable, -1);

    MemoryReadPointer readPointer = new MemoryReadPointer(timeOracle.getTimestamp());

    int countEntriesWritten = writeNTimesToStream(1, streamTable);
    assertTrue(1== countEntriesWritten);

    StreamReadResult readResult = streamTable.read(consumer, readPointer);
    assertTrue(readResult.isSuccess());

    streamTable.ack(StreamEntryPointer.fromQueueEntryPointer(readResult.getEntryPointer()),consumer,readPointer);
    streamTable.unack(StreamEntryPointer.fromQueueEntryPointer(readResult.getEntryPointer()), consumer, readPointer);


    int countEntriesRead  = readAllEntriesFromStream(streamTable, consumer, readPointer);
    assertTrue(1==countEntriesRead);

  }