public void unack(StreamEntryPointer entryPointer, StreamQueueConsumer consumer, ReadPointer readPointer)
    throws  OperationException {
    queue.unack(entryPointer,consumer,readPointer);
  }