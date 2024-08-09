public void unack(StreamEntryPointer entryPointer, StreamQueueConsumer consumer, ReadPointer readPointer)
    throws  OperationException {
    queue.unack(name,entryPointer,consumer,readPointer);
  }