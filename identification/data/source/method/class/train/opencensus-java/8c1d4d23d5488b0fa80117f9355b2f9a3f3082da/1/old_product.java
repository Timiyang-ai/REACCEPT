public byte[] getBytes() {
    return Arrays.copyOf(bytes, SPAN_ID_SIZE);
  }