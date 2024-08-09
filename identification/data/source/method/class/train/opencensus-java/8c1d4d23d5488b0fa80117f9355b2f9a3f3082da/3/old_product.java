public byte[] getBytes() {
    return Arrays.copyOf(bytes, TRACE_ID_SIZE);
  }