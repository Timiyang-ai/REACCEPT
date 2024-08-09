public DefaultLoadControl createDefaultLoadControl() {
      Assertions.checkState(!createDefaultLoadControlCalled);
      createDefaultLoadControlCalled = true;
      if (allocator == null) {
        allocator = new DefaultAllocator(/* trimOnReset= */ true, C.DEFAULT_BUFFER_SEGMENT_SIZE);
      }
      return new DefaultLoadControl(
          allocator,
          minBufferAudioMs,
          minBufferVideoMs,
          maxBufferMs,
          bufferForPlaybackMs,
          bufferForPlaybackAfterRebufferMs,
          targetBufferBytes,
          prioritizeTimeOverSizeThresholds,
          backBufferDurationMs,
          retainBackBufferFromKeyframe);
    }