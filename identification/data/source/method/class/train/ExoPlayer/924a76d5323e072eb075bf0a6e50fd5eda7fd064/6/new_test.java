  private void createDefaultLoadControl() {
    builder.setAllocator(allocator);
    builder.setTargetBufferBytes(TARGET_BUFFER_BYTES);
    loadControl = builder.createDefaultLoadControl();
    loadControl.onTracksSelected(new Renderer[0], null, null);
  }