  private static void writeKnownLength(MessageFramer framer, byte[] bytes) {
    framer.writePayload(new ByteArrayInputStream(bytes));
    // TODO(carl-mastrangelo): add framer.flush() here.
  }