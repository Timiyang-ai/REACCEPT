void setStream(ClientStream stream) {
    synchronized (this) {
      if (cancelledPrematurely()) {
        // Already cancelled
        throw new IllegalStateException("Can't set on cancelled stream");
      }
      checkState(realStream == null, "Stream already created: %s", realStream);
      realStream = stream;
      if (compressor != null) {
        realStream.setCompressor(compressor);
      }
      if (this.decompressionRegistry != null) {
        realStream.setDecompressionRegistry(this.decompressionRegistry);
      }
      for (PendingMessage message : pendingMessages) {
        realStream.setMessageCompression(message.shouldBeCompressed);
        realStream.writeMessage(message.message);
      }
      // Set this again, incase no messages were sent.
      realStream.setMessageCompression(messageCompressionEnabled);
      pendingMessages.clear();
      if (pendingHalfClose) {
        realStream.halfClose();
        pendingHalfClose = false;
      }
      if (pendingFlowControlRequests > 0) {
        realStream.request(pendingFlowControlRequests);
        pendingFlowControlRequests = 0;
      }
      if (pendingFlush) {
        realStream.flush();
        pendingFlush = false;
      }
    }
  }