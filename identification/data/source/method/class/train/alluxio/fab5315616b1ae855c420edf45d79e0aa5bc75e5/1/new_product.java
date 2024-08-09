@Override
  public void flush() throws IOException {
    // We only flush the checkpoint output stream.
    // No flushing needed for ramfs block streams.
    mCheckpointOutputStream.flush();
  }