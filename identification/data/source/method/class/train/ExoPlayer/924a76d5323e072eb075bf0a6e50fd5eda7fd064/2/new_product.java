public DefaultLoadControl createDefaultLoadControl() {
      Assertions.checkState(!createDefaultLoadControlCalled);
      createDefaultLoadControlCalled = true;
      if (allocator == null) {
        allocator = new DefaultAllocator(/* trimOnReset= */ true, C.DEFAULT_BUFFER_SEGMENT_SIZE);
      }
      return new DefaultLoadControl(
          allocator,
          minBufferMs,
          maxBufferMs,
          bufferForPlaybackMs,
          bufferForPlaybackAfterRebufferMs,
          targetBufferBytes,
          prioritizeTimeOverSizeThresholds,
          priorityTaskManager,
          backBufferDurationMs,
          retainBackBufferFromKeyframe);
    }