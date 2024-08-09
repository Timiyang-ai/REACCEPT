protected void inboundDataReceived(ReadableBuffer frame) {
      Preconditions.checkNotNull(frame, "frame");
      boolean needToCloseFrame = true;
      try {
        if (statusReported) {
          log.log(Level.INFO, "Received data on closed stream");
          return;
        }

        needToCloseFrame = false;
        deframe(frame, false);
      } finally {
        if (needToCloseFrame) {
          frame.close();
        }
      }
    }