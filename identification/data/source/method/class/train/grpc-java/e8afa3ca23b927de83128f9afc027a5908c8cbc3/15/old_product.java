private void createStream(CreateStreamCommand command, ChannelPromise promise) {
    // Add the creation request to the queue.
    pendingStreams.addLast(new PendingStream(command, promise));

    // Process the pending streams queue.
    createPendingStreams();
  }