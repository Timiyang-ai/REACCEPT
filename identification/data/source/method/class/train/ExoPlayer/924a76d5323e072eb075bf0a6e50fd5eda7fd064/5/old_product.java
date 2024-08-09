@SuppressWarnings("deprecation")
    public DefaultLoadControl createDefaultLoadControl() {
      if (allocator == null) {
        allocator = new DefaultAllocator(true, C.DEFAULT_BUFFER_SEGMENT_SIZE);
      }
      return new DefaultLoadControl(
          allocator,
          minBufferMs,
          maxBufferMs,
          bufferForPlaybackMs,
          bufferForPlaybackAfterRebufferMs,
          targetBufferBytes,
          prioritizeTimeOverSizeThresholds,
          priorityTaskManager);
    }